package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Resource
    AdminMapper adminMapper;

    public void add(Admin admin) {
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if(dbAdmin != null) {
            throw new CustomException("duplicate username!!!!!!!!!!!!!");
        }
        //default password
        if (StrUtil.isBlank(admin.getUsername())) {
            admin.setPassword("admin");
        }
        adminMapper.insert(admin);
    }

    public String admin(String name){
        if(name.equals("admin")){
            return "admin";
        }else {
            throw new CustomException("!!!!!CUSTOM ERROR!!!!");
        }
    }

    public List<Admin> selectAll(Admin admin){
        return adminMapper.selectAll(admin);
    }

    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }


    public void update(Admin admin) {
        adminMapper.updateByid(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    public void deleteBatch(List<Admin> list) {
        for (Admin admin : list) {
            this.deleteById(admin.getId());
        }
    }

    public Admin login(Admin admin) {
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if(dbAdmin == null) {
            throw new CustomException("账号不存在");
        }
        if(!dbAdmin.getPassword().equals(admin.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        return dbAdmin;
    }
}
