package com.zyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.zyf.pojo.Shopping;
import com.zyf.service.ShoppingService;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@WebServlet(urlPatterns = "/shopping/*")
public class ShoppingServlet extends BaseServlet
{
    private ShoppingService shoppingService = new ShoppingService();


    public void addBuyBrandServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取请求参数 两种格式
        //1.需要向购物车内添加的商品信息
        BufferedReader br = req.getReader();
        String shoppingData = br.readLine();
        System.out.println(shoppingData);
        //防止中文乱码
//        byte[] CN = shoppingData.getBytes(StandardCharsets.ISO_8859_1);
//        shoppingData = new String(CN, StandardCharsets.UTF_8);
//
        //2.设置为默认地址的单选框的value值 用于判断用户是否勾选了单选框
        int remember_value = Integer.parseInt(req.getParameter("remember"));
        //将JSON字符串转化为Java对象
        Shopping shopping = JSON.parseObject(shoppingData,Shopping.class);

        //购买的商品价格的修改
        //1.获取购买的商品的数量
        int brandNumber = shopping.getBrandNumber();
        //2.获取商品的单价
        int aPrice = shopping.getPrice();
        //商品总价格
        int newPrice = aPrice * brandNumber;
        //更新价格
        shopping.setPrice(newPrice);
        if(remember_value == 1)
        {
            //封装cookie信息
            Cookie cookie_addressee = new Cookie("addressee",shopping.getAddressee());
            Cookie cookie_receiptAddress = new Cookie("receiptAddress",shopping.getReceiptAddress());
            cookie_addressee.setPath("/");
            cookie_receiptAddress.setPath("/");
            resp.addCookie(cookie_addressee);
            resp.addCookie(cookie_receiptAddress);
        }
        //先判断是否有重复商品
        Shopping brandRepeat = new Shopping();
        brandRepeat.setAddressee(shopping.getAddressee());//收件人姓名
        brandRepeat.setBrandName(shopping.getBrandName());//商品名称
        brandRepeat.setReceiptAddress(shopping.getReceiptAddress());//收件地址
        brandRepeat.setShoppingAccount(shopping.getShoppingAccount());//购买商品的账户
        Shopping repeatInformation = shoppingService.selectRepeatService(brandRepeat);

        //如果同一用户购买的是同一件商品 , 且收件人 收件地址 都相同 则 不向数据库中添加新的数据 而是 直接修改商品的数量
        if(repeatInformation != null)//改变商品数量
        {
            //获取原有的商品的数量
            int topNumber = repeatInformation.getBrandNumber();
            //获取第二次购买时的商品的数量
            int againNumber = shopping.getBrandNumber();
            //商品总数量
            int newBrandNumber = topNumber + againNumber;
            //更新商品价格
            int newPrice1 = newPrice * newBrandNumber;
            //修改重复商品的数量
            shoppingService.updateBrandNumberService(brandRepeat,newBrandNumber,newPrice1);
        }
        else
        {
            //向数据库中添加用户购买的商品的信息
            shoppingService.addBuyBrandService(shopping);
        }
    }
    //查询当前登录的用户所购买的商品的信息
    public void selectFinishMoneyServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        //获取当前登录的账号
        String shoppingAccount = req.getParameter("shoppingAccount");
        //根据账号 查询商品信息
        List<Shopping> shoppingList = shoppingService.selectFinishMoneyService(shoppingAccount);
        //将list集合改为json对象
        String json_shoppingList = JSON.toJSONString(shoppingList);
        resp.getWriter().write(json_shoppingList);
    }
    //用户注销账号时,删除已经支付的订单的信息
    public void deleteFinishBuyServlet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException
    {
        //获取需要注销的账号
        String shoppingAccount = req.getParameter("shoppingAccount");
        //根据账号删除订单信息
        shoppingService.deleteFinishBuyService(shoppingAccount);
    }
    //点击删除按钮之后 , 删除已经支付的订单的信息
    public void deleteFinishBrandServlet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException
    {
        //获取请求参数
        String json_id = req.getParameter("id");
        int id = Integer.parseInt(json_id);
        //根据id删除
        shoppingService.deleteFinishBrandByIdService(id);
    }
    //批量删除已经支付的订单的信息
    public void deleteFinishBrandByIdsServlet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException
    {
        //获取请求参数
        BufferedReader br = req.getReader();
        String json_ids = br.readLine();
        int[] ids = JSON.parseObject(json_ids,int[].class);
        //批量删除
        shoppingService.deleteFinishBrandByIdsService(ids);
    }
    //已经支付页面的查询功能
    public void selectFinishLikeServlet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        //获取请求的账号
        String shoppingAccount = req.getParameter("account");

        //获取请求参数
        String brandName = req.getParameter("brandName");//商品名称
        String addressee = req.getParameter("addressee");//收件人

        System.out.println(brandName+addressee+shoppingAccount);
        //模糊查询 拼接字符串 包含brandName 和 addressee 即可
        if(!Objects.equals(brandName, "") && brandName != null){
            brandName = "%"+brandName+"%";
        }
        if(!Objects.equals(addressee, "") && addressee != null){
            addressee = "%"+addressee+"%";
        }
        List<Shopping> shoppingList = shoppingService.selectFinishLikeService(brandName,addressee,shoppingAccount);
        //将list集合转化为JSON字符串
        String json_shoppingList = JSON.toJSONString(shoppingList);
        resp.getWriter().write(json_shoppingList);
    }
}
