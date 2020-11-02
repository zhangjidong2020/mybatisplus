package com.blb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blb.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User findUserById( Long id);

}
