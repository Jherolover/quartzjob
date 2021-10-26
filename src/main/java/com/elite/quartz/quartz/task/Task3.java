package com.elite.quartz.quartz.task;

import com.elite.quartz.quartz.service.ISchedulingJob;

import java.time.LocalDate;

/**
 *执行任务
 */
public class Task3 implements ISchedulingJob {

    @Override
    public void run() {
        System.out.println(LocalDate.now()+"任务shedulingTask3开始执行");
    }
}
