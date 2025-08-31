package com.example.controller;

import cn.hutool.core.io.FileUtil;
import com.example.common.Result;
import com.example.exception.CustomException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {
    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = System.getProperty("user.dir") + "/springboot/files/";
        if (!FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);
        }
        byte[] bytes = file.getBytes();
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        //String encodeFilename = URLEncoder.encode(filename, "UTF-8");
        FileUtil.writeBytes(bytes, filePath + filename);
        return Result.success("http://localhost:9999/files/download/" + filename);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = System.getProperty("user.dir") + "/springboot/files/";
        String realPath = filePath + fileName;
        boolean exist = FileUtil.exist(realPath);
        if (!exist) {
            throw new CustomException("File Not Exist");
        }
        byte[] bytes = FileUtil.readBytes(realPath);
        ServletOutputStream os = response.getOutputStream();
        os.write(bytes);
        os.flush();
        os.close();
    }
}
