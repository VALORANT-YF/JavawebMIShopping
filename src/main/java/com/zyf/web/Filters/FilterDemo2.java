package com.zyf.web.Filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo2 implements Filter
{
    //过滤器 拦截除index.html 和 home.html以外所有的资源
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        //需要放行的资源
        String[] urls = {"/upload/images/","register.html","/indexShow","login.html","/images/","/css/","/loginServlet","/changeEngineerServlet","/checkCodeServlet","/checkWorkNumberServlet","/Engineer_loginServlet","/registerServlet","/registerServlet_username","home.html","engineer_changePassword.html","index.html","engineer.html","/fonts/","/js/","/element_ui/","/EngineerQuitServlet","/addUserInformation"};
        //获取当前访问的资源路径
        String url = req.getRequestURL().toString();
        for(String element : urls)
        {
            if(url.contains(element))
            {
                filterChain.doFilter(req,servletResponse);
                return;
            }
        }

        //判断工程师 或 这用户是否输入了账号 密码 没有输入 拦截
        HttpSession httpSession = req.getSession(true);
        Object username = httpSession.getAttribute("username");
        Object engineer = httpSession.getAttribute("engineer_login");
//        System.out.println("engineer: "+engineer);
        if((username != null && username != "") || (engineer != null && engineer != "") )
        {
            filterChain.doFilter(req,servletResponse);
        }
        else
        {
            req.getRequestDispatcher("//http://localhost:8081").forward(req,servletResponse);
        }
    }

    @Override
    public void destroy()
    {

    }
}
