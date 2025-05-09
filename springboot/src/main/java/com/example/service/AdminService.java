package com.example.service;

import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public String admin(String name){
        if(name.equals("admin")){
            return "admin";
        }else {
            throw new CustomException("!!!!!CUSTOM ERROR!!!!");
        }
    }
}
