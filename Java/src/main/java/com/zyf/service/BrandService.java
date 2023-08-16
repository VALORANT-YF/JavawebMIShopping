package com.zyf.service;

import com.zyf.mapper.BrandMapper;
import com.zyf.pojo.Brand;
import com.zyf.pojo.PageBean;
import com.zyf.util.sqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService
{
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryUtils.getSqlSessionFactory();


    //查询所有
    public List<Brand> selectAllBrand()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAllBrand();
        sqlSession.close();
        return brands;
    }

    //添加商品
    public int addBrand(Brand brand)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        int num = brandMapper.addBrand(brand);//返回受影响的行数
        sqlSession.close();
        return num;
    }

    //批量删除商品
    public int deleteByIds(int[] ids)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        int num = brandMapper.updateByIds(ids);
        sqlSession.close();
        return num;
    }

    /**
     *
     * @param currentPage 当前页码
     * @param pageSize 每页记录的条数
     * @return
     */
    //条件查询
    public PageBean<Brand> selectByPageAndCondition(int currentPage , int pageSize, Brand brand)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //计算每页开始索引
        int begin = (currentPage - 1) * pageSize;//(当前页码-1)*每页显示条数 为每一页起始
        //查询条目数
        int size = pageSize;


        //处理对应的brand条件
        String brandName = brand.getBrandName();

        if(brandName != null && brandName.length() > 0)
        {
            brand.setBrandName("%"+brandName+"%");
//            System.out.println("商品名称: "+brand.getBrandName());
        }

        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length() > 0)
        {
            brand.setCompanyName("%"+companyName+"%");
//            System.out.println("企业名称:"+brand.getCompanyName());
        }
        //查询当前页数据
        List<Brand> rows = brandMapper.selectByPageAndCondition(begin,size,brand);


        //查询总记录数
        int sum = brandMapper.selectCountByCodition(brand);
        //
//        System.out.println("总记录数:"+sum);
        PageBean<Brand> pageBean = new PageBean<>();


        pageBean.setRows(rows);
        pageBean.setTotalCount(sum);
//        System.out.println("BrandServicePageBean: "+pageBean);
        sqlSession.close();
        return pageBean;
    }
    //根据id单独删除数据
    public int deleteById(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int num = brandMapper.updateById(id);

        sqlSession.close();
        return num;
    }

    //根据id查出对应商品的信息
    public Brand selectBrandById(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.selectBrandById(id);

        sqlSession.close();
        return brand;
    }

    //修改商品信息
    public int updateBrand(int id , Brand brand)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //返回受影响的行数
        int num = brandMapper.updateBrand(id,brand);

        sqlSession.close();
        return num;
    }

    //根据ids查询商品名称
    public List<Brand> selectByIds(int[] ids)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brandNames = brandMapper.selectByIds(ids);
        sqlSession.close();
        return brandNames;
    }
    //根据brandNames删除图片
    public int deleteImagesByBrandNames(String[] brandNames)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int num = brandMapper.updateImagesByBrandNames(brandNames);
        sqlSession.close();
        return num;
    }

    //查询商品名称
    public String selectBrandName(String brandName)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        String in_brandName = brandMapper.selectBrandName(brandName);
        sqlSession.close();
        return  in_brandName;
    }

    //修改商品图片路径
    public int updateBrandImageUrl(String brandName , String imageUrl)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int num = brandMapper.updateBrandImageUrl(brandName,imageUrl);
        sqlSession.close();
        return num;
    }
    //查看图片路径
    public Brand selectBrandImages(String imageUrl)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.selectBrandImages(imageUrl);
        sqlSession.close();
        return brand;
    }
    //查询图片路径是否有重复
    public String selectImageUrl(String imageUrl , int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        String imageUrl_check = brandMapper.selectImageUrl(imageUrl , id);

        sqlSession.close();
        return imageUrl_check;
    }
    //单个删除图片路径 逻辑删除
    public int deleteImageUrl(String brandName)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int num = brandMapper.updateImageUrl(brandName);
        sqlSession.close();
        return num;
    }
    //修改图片表中的商品名称
    public int updateImageName(int id , String newBrandName , String imageUrl)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        int num = brandMapper.updateImageName(id,newBrandName,imageUrl);
        sqlSession.close();
        return num;
    }

    //名称
    public String selectBrandNameById(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        String brandName = brandMapper.selectBrandNameById(id);
        sqlSession.close();
        return brandName;
    }
    //根据id查询商品图片
    public List<Brand> selectImgByIds(int[] ids)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brandImgs = brandMapper.selectImgByIds(ids);
        sqlSession.close();
        return brandImgs;
    }
    //根据id单个查询商品图片
    public List<Brand> selectImgById(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brandImg = brandMapper.selectImgById(id);
        sqlSession.close();
        return brandImg;
    }
    //联想搜索功能
    public List<Brand> selectBrandBySearchService(String brandName)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> list = brandMapper.selectBrandBySearch(brandName);
        sqlSession.close();
        return list;
    }
    //查询商品单价
    public int selectPriceService(String brandName)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        int price = brandMapper.selectPriceByBrandName(brandName);
        sqlSession.close();
        return price;
    }
    //查询商品图片
    public String selectImgUrlById(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        String imageUrl = brandMapper.selectImgUrlById(id);
        sqlSession.close();
        return imageUrl;
    }
    //查询是否有重复商品
    public Brand selectRepeatService(String brandName , String companyName , int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectRepeat(brandName,companyName,id);
        sqlSession.close();
        return brand;
    }
    //查询出已经被删除的商品
    public List<Brand> selectRemoveBrand()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> listBrand = brandMapper.selectRemoveBrand();
        sqlSession.close();
        return listBrand;
    }
    //还原已经被删除的商品
    public void updateRemoveBrandService(int id)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.updateRemoveBrand(id);
        sqlSession.close();
    }
    public List<Brand> selectLikeRemoveBrandService(String brandName , String companyName)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brand = brandMapper.selectLikeRemoveBrand(brandName,companyName);
        sqlSession.close();
        return brand;
    }
    public Brand selectConfirmBrand(String brandName , String companyName)
    {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectConfirmBrand(brandName , companyName);
        sqlSession.close();
        return brand;
    }
}
