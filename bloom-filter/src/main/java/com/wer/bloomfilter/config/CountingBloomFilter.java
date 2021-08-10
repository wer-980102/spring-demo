package com.wer.bloomfilter.config;

import com.wer.bloomfilter.filter.H3HashFunction;
import com.wer.bloomfilter.filter.HashFunctions;


public class CountingBloomFilter {

    public static int size;           //槽数量
    public static int currentNum;     //当前数量
    public static final byte[] bitVector = new byte[31];   //比特向量
    public static final int H3_HASH_NUM = 6;   //H3哈希函数的个数
    public static H3HashFunction h3HashFunction = new H3HashFunction(H3_HASH_NUM);


    public static boolean contains(String key){
        int h3_hash_code = HashFunctions.rotatingHash(key);
        for(int i = 0;i<H3_HASH_NUM;i++){
            int h3_result = h3HashFunction.h3Hash(32,h3_hash_code,i);
            int index = h3_result % size;
            if(bitVector[index] == 0){
                return false;
            }
        }
        return true;
    }

    public static void add(String key){
        int h3_hash_code = HashFunctions.rotatingHash(key);
        for(int i = 0;i<H3_HASH_NUM;i++){
            int h3_result = h3HashFunction.h3Hash(32,h3_hash_code,i);
            int index = h3_result % size;
            assert index < Byte.MAX_VALUE;
            bitVector[index]++;
        }
        currentNum ++;
        System.out.println("增加key成功！");
    }

    public static void remove(String key){
        int h3_hash_code = HashFunctions.rotatingHash(key);
        for(int i = 0;i<H3_HASH_NUM;i++){
            int h3_result = h3HashFunction.h3Hash(32,h3_hash_code,i);
            int index = h3_result % size;
            assert index < Byte.MAX_VALUE;
            bitVector[index]--;
        }
        currentNum --;
        System.out.println("删除key成功！");
    }

}
