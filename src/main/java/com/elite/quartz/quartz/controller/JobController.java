package com.elite.quartz.quartz.controller;


import com.elite.quartz.common.CommonResult;
import com.elite.quartz.quartz.model.Job;
import com.elite.quartz.quartz.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 定时任务 前端控制器
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/quartz/job")
public class JobController {

  @Autowired
  IJobService jobService;

    /**
     * 获取所有的任务
     */
    @GetMapping("/getAllJobs")
    public CommonResult getAllJobs(@RequestParam("id")String id,@RequestParam("jobName") String jobName){
        List<Job> allJobs = jobService.getAllJobs(id,jobName);
        return CommonResult.success(0,"",allJobs);
    }

    /**
     * 根据id获取job任务
     */
    @GetMapping("getJobById")
    public CommonResult getJob(@RequestParam("jobid")String jobid){
        Job job = jobService.getJob(jobid);
        return CommonResult.success(0,"",job);
    }

    /**
     * 修改任务
     */
    @PutMapping("/updatejob")
    public CommonResult updateJob(@RequestBody Job job){
        boolean flag = jobService.updateJob(job);
        if(flag){
            return  CommonResult.success(0,"操作成功");
        }
        return CommonResult.fail(-1,"更新失败");
    }

    /**
     * 新增任务
     */
    @PostMapping("/addJob")
    public CommonResult addJob(@RequestBody Job job){
        boolean flag = jobService.addJob(job);
        if(flag){
            return  CommonResult.success(0,"操作成功");
        }
        return CommonResult.fail(0,"添加失败");
    }

    /**
     * 删除任务
     */
    @DeleteMapping("/deleteJob")
    public CommonResult deleteJob(@RequestParam("jobid")String jobid){
        boolean flag = jobService.deleteJob(jobid);
        if(flag){
            return CommonResult.success(0,"");
        }
        return CommonResult.fail(0,"删除失败");
    }


}
