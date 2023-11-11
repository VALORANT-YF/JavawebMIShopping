//package com.zyf.web.Filters;
//
//import java.io.IOException;
//
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//
//import javax.servlet.http.HttpServletResponse;
//
//@WebFilter("/*")
//public class ResponseFilter implements Filter
//{
//
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        HttpServletResponse response = (HttpServletResponse) resp;
//        response.setCharacterEncoding("UTF-8");
//        /* 允许跨域的主机地址 */
//        response.setHeader("Access-Control-Allow-Origin", "http://index.nat100.top/");
//        /* 允许跨域的请求方法GET, POST, HEAD 等 */
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        /* 重新预检验跨域的缓存时间 (s) */
//        response.setHeader("Access-Control-Max-Age", "3600");
//        /* 允许跨域的请求头 */
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        /* 是否携带cookie */
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        chain.doFilter(req, resp);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//        System.out.println("ResponseFilter init...");
//    }
//}

