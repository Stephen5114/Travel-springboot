package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public void add(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser != null) {
            throw new CustomException("duplicate username!!!!!!!!!!!!!");
        }
        //default password
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword("123");
        }
        if (StrUtil.isBlank(user.getName())) {
            user.setName(user.getUsername());
        }
        user.setRole("USER");
        userMapper.insert(user);
    }

    public String user(String name){
        if(name.equals("user")){
            return "user";
        }else {
            throw new CustomException("!!!!!CUSTOM ERROR!!!!");
        }
    }

    public List<User> selectAll(User user){
        return userMapper.selectAll(user);
    }

    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, User user) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }


    public void update(User user) {
        userMapper.updateByid(user);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void deleteBatch(List<User> list) {
        for (User user : list) {
            this.deleteById(user.getId());
        }
    }

    public User login(Account account) {
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if(dbUser == null) {
            throw new CustomException("账号不存在");
        }
        if(!dbUser.getPassword().equals(account.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        String token = TokenUtils.createToken(dbUser.getId() + "-" + "USER", dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public void register(User user) {
        this.add(user);
    }

    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    public void updatePassword(Account account) {
        if (!account.getNewPassword().equals(account.getNew2Password())) {
            throw new CustomException("The two passwords don't match", "500");
        }

        Account currentUser = TokenUtils.getCurrentUser();
        if (!account.getPassword().equals(currentUser.getPassword())) {
            throw new CustomException("The old password you entered is incorrect", "500");
        }

        User user = userMapper.selectById(currentUser.getId().toString());
        user.setPassword(account.getNewPassword());
        userMapper.updateByid(user);
    }
}
