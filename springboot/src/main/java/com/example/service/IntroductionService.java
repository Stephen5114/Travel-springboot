package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Category;
import com.example.entity.Introduction;
import com.example.exception.CustomException;
import com.example.mapper.CategoryMapper;
import com.example.mapper.IntroductionMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntroductionService {
    @Resource
    IntroductionMapper introductionMapper;
    @Resource
    CategoryMapper categoryMapper;

    public void add(Introduction introduction) {
        Account currentUser = TokenUtils.getCurrentUser();
        introduction.setUserId(currentUser.getId());
        introduction.setTime(DateUtil.now());
        introductionMapper.insert(introduction);
    }

    public String introduction(String name){
        if(name.equals("introduction")){
            return "introduction";
        }else {
            throw new CustomException("!!!!!CUSTOM ERROR!!!!");
        }
    }

    public List<Introduction> selectAll(Introduction introduction){
        return introductionMapper.selectAll(introduction);
    }

    public PageInfo<Introduction> selectPage(Integer pageNum, Integer pageSize, Introduction introduction) {
        Account cueetntUser = TokenUtils.getCurrentUser();
        if ("USER".equals(cueetntUser.getRole())){
            introduction.setUserId(cueetntUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Introduction> list = introductionMapper.selectAll(introduction);
        for (Introduction dbIntroduction : list) {
            Integer categoryId = dbIntroduction.getCategoryId();
            Category category =  categoryMapper.selectById(categoryId);
            if (ObjectUtil.isNotEmpty(category)) {
                dbIntroduction.setCategoryTitle(category.getTitle());
            }
        }
        return PageInfo.of(list);
    }


    public void update(Introduction introduction) {
        introductionMapper.updateByid(introduction);
    }

    public void deleteById(Integer id) {
        introductionMapper.deleteById(id);
    }


}
