package com.elite.quartz.quartz.service.impl;

import com.elite.quartz.quartz.model.Log;
import com.elite.quartz.quartz.mapper.LogMapper;
import com.elite.quartz.quartz.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
