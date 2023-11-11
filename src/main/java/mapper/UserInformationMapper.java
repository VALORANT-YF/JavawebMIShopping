package mapper;

import com.zyf.pojo.UserInformation;
import org.apache.ibatis.annotations.Param;



public interface UserInformationMapper
{
    //根据账号 向tb_information表中添加数据
    int updateUserInformation(@Param("UserInformation") UserInformation userInformation);

    //查询用户全部的信息
    UserInformation selectUserInformationAll(@Param("account") String account);

    //根据账号 删除个人信息
    int deleteUserInformation(@Param("account") String account);

    //根据账号 查询用户的昵称以及个头像
    UserInformation selectIndexByAccount(@Param("account") String account);
}
