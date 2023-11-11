package com.zyf.service;

import mapper.UserMapper;
import com.zyf.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.zyf.util.sqlSessionFactoryUtils;

public class UserService
{
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryUtils.getSqlSessionFactory();

    //用户登录 查询用户名和密码
    public User selectInformation(String username , String password)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectInformation(username,password);
        sqlSession.close();
        return user;
    }

    //注册功能 1.查询用户名是否存在
    public String selectUsername(String username)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String u = userMapper.selectUsername(username);
        sqlSession.close();
        return u;
    }

    //注册功能 2.添加数据
    public int addInformation(User user)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int num = userMapper.addInformation(user);
        sqlSession.close();
        return num;
    }

    //注册成功之后 向用户详细信息表中添加用户的账号名
    public int addUserInformation(String account)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int num = userMapper.addUserInformation(account);
        sqlSession.close();
        return num;
    }

    //查询密码
    public String selectPassword(String username)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String password = userMapper.selectPassword(username);
        sqlSession.close();
        return password;
    }

    //修改密码
    public int updatePassword(String account , String password)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int num = userMapper.updatePassword(account,password);
        sqlSession.close();
        return num;
    }

    //删除账号密码
    public int deleteTbUser(String username)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int num = userMapper.deleteUser(username);
        sqlSession.close();
        return num;
    }
}
