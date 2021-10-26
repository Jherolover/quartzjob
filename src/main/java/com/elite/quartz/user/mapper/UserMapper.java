package com.elite.quartz.user.mapper;

import com.elite.quartz.user.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author elite
 * @since 2021-10-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    public List<User> getUserList();


}
