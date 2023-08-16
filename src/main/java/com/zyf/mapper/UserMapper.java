package com.zyf.mapper;

import com.zyf.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper
{
    //登录功能 查询用户的用户名及其密码
    User selectInformation(@Param("username") String username , @Param("password") String password);

    //注册功能,首先判断用户名是否存在
        //1.查询用户名
    String selectUsername(@Param("username") String username);
        //2.添加数据
    int addInformation(User user);

    //注册成功之后 tb_userinformation表中添加账号名 account
    int addUserInformation(@Param("account") String account);

    //根据账号 查询密码
    String selectPassword(@Param("username") String username);

    //根据账号 修改密码
    int updatePassword(@Param("account") String account , @Param("password") String password);

    //根据账号 删除账号密码
    int deleteUser(@Param("username") String username);
}
