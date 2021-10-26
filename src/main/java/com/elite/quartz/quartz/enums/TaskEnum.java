package com.elite.quartz.quartz.enums;

import com.elite.quartz.quartz.service.ISchedulingJob;
import com.elite.quartz.quartz.task.Task1;
import com.elite.quartz.quartz.task.Task2;
import com.elite.quartz.quartz.task.Task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务枚举类
 */
public enum TaskEnum {
    /**
     * 任务1
     */
    TASK_01("task1", new Task1()),
    /**
     * 任务2
     */
    TASK_02("task2", new Task2()),
    /**
     * 任务3
     */
    TASK_03("task3", new Task3()),;

    /**
     * 定时任务key
     */
    private String taskKey;
    /**
     * 定时任务 执行实现类
     */
    private ISchedulingJob iSchedulingJob;

    TaskEnum(String taskKey, ISchedulingJob taskJob) {
        this.taskKey = taskKey;
        this.iSchedulingJob = taskJob;
    }

    /**
     * 初始化 所有任务
     */
    public static Map<String, ISchedulingJob> initScheduledTask() {
        if (TaskEnum.values().length < 0) {
            return new ConcurrentHashMap<>();
        }
        Map<String, ISchedulingJob> scheduledTaskJobMap = new ConcurrentHashMap<>();
        for (TaskEnum taskEnum : TaskEnum.values()) {
            scheduledTaskJobMap.put(taskEnum.taskKey, taskEnum.iSchedulingJob);
        }
        return scheduledTaskJobMap;
    }
}
