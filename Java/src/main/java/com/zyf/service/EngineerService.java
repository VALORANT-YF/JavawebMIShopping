package com.zyf.service;

import com.zyf.mapper.EngineerMapper;
import com.zyf.pojo.Engineer;
import com.zyf.pojo.EngineerInformation;
import com.zyf.util.sqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class EngineerService
{
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryUtils.getSqlSessionFactory();

    //工程师登录 查询工程师的用户名以及密码
    public Engineer selectEngineerInformation(Engineer engineer)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        Engineer engineer1 = engineerMapper.selectEngineerInformation(engineer);
        sqlSession.close();
        return engineer1;
    }
    //查询工程师的工号
    public String selectWorkNumber(String workNumber)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        String Engineer_workNumber = engineerMapper.selectWorkNumber(workNumber);
        sqlSession.close();
        return Engineer_workNumber;
    }
    //工程师管理页面 查看工程师的姓名
    public String selectEngineerName(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        String engineerName = engineerMapper.selectEngineerName(id);
        sqlSession.close();
        return engineerName;
    }

    //根据工程师输入的电话 判断id
    public int selectId(String telephone)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        int id = engineerMapper.selectId(telephone);
        sqlSession.close();
        return id;
    }

    //根据id 修改密码
    public int updateEngineerPassword(String password , int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        int num = engineerMapper.updateEngineerPassword(password,id);
        sqlSession.close();
        return num;
    }

    //根据id查询出工程师的全部信息
    public EngineerInformation selectAllEngineer(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        EngineerInformation engineerInformationAll = engineerMapper.selectAllEngineer(id);
        sqlSession.close();
        return engineerInformationAll;
    }

    //根据部门编号查询出部门名称
    public String selectEngineerDepartment(String department)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        String dept_name = engineerMapper.selectEngineerDepartment(department);
        sqlSession.close();
        return dept_name;
    }

    //根据id修改工程师信息表中的电话号码
    public int updateEngineerTelephone(String telephone , int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EngineerMapper engineerMapper = sqlSession.getMapper(EngineerMapper.class);
        int num = engineerMapper.updateEngineerTelephone(telephone, id);
        sqlSession.close();
        return num;//返回受影响的行数
    }
}
