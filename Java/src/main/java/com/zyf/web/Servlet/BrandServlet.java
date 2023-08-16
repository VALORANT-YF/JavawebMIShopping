package com.zyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyf.pojo.Brand;
import com.zyf.pojo.PageBean;
import com.zyf.service.BrandService;
import org.apache.ibatis.javassist.bytecode.ByteArray;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/brand/*")
public class BrandServlet extends BaseServlet
{
    private BrandService brandService = new BrandService();

    //查询所有
    public void selectAllBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Brand> brands = brandService.selectAllBrand();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(brands);
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void addBrandServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //接收品牌数据
        resp.setContentType("text/html;charset=utf-8");
        BufferedReader br = req.getReader();
        String newBrand = br.readLine(); //JSON字符串
    
        //get请求中文乱码解决
//        byte[] CN = newBrand.getBytes(StandardCharsets.ISO_8859_1);
//        newBrand = new String(CN, StandardCharsets.UTF_8);
        Brand brand = JSON.parseObject(newBrand,Brand.class);//将JSON字符串转化为Java对象
        //调用添加语句
        int num = brandService.addBrand(brand);
        if(num != 1)   
        {
            resp.getWriter().write("添加失败");
        }
        else
        {
            resp.getWriter().write("success");
        }
    }

    //批量删除的功能
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        //获取请求参数
        BufferedReader br = req.getReader();
        String str_id = br.readLine();

        //转化为int类型的数组
        int[] ids = JSON.parseObject(str_id,int[].class);

        int num = brandService.deleteByIds(ids);

        if(num < 0)
        {
            resp.getWriter().write("删除失败");
        }
        else
        {
            resp.getWriter().write("success");
        }
    }

    //条件分页查询
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        BufferedReader br = req.getReader();
        String json = br.readLine(); //JSON字符串
//        System.out.println("json"+JSON.parseObject(json,Brand.class));
        //解决请求参数中文乱码问题
//        byte[] CN = json.getBytes(StandardCharsets.ISO_8859_1);
//        json = new String(CN, StandardCharsets.UTF_8);

        Brand brand = JSON.parseObject(json,Brand.class);
//        System.out.println(brand);
        //查询
        //接收 当前页码 和 每页展示条数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //转为JSON字符串
        String page = JSON.toJSONString(pageBean,SerializerFeature.IgnoreErrorGetter);

        if(page != null && page != "")
        {
            resp.getWriter().write(page);
        }
    }
    //根据id单独删除商品信息
    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取id
        int id = Integer.parseInt(req.getParameter("id"));

        //返回受影响的行数
        int num = brandService.deleteById(id);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }

    //根据id查询对应商品的信息
    public void ChangeById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取id
        int id = Integer.parseInt(req.getParameter("id"));
        //获取查询到的商品信息
        Brand brand = brandService.selectBrandById(id);
        resp.setContentType("text/html;charset=utf-8");
        //将Java对象转化为JSON字符串
        String brand_json = JSON.toJSONString(brand, SerializerFeature.IgnoreErrorGetter);
        if(brand != null)
        {
            resp.getWriter().write(brand_json);
        }
    }

    //修改商品信息
    public void updateBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取修改后的商品信息
        BufferedReader br = req.getReader();
        String brandInformation = br.readLine();
        //解决请求参数中文乱码问题
//        byte[] CN = brandInformation.getBytes(StandardCharsets.ISO_8859_1);
//        brandInformation = new String(CN, StandardCharsets.UTF_8);

        //将JSON字符串转化为Java对象
        Brand brand = JSON.parseObject(brandInformation,Brand.class);
        //执行修改信息的方法
        //获取id
        int id = Integer.parseInt(req.getParameter("id"));
        int num = brandService.updateBrand(id,brand);

        if(num > 0)
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }
    //查询商品名称
    public void selectByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        //获取请求参数
        BufferedReader br = req.getReader();
        String str_id = br.readLine();

        //转化为int类型的数组
        int[] ids = JSON.parseObject(str_id,int[].class);

        List<Brand> brandNames = brandService.selectByIds(ids);
        //转化为JSON字符串
        String json_brandNames = JSON.toJSONString(brandNames);

        resp.getWriter().write(json_brandNames);
    }
    //批量删除图片
    public void deleteImagesByBrandNames(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        //获取请求参数
        BufferedReader br = req.getReader();
        String str_brandNames = br.readLine();

        //转化为String类型的数组
        String[] brandNames = JSON.parseObject(str_brandNames,String[].class);
        int num = brandService.deleteImagesByBrandNames(brandNames);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }
    //检测用户输入的商品名称是否存在
    public void selectBrandName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get请求
        String in_brandName = req.getParameter("brandName");
        String brandName = brandService.selectBrandName(in_brandName);
        if(brandName != null && brandName != "")
        {
            resp.getWriter().write("success");
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }
    //添加商品图片



    //修改图片路径
    public void updateBrandImageUrl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String brandName = req.getParameter("brandName");
        String imageUrl = req.getParameter("imageUrl");

        int num = brandService.updateBrandImageUrl(brandName,imageUrl);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
    }
    //查看图片路径以及图片对应的商品名称
    public void selectBrandImages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        String imageUrl = req.getParameter("imageUrl");

        Brand brand = brandService.selectBrandImages(imageUrl);

        if(brand != null)
        {
            String brandName = brand.getBrandName();
            String tb_imageUrl = brand.getImageUrl();

            if(tb_imageUrl != null && tb_imageUrl != "")
            {
                resp.getWriter().write(brandName);
            }
            else
            {
                resp.getWriter().write("failed");
            }
        }
        else
        {
            resp.getWriter().write("failed");
        }
    }
    //查询图片路径是否有重复
    public void selectImageUrl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        String imageUrl = req.getParameter("imageUrl");
        int id = Integer.parseInt(req.getParameter("id"));
        String imageUrl_check = brandService.selectImageUrl(imageUrl,id);
        if(imageUrl_check != null)
        {
            resp.getWriter().write("exist");
        }
    }
    //单个删除图片
    public void deleteImageUrl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get
        String brandName = req.getParameter("brandName");
        int num = brandService.deleteImageUrl(brandName);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
    }
    //修改
    public void updateImageName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //get
        int id = Integer.parseInt(req.getParameter("id"));
        String newBrandName = req.getParameter("newBrandName");
        String imageUrl = req.getParameter("imageUrl");
        int num = brandService.updateImageName(id,newBrandName,imageUrl);
        if(num > 0)
        {
            resp.getWriter().write("success");
        }
    }
    //根据id查询brandName
    public void selectBrandNameById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        //get
        int id = Integer.parseInt(req.getParameter("id"));
        String brandName = brandService.selectBrandNameById(id);
        resp.getWriter().write(brandName);
    }
    //根据id查询商品图片
    public void selectImgByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        //接收ids 字符串
        BufferedReader br = req.getReader();
        String json_ids = br.readLine();
        //将json字符串转化为ids数组
        int[] ids = JSON.parseObject(json_ids,int[].class);
        List<Brand> brandImgs = brandService.selectImgByIds(ids);
        //将list集合转化为JSNO字符串
        resp.getWriter().write(JSON.toJSONString(brandImgs));
    }
    //根据id单个查询商品图片
    public void selectImgById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        List<Brand> brandImg = brandService.selectImgById(id);
        resp.getWriter().write(JSON.toJSONString(brandImg));
    }
    //接收请求参数 , 将数据渲染到brandBuy.html页面商
    public void viewBrandBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Brand brand = brandService.selectBrandById(id);
        Brand brand1 = brandService.selectImgById(id).get(0);
        brand.setImageUrl(brand1.getImageUrl());
        resp.getWriter().write(JSON.toJSONString(brand));
    }
    //联想搜索功能
    public void selectBrandBySearchServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        String brandName = req.getParameter("brandName");
        brandName = "%"+brandName+"%";
        List<Brand> brandList= brandService.selectBrandBySearchService(brandName);
        String json_brandList = JSON.toJSONString(brandList);
        resp.getWriter().write(json_brandList);
    }
    //查询商品单价
    public void selectPriceServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        String brandName = req.getParameter("brandName");
        int price = brandService.selectPriceService(brandName);
        resp.getWriter().write(JSON.toJSONString(price));
    }
    //查询商品图片
    public void selectImgUrlByIdServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String imageUrl = brandService.selectImgUrlById(id);
        resp.getWriter().write(imageUrl);
    }
    //根据brandName 和 companyName查询商品是否重复 或者 已经被删除
    public void selectRepeat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        int id = Integer.parseInt(req.getParameter("id"));
        Brand brand = brandService.selectRepeatService(brandName,companyName,id);
        //如果brand不为空 则说明修改或者增加商品时 产生了重复商品
        if(brand != null)
        {
            //判断重复的商品是已经被删除的 还是 存在的
            int flag = brand.getFlag();
            if(flag == 0)
            {
                resp.getWriter().write("0");
            }
            else
            {
                resp.getWriter().write("1");
            }
        }
        else
        {
            resp.getWriter().write("no_repeat");//没有重复商品
        }
    }
    //查询已经被删除的商品
    public void selectRemoveBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        List<Brand> listBrand = brandService.selectRemoveBrand();
        if(listBrand.size() == 0)
        {
            resp.getWriter().write("NULL");
        }
        else
        {
            resp.getWriter().write(JSON.toJSONString(listBrand));
        }
    }
    //还原被删除的商品
    public void updateRemoveBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        brandService.updateRemoveBrandService(id);
    }
    //模糊查询已经被删除的商品
    public void selectLikeRemoveBrandServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        if(!Objects.equals(brandName, ""))
        {
            brandName = "%"+brandName+"%";
        }
        if(!Objects.equals(companyName, ""))
        {
            companyName = "%"+companyName+"%";
        }
        //模糊查询
        List<Brand> removeBrand = brandService.selectLikeRemoveBrandService(brandName,companyName);
        if(removeBrand != null)
        {
            resp.getWriter().write(JSON.toJSONString(removeBrand));
        }
        else
        {
            resp.getWriter().write("NULL");
        }
    }
    //按照条件准确查询商品
    public void selectConfirmBrandServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset = utf-8");
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        Brand brand = brandService.selectConfirmBrand(brandName , companyName);
        if(brand != null)
        {
            resp.getWriter().write(JSON.toJSONString(brand));
        }
        else
        {
            resp.getWriter().write("null");
        }
    }
}
