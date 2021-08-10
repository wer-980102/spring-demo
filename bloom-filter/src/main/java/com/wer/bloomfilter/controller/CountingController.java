package com.wer.bloomfilter.controller;

import com.wer.bloomfilter.config.CountingBloomFilter;
import com.wer.bloomfilter.config.Filter;
import com.wer.bloomfilter.dao.ShopUserMapper;
import com.wer.bloomfilter.model.ShopUser;
import com.wer.bloomfilter.service.HashKeyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountingController {
    private static final Logger logger = LoggerFactory.getLogger(Filter.class);

    @Autowired
    private ShopUserMapper shopUserMapper;
    @Autowired
    private HashKeyServiceImpl hashKeyService;

    @RequestMapping("/getCounting")
    public String getCounting(@RequestParam("name") String name){
        boolean contains = CountingBloomFilter.contains(name);
        if(contains){
            return "数据库有该数据！";
        }else{
            return "该数据已过滤！";
        }
    }

}
