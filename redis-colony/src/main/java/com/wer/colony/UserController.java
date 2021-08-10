package com.wer.colony;

import com.wer.colony.model.ShopUser;
import com.wer.colony.service.UserServiuceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiuceImpl userServiuce;

    @ResponseBody
    @RequestMapping("")
    public ShopUser  getuser(@RequestParam("userId") Long userId){
        return userServiuce.redisStringType(userId);
    }
}
