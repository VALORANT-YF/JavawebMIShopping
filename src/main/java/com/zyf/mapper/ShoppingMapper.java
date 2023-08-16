package com.zyf.mapper;

import com.zyf.pojo.Shopping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingMapper
{
    //向数据库中用户购买的商品
    void addBuyBrand(Shopping shopping);
    //查询tb_shopping中的商品名称 , 收件人姓名 , 收件地址 , 购买商品的账号 ,以及商品数量
    Shopping selectRepeatInformation(Shopping brandRepeat);
    void updateBrandNumberInformation(@Param("Shopping") Shopping brandRepeat , @Param("brandNumber") int brandNumber , @Param("price") int price);
    //查询属于当前登录的用户已经购买的商品的信息
    List<Shopping> selectFinishMoney(String shoppingAccount);
    //根据用户名 删除全部已经支付的订单信息(用户注销功能的实现)
    void deleteFinishBuy(String shoppingAccount);
    //单个删除用户想删除的已经购买的商品
    void deleteFinishBrandById(int id);
    //批量删除
    void deleteFinishBrandByIds(@Param("ids") int[] ids);
    //查询功能
    List<Shopping> selectFinishLike(@Param("brandName") String brandName , @Param("addressee") String addressee , @Param("shoppingAccount") String shoppingAccount);
}
