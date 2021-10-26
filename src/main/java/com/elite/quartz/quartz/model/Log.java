package com.elite.quartz.quartz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 定时任务日志
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("quartz_log")
public class Log extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String baenName;

    private LocalDateTime createTime;

    private String cronExpression;

    private String exceptionDetail;

    private Boolean isSuccess;

    private String jobName;

    private String methodName;

    private String params;

    private Long time;

    private LocalDateTime updateTime;

    private Boolean isDel;


}
