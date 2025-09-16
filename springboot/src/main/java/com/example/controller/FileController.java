package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import com.example.common.Result;
import com.example.exception.CustomException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * wang-editor编辑器文件上传接口
     */
    @PostMapping("/wang/upload")
    public Map<String, Object> wangEditorUpload(MultipartFile file) {
        String flag = System.currentTimeMillis() + "";
        String fileName = file.getOriginalFilename();
        try {
            String filePath = System.getProperty("user.dir") + "/springboot/files/";
            // 文件存储形式：时间戳-文件名
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
            System.out.println(fileName + "--上传成功");
            Thread.sleep(1L);
        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        String http = "http://localhost:9999/files/download/";
        Map<String, Object> resMap = new HashMap<>();
        // wangEditor上传图片成功后， 需要返回的参数
        resMap.put("errno", 0);
        resMap.put("data", CollUtil.newArrayList(Dict.create().set("url", http + flag + "-" + fileName)));
        return resMap;
    }
}
