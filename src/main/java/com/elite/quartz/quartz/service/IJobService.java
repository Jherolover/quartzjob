package com.elite.quartz.quartz.service;

import com.elite.quartz.quartz.model.Job;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.print.attribute.standard.JobName;
import java.util.List;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
public interface IJobService extends IService<Job> {
    /**
     * 获取所有的任务
     * @param id
     * @param JobName
     * @return
     */
    List<Job> getAllJobs(String id,String JobName);

    /**
     * 根据jobid获取job任务
     * @param jobid
     * @return
     */
    Job getJob(String jobid);

    /**
     * 更新job任务
     * @param job
     * @return
     */
    boolean updateJob(Job job);

    /**
     * 添加job任务
     * @param job
     * @return
     */
    boolean addJob(Job job);

    /**
     * 删除Job任务
     * @param jobid
     * @return
     */
    boolean deleteJob(String jobid);

    /**
     * 初始化所有的任务
     */
    void initAllJobs();
}
