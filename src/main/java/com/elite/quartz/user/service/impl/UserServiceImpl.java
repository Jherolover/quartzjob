package com.elite.quartz.user.service.impl;

import com.elite.quartz.user.model.User;
import com.elite.quartz.user.mapper.UserMapper;
import com.elite.quartz.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 获取用户列表
     * @return
     */
    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
