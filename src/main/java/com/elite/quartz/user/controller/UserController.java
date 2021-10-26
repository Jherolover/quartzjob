package com.elite.quartz.user.controller;


import com.elite.quartz.user.model.User;
import com.elite.quartz.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("/getUserList")
    public List<User> getUserList(){
       return userService.getUserList();
    }

}
