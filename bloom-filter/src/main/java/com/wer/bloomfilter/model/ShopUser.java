package com.wer.bloomfilter.model;

import com.wer.bloomfilter.config.CountingBloomFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopUser {
    private Long userId;

    private String userName;

    private String userPassword;

    private String userMobile;

    private Date userRegTime;

    private Long userMoney;


}
