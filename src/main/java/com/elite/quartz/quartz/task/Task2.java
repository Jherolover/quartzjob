package com.elite.quartz.quartz.task;

import com.elite.quartz.quartz.service.ISchedulingJob;

import java.time.LocalDate;

/**
 *执行任务
 */
public class Task2 implements ISchedulingJob {

    @Override
    public void run() {
        System.out.println(LocalDate.now()+"任务shedulingtask2开始执行");
    }
}
