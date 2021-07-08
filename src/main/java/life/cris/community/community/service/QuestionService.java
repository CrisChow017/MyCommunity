package life.cris.community.community.service;

import life.cris.community.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size) {
        //分页功能
        //获取question的总数
        Integer totalCount = questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        //当前总页数为总question条数对显示页面数向上整除
        int totalPage = (totalCount + size - 1) / size;
        //越界处理
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        //该页的起始偏移量
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
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
        //paginationDTO对象中的questionList需要赋值
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list_2(Integer userId, Integer page, Integer size) {
        //分页功能
        //获取question的总数
        Integer totalCount = questionMapper.countByUserId(userId);
        PaginationDTO paginationDTO = new PaginationDTO();

        //当前总页数为总question条数对显示页面数向上整除
        int totalPage = (totalCount + size - 1) / size;
        //越界处理
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        //该页的起始偏移量
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list_2(userId, offset, size);
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
        //paginationDTO对象中的questionList需要赋值
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //create
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {
            //update
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
