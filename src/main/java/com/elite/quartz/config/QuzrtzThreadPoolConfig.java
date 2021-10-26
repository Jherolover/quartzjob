package com.elite.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * quartz线程池配置
 */
@Configuration
@Slf4j
public class QuzrtzThreadPoolConfig {
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    /**
     * 获取工厂
     */
    @Bean
    @Order(2)
    public SchedulerFactoryBean getSchedulerFactoryBean() throws IOException {

        //判断线程池任务是否为空
        if(threadPoolTaskExecutor == null){
            log.error("threadPoolTaskExecutor is null");
            System.exit(0);
        }
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //读取配置文件得属性并初始化
        propertiesFactoryBean.afterPropertiesSet();
        //创建schedulerfactorybean
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTaskExecutor(threadPoolTaskExecutor);
        schedulerFactoryBean.setQuartzProperties(propertiesFactoryBean.getObject());
         //spring 关闭时候，会等待所有的启动的quartz job结束后 才能进行shurdown
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactoryBean.setOverwriteExistingJobs(false);
        schedulerFactoryBean.setStartupDelay(1);
        return schedulerFactoryBean;
    }
    /**
     * 任务线程池
     */
    @Bean("taskExecutor")
    @Primary
    @Order(1)
     public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(5);
        threadPoolTaskExecutor.setCorePoolSize(2);
         RejectedExecutionHandler handler = new RejectedExecutionHandler() {
             @Override
             public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                 log.error("线程池超过最大容量，进行扩容{}",executor.getPoolSize());
                 executor.setMaximumPoolSize(10);
                 Thread thread = new Thread(r);
                 thread.start();
                 log.error(r.toString());
             }
         };
         threadPoolTaskExecutor.setRejectedExecutionHandler(handler);
         return threadPoolTaskExecutor;
     }

}
