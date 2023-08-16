package com.zyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.zyf.pojo.Engineer;
import com.zyf.pojo.EngineerInformation;
import com.zyf.service.EngineerService;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/engineer/*")
public class EngineerServlet extends BaseServlet
{
    private EngineerService engineerService = new EngineerService();
    //查询工程师是否登录
    public void isEngineerLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        resp.setContentType("text/html;charset=utf-8");
        HttpSession httpSession = req.getSession(true);
        if(httpSession.getAttribute("engineer_login") != null)
        {
            String workNumber = (String) httpSession.getAttribute("engineer_login");
            resp.getWriter().write(workNumber);
        }
        else
        {
            resp.getWriter().write("");
        }
    }
    //查询工程师的姓名
    public void Engineer_showEngineerServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");

        //获取使用session存储在客户端对应的 id
        HttpSession httpSession = req.getSession();
        int id = (int) httpSession.getAttribute("id");
    
        //根据id 查询出工程师的姓名
        String engineerName = engineerService.selectEngineerName(id);

        if(engineerName != null)
        {
            resp.getWriter().write(engineerName);
        }
    }
    //查询工程师的全部信息
    public void selectAllEngineerInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");//解决中文乱码问题
        //获取使用session存储在客户端中的id
        HttpSession httpSession = req.getSession();
        int id = (int) httpSession.getAttribute("id");
        //查询信息 封装对象
        EngineerInformation engineerInformationAll = engineerService.selectAllEngineer(id);
//        System.out.println("工程师的全部信息"+engineerInformationAll);
        //将工程师的全部信息传到前端页面
        String engineerAll = JSON.toJSONString(engineerInformationAll);//将Java对象转化为JSON字符串
        if(engineerInformationAll != null)
        {
//            System.out.println("JSON字符串:"+engineerAll);
            resp.getWriter().write(engineerAll);
        }
    }
    //通过部门编号查询部门名称
    public void engineerDepartmentServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");//解决中文乱码问题
        //获取get请求的请求参数,即部门编号
        String department = req.getParameter("department");
//        System.out.println("部门编号: "+department);
        //根据部门编号查询部门名称
        if(department != null && department!= "")
        {
            String dept_name = engineerService.selectEngineerDepartment(department);
//            System.out.println("部门名称: "+dept_name);
            resp.getWriter().write(dept_name);
        }
    }
    //工程师页面退出登录的功能
    public void EngineerQuitServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("engineer_login");
        String workNumber =(String) httpSession.getAttribute("engineer_login");
        resp.sendRedirect("http://localhost:8081");
    }
    //修改电话号码
    public void updateTelephone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");//解决中文乱码问题
        //获取用户输入的电话号码
        String telephone = req.getParameter("telephone");
        //获取登录登录的工程师的id
        HttpSession httpSession = req.getSession();
        int id = (int) httpSession.getAttribute("id");
        //修改号码 返回受影响的行数
        int num = engineerService.updateEngineerTelephone(telephone,id);
        if(num != 1)
        {
            resp.getWriter().write("修改失败");
        }
        else
        {
            resp.getWriter().write("success");
        }
    }
    //工程师登录
    public void Engineer_loginServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        resp.setContentType("text/html;charset=utf-8");
        //获取post请求的请求参数
        BufferedReader br = req.getReader();
        //转化为JSON字符串
        String engineerInformation = br.readLine();

        //将JSON字符串转化为Java对象
        Engineer engineer = JSON.parseObject(engineerInformation,Engineer.class);

        //判断工程师账号在数据库中是否存在
        Engineer e = engineerService.selectEngineerInformation(engineer);
//        System.out.println("工程师信息: "+e);
        HttpSession httpSession = req.getSession(true);
        if(e != null) //可以查询到
        {
            //将工程师的工号存储在session中
            int id = e.getId();
//            System.out.println("工程师id: "+id);
            httpSession.setAttribute("id",id);
            httpSession.setAttribute("engineer_login",e.getWorkNumber());
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("账号或密码错误");

        }
    }
    //查询工程师输入的工号
    public void checkWorkNumberServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取get请求的请求参数
        String workNumber = req.getParameter("workNumber");

        //查询工号是否存在
        String Engineer_workNumber = engineerService.selectWorkNumber(workNumber);

        if(Engineer_workNumber == null) //为空 即工号查询不到
        {

            resp.getWriter().write("no_exist");
        }
        else
        {

            resp.getWriter().write("exist");
        }
    }
    //忘记密码,修改
    public void changeEngineerServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取post请求的请求参数
        BufferedReader br = req.getReader();
        //转化为JSON字符串
        String engineerInformation = br.readLine();

        //将JSON字符串转为Java对象
        EngineerInformation engineerInformation1 = JSON.parseObject(engineerInformation,EngineerInformation.class);

        //获取工程师输入的电话号码
        String engineerTelephone = engineerInformation1.getTelephone();

        //根据该号码查询工程师信息表的主键
        int id = engineerService.selectId(engineerTelephone);
        //获取新密码
        String newPassword = engineerInformation1.getNewPassword();

        //修改密码
        int num = engineerService.updateEngineerPassword(newPassword,id);
        System.out.println(num);
        if(num != 1)
        {
            resp.getWriter().write("请检查您的号码或工号");
        }
        else
        {
            resp.getWriter().write("success");
        }
    }
}
