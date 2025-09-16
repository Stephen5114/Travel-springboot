package com.example.controller;

import com.example.common.Result;
import com.example.entity.Category;
import com.example.entity.Introduction;
import com.example.entity.User;
import com.example.service.CategoryService;
import com.example.service.IntroductionService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    public CategoryService categoryService;
    @Resource
    public IntroductionService introductionService;
    @Resource
    public UserService userService;

    @GetMapping("/pie")
    public Result pie() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Category> categories = categoryService.selectAll(new Category());
        List<Introduction> introductions = introductionService.selectAll(new Introduction());
        for (Category category : categories) {
            long count = introductions.stream().filter(x -> category.getId().equals(x.getCategoryId())).count();
            Map<String, Object> map = new HashMap<>();
            map.put("name", category.getTitle());
            map.put("value", count);
            list.add(map);
        }
        return Result.success(list);
    }

    @GetMapping("/bar")
    public Result bar() {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> xList = new ArrayList<>();
        List<Long> yList = new ArrayList<>();

        List<User> users = userService.selectAll(new User());
        List<Introduction> introductions = introductionService.selectAll(new Introduction());
        for (User user : users) {
            long count = introductions.stream()
                            .filter(x -> user.getId().equals(x.getUserId()))
                                    .count();
            xList.add(user.getName());
            yList.add(count);
        }

        resultMap.put("xAxis", xList);
        resultMap.put("yAxis", yList);
        return Result.success(resultMap);
    }


}
