package com.zyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.zyf.pojo.User;
import com.zyf.service.UserService;
import com.zyf.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends BaseServlet
{
    private UserService userService = new UserService();

    //查询当前登录的账号
    public void indexshow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取使用session存储的username
        HttpSession httpSession = req.getSession(true);
        String username = (String)httpSession.getAttribute("username");
        if(username != null)
        {
            resp.getWriter().write(username);
        }
    }
    //验证码
    public void checkCodeServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InterruptedException {
        //生成验证码
        ServletOutputStream os = resp.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100,50,os,4);

        os.close();//关闭生成的流
        //将生成的验证码存入Session
        HttpSession httpsession = req.getSession(true);
        httpsession.setAttribute("checkCodeSystem",checkCode);
        System.out.println("系统生成的验证码2: "+checkCode);

    }
    //登录功能 检测用户的账号密码是否正确
    public void loginServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取存储在session中的验证码
        HttpSession httpSession = req.getSession(true);
        String code = (String) httpSession.getAttribute("checkCodeSystem");
        System.out.println("存储在客户端的验证码: "+code);

        resp.setContentType("text/html;charset=utf-8");
        //获取login.html输入框中输入的账号密码
        //获取请求体的数据
        BufferedReader br = req.getReader();
        String information = br.readLine(); //JSON字符串
        //将JSON字符串转化为Java对象
        User user = JSON.parseObject(information,User.class);
        String username = user.getUsername();
        String password = user.getPassword();
        String inputCode = user.getCode();
        System.out.println("用户输入的验证码: "+inputCode);
        if(!inputCode.equalsIgnoreCase(code))
        {
            resp.getWriter().write("验证码错误");
            return;
        }
        //判断用户输入的账号,密码能否在数据库中查询到
        User u = userService.selectInformation(username,password);

        if(u != null)
        {
            HttpSession httpSession1 = req.getSession(true);
            httpSession1.setAttribute("username",u.getUsername());
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("用户名或密码错误,请重新输入");
        }
    }
    //注册 检测用户名是否存在
    public void registerServlet_username(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //当input框失去焦点后 判断用户名是否有重复
        //1.获取get请求的请求参数
        String username = req.getParameter("username");

        //2.查询数据库中是否存在该用户名
        String select_username = userService.selectUsername(username);

        //3.如果select_username为空 则可以注册
        if(select_username == null || select_username == "")
        {

            resp.getWriter().write("success");
        }
        else
        {

            resp.getWriter().write("exist");
        }
    }
    //注册功能的实现
    public void registerServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession httpSession = req.getSession();
        //判断用户的输入的验证码和系统生成的验证码是否一致
        String code = (String) httpSession.getAttribute("checkCodeSystem");
        resp.setContentType("text/html;charset=utf-8");
        //获取post请求的请求参数
        BufferedReader br = req.getReader();

        String register_information = br.readLine();
        System.out.println(br.readLine());
        //将获取的JSON字符串转换为Java对象
        User user = JSON.parseObject(register_information,User.class);
        String register_username = user.getUsername();
        String register_password = user.getPassword();
        String register_code = user.getCode();//验证码为用户输入的验证码
        if(!code.equalsIgnoreCase(register_code))
        {
            resp.getWriter().write("验证码错误");
            return;
        }
        //将用户名和密码封装为user对象
        User u = new User(register_username,register_password);
        int num = userService.addInformation(u);//注册成功
        resp.getWriter().write("success");
    }

    //注册成功之后 向用户的详细信息表中添加用户的账号
    public void addUserInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("开始添加");
        //获取用户的账号
        String account = req.getParameter("account");
        //添加
        int num = userService.addUserInformation(account);
        if(num > 0)
        {
            System.out.println("添加成功");
        }
        else
        {
            System.out.println("添加失败");
            resp.getWriter().write("failed");
        }
    }
    //查询密码
    public void selectPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get
        String username = req.getParameter("username");

        String password = userService.selectPassword(username);
        resp.getWriter().write(password);
    }
    //退出登录功能实现
    public void userQiutServlet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession httpSession = req.getSession();
        //删除使用session存储的用户账号
        httpSession.removeAttribute("username");
        resp.sendRedirect("http://localhost:8080/Java_war/login.html");
    }
    //修改密码
    public void updatePassword (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get
        String account = req.getParameter("account");//账号
        String password = req.getParameter("password");//密码
        int num = userService.updatePassword(account,password);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }
    //查询tb_user表中的信息
    public void selectTbUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.selectInformation(username,password);
        if(user != null)
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write(0);
        }
    }
    //删除账号密码
    public void deleteTbUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get
        String username = req.getParameter("username");
        int num = userService.deleteTbUser(username);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
    }
}
