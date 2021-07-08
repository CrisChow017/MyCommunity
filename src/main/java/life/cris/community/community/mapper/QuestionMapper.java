package life.cris.community.community.mapper;

import life.cris.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO QUESTION (id,title,description,gmt_create,gmt_modified,creator) VALUES (#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator})")
    void create(Question question);

    @Select("SELECT * FROM QUESTION LIMIT #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM QUESTION")
    Integer count();

    @Select("SELECT * FROM QUESTION WHERE CREATOR=#{userId}LIMIT #{offset},#{size}")
    List<Question> list_2(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    @Select("SELECT COUNT(1) FROM QUESTION WHERE CREATOR=#{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    @Select("SELECT * FROM QUESTION WHERE id=#{id}")
    Question getById(@Param(value = "id")Integer id);

    @Update("UPDATE QUESTION SET title=#{title}, description=#{description}, gmt_modified=#{gmtModified}, tag=#{tag} where id=#{id}")
    void update(Question question);
}
