package life.cris.community.community.service;

import life.cris.community.community.dto.QuestionDTO;
import life.cris.community.community.mapper.QuestionMapper;
import life.cris.community.community.mapper.UserMapper;
import life.cris.community.community.model.Question;
import life.cris.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            //通过user_id拿到user对象
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question 传入传输层的QuestionDTO
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
            BeanUtils.copyProperties(question, questionDTO); //将question对象属性拷贝至questionDTO上
        }
        return questionDTOList;
    }
}