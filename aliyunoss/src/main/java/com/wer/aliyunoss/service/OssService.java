package com.wer.aliyunoss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    /**
     * 第一种方式
     * @param file
     * @return
     * @throws Exception
     */
    String uploadFile (MultipartFile file) throws Exception;

}
