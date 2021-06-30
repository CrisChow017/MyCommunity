package life.cris.community.community.mapper;

import life.cris.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO QUESTION (id,title,description,gmt_create,gmt_modified,creator) VALUES (#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator})")
    void create(Question question);
}
