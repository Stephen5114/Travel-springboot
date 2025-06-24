package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    List<User> selectAll(User user);

    void insert(User user);

    @Select("SELECT* FROM user where username = #{username}")
    User selectByUsername(String username);

    void updateByid(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteById(Integer id);
}
