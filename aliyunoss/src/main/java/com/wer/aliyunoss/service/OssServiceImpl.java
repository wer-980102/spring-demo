package com.wer.aliyunoss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wer.aliyunoss.model.OssProperies;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Calendar;

@Service
public class OssServiceImpl implements OssService{
    /**
     * 第一种方式
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        //读取工具类的数据
        String endpoint = OssProperies.END_POINT;
        String accessKeyId = OssProperies.ACCESS_KEY_ID;
        String accessKeySecret = OssProperies.ACCESS_KEY_SECRET;
        String bucketName = OssProperies.BUCKET_NAME;


        //连接oss客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流
        InputStream inputStream = file.getInputStream();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.DAY_OF_MONTH);
        int date = c.get(Calendar.DATE);
        //自己匹配
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        //根据时间拼接url
        String url = year+"/"+month+"-"+date+"/"+file.getOriginalFilename();
        //上传
        ossClient.putObject(bucketName, url, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://"+bucketName+"."+endpoint+"/"+url;

    }

}
