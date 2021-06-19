package life.cris.community.community.mapper;

import life.cris.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (name, account_id, token, gmt_create, gmt_modified) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);
}
