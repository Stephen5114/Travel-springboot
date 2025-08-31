package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.utils.TokenUtils;
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
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword("admin");
        }
        admin.setRole("ADMIN");
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

    public Admin login(Account account) {
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if(dbAdmin == null) {
            throw new CustomException("账号不存在");
        }
        if(!dbAdmin.getPassword().equals(account.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        String token = TokenUtils.createToken(dbAdmin.getId() + "-" + "ADMIN", dbAdmin.getPassword());
        dbAdmin.setToken(token);
        return dbAdmin;
    }

    public Admin selectById(String id) {
        return adminMapper.selectById(id);
    }

    public void updatePassword(Account account) {
        if (!account.getNewPassword().equals(account.getNew2Password())) {
            throw new CustomException("The two passwords don't match", "500");
        }

        Account currentUser = TokenUtils.getCurrentUser();
        if (!account.getPassword().equals(currentUser.getPassword())) {
            throw new CustomException("The old password you entered is incorrect", "500");
        }

        Admin admin = adminMapper.selectById(currentUser.getId().toString());
        admin.setPassword(account.getNewPassword());
        adminMapper.updateByid(admin);
    }
}
