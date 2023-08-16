package com.zyf.web.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet
{
    //根据请求的最后一段路径进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取请求路径
        String uri = req.getRequestURI();//http://localhost:8080/Java_war/brand/selectAll
        //获取最后一段路径
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);
//        System.out.println("路径: "+methodName);
        //执行方法
        Class<? extends BaseServlet> cls = this.getClass();//获取对应的字节码文件
        try
        {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        }
        catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}
