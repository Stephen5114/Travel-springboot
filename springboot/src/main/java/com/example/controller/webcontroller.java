package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class webcontroller {
    @Resource
    AdminService adminService;

    @GetMapping("/hello")
    public Result hello() {
        int a = 1/0;
        return Result.success("hello");
    }

    @GetMapping("/admin")
    public Result admin(String name){
        String admin = adminService.admin(name);
        return Result.success(admin);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Admin dbAdmin = adminService.login(admin);
        return Result.success(dbAdmin);
    }
}
