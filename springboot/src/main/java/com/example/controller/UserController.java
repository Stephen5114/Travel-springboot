package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<User> list) {
        userService.deleteBatch(list);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        List<User> userList = userService.selectAll(user);
        return Result.success(userList);
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "20") Integer pageSize,
                             User user) {
        PageInfo<User> pageInfo = userService.selectPage(pageNum, pageSize, user);
        return Result.success(pageInfo);
    }

    @GetMapping("/export")
    public void exportData(User user, HttpServletResponse response) throws Exception {
        String ids = user.getIds();
        if (StrUtil.isNotBlank(ids)) {
            String[] idsArr = ids.split(",");
            user.setIdsArr(idsArr);
        }
        List<User> userList = userService.selectAll(user);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("username", "账号");
        writer.addHeaderAlias("name", "名称");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("email", "邮箱");
        writer.setOnlyAlias(true);
        writer.write(userList);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("管理员信息", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream os = response.getOutputStream();
        writer.flush(os);
        os.close();
        writer.close();
    }

    @PostMapping("/import")
    public Result importData(@RequestParam("file") MultipartFile file) throws Exception {
     InputStream inputStream = file.getInputStream();
     ExcelReader reader = ExcelUtil.getReader(inputStream);
     reader.addHeaderAlias("账号","username");
     reader.addHeaderAlias("名称", "name");
     reader.addHeaderAlias("电话", "phone");
     reader.addHeaderAlias("邮箱", "email");
     List<User> list = reader.readAll(User.class);
     for (User user : list) {
         userService.add(user);
     }
     return Result.success();
    }
}
