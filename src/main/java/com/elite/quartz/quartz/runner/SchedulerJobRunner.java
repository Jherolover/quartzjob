package com.elite.quartz.quartz.runner;

import com.elite.quartz.quartz.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *系统启动完成需要完成的业务
 */
@Component
@Order(1)
public class SchedulerJobRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerJobRunner.class);

    @Autowired
    IJobService jobService;

    /**
     * 项目启动完毕之后需要执行的业务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info(" 《================项目启动完毕, 开启定时的任务开始!==================>");
      jobService.initAllJobs();
        LOGGER.info(" 《================项目启动完毕, 开启定时任务结束===============》!");

    }
}
