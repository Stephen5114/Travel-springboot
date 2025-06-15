package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Admin> list) {
        adminService.deleteBatch(list);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        List<Admin> adminList = adminService.selectAll(admin);
        return Result.success(adminList);
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "20") Integer pageSize,
                             Admin admin) {
        PageInfo<Admin> pageInfo = adminService.selectPage(pageNum, pageSize, admin);
        return Result.success(pageInfo);
    }

    @GetMapping("/export")
    public void exportData(Admin admin, HttpServletResponse response) throws Exception {
        String ids = admin.getIds();
        if (StrUtil.isNotBlank(ids)) {
            String[] idsArr = ids.split(",");
            admin.setIdsArr(idsArr);
        }
        List<Admin> adminList = adminService.selectAll(admin);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("username", "账号");
        writer.addHeaderAlias("name", "名称");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("email", "邮箱");
        writer.setOnlyAlias(true);
        writer.write(adminList);
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
     List<Admin> list = reader.readAll(Admin.class);
     for (Admin admin : list) {
         adminService.add(admin);
     }
     return Result.success();
    }
}
