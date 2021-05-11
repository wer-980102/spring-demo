package com.wer.importInfo.controller;

import com.alibaba.fastjson.JSON;
import com.wer.importInfo.model.BusClick;
import com.wer.importInfo.utils.ExcelUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/excel")
@Api(value = "excel导入导出", tags = "excel导入导出", description = "excel导入导出")
public class ExcelController {


    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
    public void readExcel(MultipartFile file){

        long t1 = System.currentTimeMillis();
        List<BusClick> list = ExcelUtils.readExcel("", BusClick.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        list.forEach(
                b -> System.out.println(JSON.toJSONString(b))
        );
    }
}
