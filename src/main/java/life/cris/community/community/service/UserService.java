package life.cris.community.community.service;

import life.cris.community.community.mapper.UserMapper;
import life.cris.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser = userMapper.getByAccountId(user.getAccountId());
        if (dbuser == null) {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setToken(user.getToken());
            dbuser.setName(user.getName());
            userMapper.update(dbuser);
        }
    }
}
