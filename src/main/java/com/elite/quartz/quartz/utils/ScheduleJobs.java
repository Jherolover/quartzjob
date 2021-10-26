package com.elite.quartz.quartz.utils;

import com.elite.quartz.quartz.model.Job;
import com.elite.quartz.quartz.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务控制工具类
 */
public class ScheduleJobs {

    @Autowired
    private SchedulerFactoryBean factoryBean;

    //注入job服务类
    @Autowired
    IJobService jobService;

    /**
     * 启动所有的定时任务
     */
     public void startAllJobs(){
         org.quartz.Scheduler scheduler = factoryBean.getScheduler();
         //获取数据库配置的所有的任务
         List<Job> taskList = jobService.list();
         if(taskList.size() > 0){
             for(Job  task:taskList){

             }

         }


     }


}
