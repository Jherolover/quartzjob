package com.elite.quartz.quartz.mapper;

import com.elite.quartz.quartz.model.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 定时任务日志 Mapper 接口
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
