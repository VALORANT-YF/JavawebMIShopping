package com.zyf.web.servlet;


import com.alibaba.fastjson.JSON;
import com.zyf.pojo.Brand;
import com.zyf.pojo.UserInformation;
import com.zyf.service.UserInformationService;
import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.List;


@WebServlet("/userInformation/*")
public class UserInformationServlet extends BaseServlet
{
    private UserInformationService userInformationService = new UserInformationService();
    //增加用户的详细信息
    public void updateUserInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取请求参数
        BufferedReader br = req.getReader();
        String informationUser = br.readLine(); //JSON字符串
        byte[] CN = informationUser.getBytes(StandardCharsets.ISO_8859_1);
        informationUser = new String(CN, StandardCharsets.UTF_8);
        //将JSON字符串转化为Java对象
        UserInformation userInformation = JSON.parseObject(informationUser,UserInformation.class);
        //添加到数据库中
        int num = userInformationService.updateUserInformation(userInformation);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }
    //查询单个用户的全部信息
    public void selectUserInformationAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");//解决中文乱码问题
        //get
        String account = req.getParameter("account");

        //获取用户的详细信息
        UserInformation userInformation = userInformationService.selectUserInformationAll(account);


        //将Java对象转化为JSON字符串
        String json_userInformation = JSON.toJSONString(userInformation);
        if(json_userInformation != "" && json_userInformation != null)
        {
            resp.getWriter().write(json_userInformation);
        }
    }
    //删除个人信息
    public void deleteUserInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get
        String account = req.getParameter("account");
        int num = userInformationService.deleteUserInformation(account);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
    }
    //接收文件
    public void uploadServlet(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {

        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        resp.setContentType("text/html;charset = utf-8");
        List<FileItem> fileItems = servletFileUpload.parseRequest(req);
        //获取请求参数
        UserInformation userInformation = userInformationService.paseEmByFileItem(fileItems);
        System.out.println(userInformation);
        //添加到数据库中
        int num = userInformationService.updateUserInformation(userInformation);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }
    //查询头像以及昵称
    public void selectIndex(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {
        resp.setContentType("text/html;charset = utf-8");
        //get
        String account = req.getParameter("account");
        UserInformation userInformation = userInformationService.selectIndexByAccount(account);
        resp.getWriter().write(JSON.toJSONString(userInformation));
    }
}