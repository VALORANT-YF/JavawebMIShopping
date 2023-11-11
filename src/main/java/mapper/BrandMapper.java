package mapper;

import com.zyf.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper
{
    //查询商品除主键所有信息
    // 分页查询
    List<Brand> selectAllBrand();

    //新增商品
    int addBrand(Brand brand);

    //批量删除 动态SQL
    int updateByIds(@Param("ids") int[] ids);

    //根据ids[] 查询出对应的商品名称
    List<Brand> selectByIds(@Param("ids") int[] ids);
    //根据查询出的商品名称删除对应的图片表的信息
    int updateImagesByBrandNames(@Param("brandNames") String[] brandNames);
    //分页条件查询
    List<Brand> selectByPageAndCondition(@Param("begin") int begin , @Param("size") int size , @Param("brand") Brand brand);

    //查询符合条件的记录数
    int selectCountByCodition(Brand brand);

    //根据id删除数据
    int updateById(int id);

    //根据id查询对应的一条商品的信息
    Brand selectBrandById(int id);

    //根据id修改商品信息和更改后的商品信息去修改
    int updateBrand(@Param("id") int id , @Param("brand") Brand brand);

    //查询商品名称
    String selectBrandName(@Param("brandName") String brandName);



    //根据商品名称修改商品图片路径
    int updateBrandImageUrl(@Param("brandName") String brandName , @Param("imageUrl") String imageUrl);

    //查询图片路径以及商品名称
    Brand selectBrandImages(@Param("imageUrl") String imageUrl);

    //查询图片路径是否重复
    String selectImageUrl(@Param("imageUrl") String imageUrl , @Param("id") int id);

    //删除图片路径
    int updateImageUrl(@Param("brandName") String brandName);

    //修改图片路径表中的图片名称
    int updateImageName(@Param("id") int id , @Param("newBrandName") String newBrandName , @Param("imageUrl") String imageUrl);

    //查询单个商品名称
    String selectBrandNameById(@Param("id") int id);

    //根据ids查询商品图片
    List<Brand> selectImgByIds(@Param("ids") int[] ids);

    //根据单个id查询商品图片
    List<Brand> selectImgById(@Param("id") int id);

    //根据index.html页面的搜索框查询商品名称 以及对应的公司名称
    List<Brand> selectBrandBySearch(String brandName);

    //根据商品名称 查询商品单价
    int selectPriceByBrandName(String brandName);

    //根据id 查询商品图片
    String selectImgUrlById(int id);

    Brand selectRepeat(@Param("brandName") String brandName , @Param("companyName") String companyName , @Param("id") int id);

    //查询出已经被删除的商品
    List<Brand> selectRemoveBrand();

    //点击还原按钮 还原被删除的商品
    void updateRemoveBrand(int id);

    //根据brandName 和 companyName 模糊查询 被删除的商品
    List<Brand> selectLikeRemoveBrand(@Param("brandName") String brandName , @Param("companyName") String companyName);

    //根据brandName 和 companyName 准确查询出符合条件的未被删除的商品
    Brand selectConfirmBrand(@Param("brandName") String brandName , @Param("companyName") String companyName);
}
