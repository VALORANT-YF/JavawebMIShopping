package com.zyf.service;

import mapper.ShoppingMapper;
import com.zyf.pojo.Shopping;
import com.zyf.util.sqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ShoppingService
{
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryUtils.getSqlSessionFactory();

    public void addBuyBrandService(Shopping shopping)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        shoppingMapper.addBuyBrand(shopping);
        sqlSession.close();
    }
    //判断购买的商品是否重复
    public Shopping selectRepeatService(Shopping brandRepeat)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        Shopping repeatInformation = shoppingMapper.selectRepeatInformation(brandRepeat);
        sqlSession.close();
        return repeatInformation;
    }
    //如果商品重复 , 则直接修改商品的数量 不在向数据库中添加新的商品数据
    public void updateBrandNumberService(Shopping brandRepeat , int brandNumber , int price)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        shoppingMapper.updateBrandNumberInformation(brandRepeat,brandNumber,price);
        sqlSession.close();
    }
    //查询当前登录的用户所购买的商品的信息
    public List<Shopping> selectFinishMoneyService(String shoppingAccount)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        List<Shopping> shoppingList = new ArrayList<>();
        shoppingList = shoppingMapper.selectFinishMoney(shoppingAccount);
        sqlSession.close();
        return shoppingList;
    }
    //用户注销账号时,删除已经支付的订单信息
    public void deleteFinishBuyService(String shoppingAccount)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        shoppingMapper.deleteFinishBuy(shoppingAccount);
        sqlSession.close();
    }
    //用户单个删除已经支付的订单信息
    public void deleteFinishBrandByIdService(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        shoppingMapper.deleteFinishBrandById(id);
        sqlSession.close();
    }
    //批量删除
    public void deleteFinishBrandByIdsService(int[] ids)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        shoppingMapper.deleteFinishBrandByIds(ids);
        sqlSession.close();
    }
    //已支付页面的查询功能
    public List<Shopping> selectFinishLikeService(String brandName , String addressee , String shoppingAccount)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ShoppingMapper shoppingMapper = sqlSession.getMapper(ShoppingMapper.class);
        List<Shopping> shoppingList = shoppingMapper.selectFinishLike(brandName,addressee,shoppingAccount);
        sqlSession.close();
        return shoppingList;
    }
}
