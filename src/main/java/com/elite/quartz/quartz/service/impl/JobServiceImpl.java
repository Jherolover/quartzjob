package com.elite.quartz.quartz.service.impl;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elite.quartz.quartz.model.Job;
import com.elite.quartz.quartz.mapper.JobMapper;
import com.elite.quartz.quartz.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elite.quartz.quartz.service.ISchedulingJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired(required = false)
    JobMapper jobMapper;

    /**
     * 可重入锁
     */
   private ReentrantLock lock = new ReentrantLock();

    /**
     * 定时任务线程池
     *
     */
    @Autowired(required = false)
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 所有的定时任务存放map
     * key：任务key
     * value 任务实现
     */
   @Autowired
   @SuppressWarnings("all")
   @Qualifier(value ="scheduledTaskJobMap")
   private Map<String, ISchedulingJob> scheduledTaskJobMap;


   /**
    * 存放已经启动的任务
    */
   private Map<String , ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>();

    /**
     * 获取所有的任务列表
     * @return
     */
    @Override
    public List<Job> getAllJobs(String id,String jobName) {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        if(id != null && id != ""){
            queryWrapper.eq("id",id);
        }
        if(jobName != null && jobName != ""){
            queryWrapper.like("job_name",jobName);
        }
        return this.list(queryWrapper);
    }

    /**
     * 获取job任务
     * @param jobid
     * @return
     */
    @Override
    public Job getJob(String jobid) {
        return this.getById(jobid);
    }

    /**
     * 添加job任务
     * @param job
     * @return
     */
    @Transactional
    @Override
    public boolean updateJob(Job job) {

        return this.updateById(job);
    }

    /**
     * 添加任务
     * @param job
     * @return
     */
    @Transactional
    @Override
    public boolean addJob(Job job) {
        return this.save(job);
    }

    /**
     * 删除job
     * @param jobid
     * @return
     */
    @Transactional
    @Override
    public boolean deleteJob(String jobid) {
        return this.removeById(jobid);
    }

    /**
     * 初始化所有的任务
     */
    @Override
    public void initAllJobs() {

        LOGGER.info("程序启动====>获取所有的任务列表....");
        List<Job> allJobs = this.getAllJobs(null, null);
        if(CollectionUtils.isEmpty(allJobs)){
            LOGGER.info("===>当前没有需要执行的任务");
            return;
        }
        //不为空进行添加任务进行执行
        for ( Job job : allJobs){
            //获取任务bean
            String taskKey = job.getBeanName();
            //校验是否启动
            if( this.isStart(taskKey)){
                continue;
            }
            //没有启动进行添加任务
            this.doStartTask(job);
        }


    }

    /**
     * 启动任务
     * @param job
     */
    private void doStartTask(Job job) {

        // 获取任务key
        String taskKey = job.getBeanName();
        //获取cron  expression
        String cron = job.getCronExpression();
        //获取需要定时调度的接口
        ISchedulingJob iSchedulingJob = scheduledTaskJobMap.get(taskKey);

        LOGGER.info(">>>>>> 任务 [ {} ] ,cron={}", job.getBeanName(), cron);
        ScheduledFuture scheduledFuture =threadPoolTaskScheduler.schedule(iSchedulingJob, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                org.springframework.scheduling.support.CronTrigger cronTrigger = new CronTrigger(cron);
                return cronTrigger.nextExecutionTime(triggerContext);
            }
        });
        //将启动的任务放入 map
        scheduledFutureMap.put(taskKey, scheduledFuture);

    }

    /**
     * 任务是否已经启动
     */
    private Boolean isStart(String taskKey) {
        //校验是否已经启动
        if (scheduledFutureMap.containsKey(taskKey)) {
            if (!scheduledFutureMap.get(taskKey).isCancelled()) {
                return true;
            }
        }
        return false;
    }
    /**
     * 打印正在启动的任务
     */
    public String printlnTask() {
        LOGGER.info("当前启动的任务项 size {}", scheduledFutureMap.size());
        String printlnTask = JSON.toJSONString(scheduledFutureMap);
        LOGGER.info("当前启动的任务项 {}", printlnTask);
        return printlnTask;
    }
}
