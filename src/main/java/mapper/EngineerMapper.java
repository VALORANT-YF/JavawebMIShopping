package mapper;

import com.zyf.pojo.Engineer;
import com.zyf.pojo.EngineerInformation;
import org.apache.ibatis.annotations.Param;

public interface EngineerMapper
{
    //查询数据库中是否有工程师的数据
    Engineer selectEngineerInformation(Engineer engineer);
    //查看工程师的工号
    String selectWorkNumber(String workNumber);
    //查看工程师的姓名
    String selectEngineerName(int id);
    //根据电话号码 查看工程师信息的主键
    int selectId(String telephone);

    //根据id 修改工程师的密码
    int updateEngineerPassword(@Param("password") String password , @Param("id") int id);

    //根据id查询出工程师的全部信息
    EngineerInformation selectAllEngineer(@Param("id") int id);

    //通过部门编号查询出工程师所属部门
    String selectEngineerDepartment(@Param("department") String department);

    //通过id修改电话号码
    int updateEngineerTelephone(@Param("telephone") String telephone,@Param("id") int id);
}
