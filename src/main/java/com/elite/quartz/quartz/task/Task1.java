package com.elite.quartz.quartz.task;

import com.elite.quartz.quartz.service.ISchedulingJob;

import java.time.LocalDate;
import java.util.Locale;

/**
 *执行任务
 */
public class Task1 implements ISchedulingJob {

    @Override
    public void run() {
        System.out.println(LocalDate.now()+"任务shedulingtask1开始执行");
    }
}
