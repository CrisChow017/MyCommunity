package life.cris.community.community.mapper;

import life.cris.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//用于将user信息放入数据库交互
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (name, account_id, token, gmt_create, gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
    //通过cookie中的token查找user信息
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
