package com.wer.aliyunoss.controller;

import com.wer.aliyunoss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class OssController {

    @Autowired
    OssService ossServiceImpl;

    @PostMapping("/uploadimg")
    public String uploadOssFile(MultipartFile file) throws Exception{
        String url =  ossServiceImpl.uploadFile(file);
        return url;
    }


}
