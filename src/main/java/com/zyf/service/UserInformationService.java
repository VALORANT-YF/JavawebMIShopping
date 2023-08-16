package com.zyf.service;


import com.zyf.mapper.UserInformationMapper;
import com.zyf.pojo.UserInformation;
import com.zyf.util.sqlSessionFactoryUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class UserInformationService
{
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryUtils.getSqlSessionFactory();
    //完善用户详细信息
    public int updateUserInformation(UserInformation userInformation)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInformationMapper userInformationMapper = sqlSession.getMapper(UserInformationMapper.class);
        int num = userInformationMapper.updateUserInformation(userInformation);
        sqlSession.close();
        return num;
    }
    //查询单个用户的全部信息
    public UserInformation selectUserInformationAll(String account)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInformationMapper userInformationMapper = sqlSession.getMapper(UserInformationMapper.class);
        UserInformation userInformation = userInformationMapper.selectUserInformationAll(account);
        sqlSession.close();
        return userInformation;
    }
    //删除个人信息
    public int deleteUserInformation(String account)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInformationMapper userInformationMapper = sqlSession.getMapper(UserInformationMapper.class);
        int num = userInformationMapper.deleteUserInformation(account);
        sqlSession.close();
        return num;
    }
    //上传文件
    public UserInformation paseEmByFileItem(List<FileItem> fs) throws Exception
    {
        UserInformation userInformation = new UserInformation();
        if(fs != null)
        {
            for(FileItem f : fs)
            {
                if(f.isFormField()) //普通表单
                {
                    String inputName = f.getFieldName();//获取表单的name属性值
                    String inputValue = f.getString("utf-8");
                    if("nickname".equals(inputName))
                    {
                        if(inputValue != null && !"".equals(inputValue))
                        {
                            userInformation.setNickname(inputValue);
                        }
                    }
                    if("account".equals(inputName))
                    {
                        if(inputValue != null && !"".equals(inputValue))
                        {
                            userInformation.setAccount(inputValue);
//                            System.out.println("账号:"+inputValue);
                        }
                    }
                    if("sex".equals(inputName))
                    {
                        if(inputValue != null && !"".equals(inputValue))
                        {
                            userInformation.setSex(Integer.parseInt(inputValue));
//                            System.out.println("性别:"+inputValue);
                        }
                    }
                    if("city".equals(inputName))
                    {
                        if(inputValue != null && !"".equals(inputValue))
                        {
                            userInformation.setCity(inputValue);
//                            System.out.println("城市:"+inputValue);
                        }
                    }
                }
                else //文件上传
                {
                    //获取文件
                    String oldImgName = f.getName();
                    //新文件名称
                    String newImgName = UUID.randomUUID().toString()+oldImgName.substring(oldImgName.lastIndexOf("."));
                    //文件
                    File newImgFile = new File("D:\\softwore\\Vue\\vue_opus\\public\\uploadImages\\"+newImgName);
                    f.write(newImgFile);//把添加的图片写到本地硬盘中
                    //往数据库中存图片路径
                    userInformation.setAvatarPath(newImgName);//只将图片名称写在数据库
                    //upload/images/fdb4e1aa-f4af-40fd-a1d0-58e37548ca4d.jpg
                }
            }
        }
        return userInformation;
    }
    //查询昵称以及对应的头像路径
    public UserInformation selectIndexByAccount(String account)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInformationMapper userInformationMapper = sqlSession.getMapper(UserInformationMapper.class);
        UserInformation userInformation = userInformationMapper.selectIndexByAccount(account);
        sqlSession.close();
        return userInformation;
    }
}
