package com.elite.quartz.user.service;

import com.elite.quartz.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
public interface IUserService extends IService<User> {

    List<User> getUserList();
}
