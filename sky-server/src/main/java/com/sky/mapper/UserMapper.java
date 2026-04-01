package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);

    void insert(User user);
}
