package com.elite.quartz.quartz.mapper;

import com.elite.quartz.quartz.model.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 定时任务 Mapper 接口
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {

    List<Job> getAllJobs();
}
