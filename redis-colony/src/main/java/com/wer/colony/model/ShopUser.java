package com.wer.colony.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
/*@Builder
@AllArgsConstructor
@NoArgsConstructor*/
public class ShopUser {


    private Long userId;

    private String userName;

    private String userPassword;

    private String userMobile;

    private Date userRegTime;

    private Long userMoney;


    private String list;
}
