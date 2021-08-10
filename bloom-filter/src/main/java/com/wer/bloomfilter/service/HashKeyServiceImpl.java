package com.wer.bloomfilter.service;

import com.wer.bloomfilter.config.CountingBloomFilter;
import org.springframework.stereotype.Service;

@Service
public class HashKeyServiceImpl {


   public String getCounting(CountingBloomFilter filter,String name){
       boolean contains = filter.contains(name);
       if(contains){
           return "数据库有该数据！";
       }else{
           return "该数据已过滤！";
       }
   }
}
