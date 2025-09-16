package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Category;
import com.example.exception.CustomException;
import com.example.mapper.CategoryMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    public void add(Category category) {
        Account currentUser = TokenUtils.getCurrentUser();
        if ("USER".equals(currentUser.getRole())){
            throw new CustomException("You do not have permission","500");
        }
        categoryMapper.insert(category);
    }

    public String category(String name){
        if(name.equals("category")){
            return "category";
        }else {
            throw new CustomException("!!!!!CUSTOM ERROR!!!!");
        }
    }

    public List<Category> selectAll(Category category){
        return categoryMapper.selectAll(category);
    }

    public PageInfo<Category> selectPage(Integer pageNum, Integer pageSize, Category category) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> list = categoryMapper.selectAll(category);
        return PageInfo.of(list);
    }


    public void update(Category category) {
        Account currentUser = TokenUtils.getCurrentUser();
        if ("USER".equals(currentUser.getRole())){
            throw new CustomException("You do not have permission","500");
        }
        categoryMapper.updateByid(category);
    }

    public void deleteById(Integer id) {
        Account currentUser = TokenUtils.getCurrentUser();
        if ("USER".equals(currentUser.getRole())){
            throw new CustomException("You do not have permission","500");
        }
        categoryMapper.deleteById(id);
    }


}
