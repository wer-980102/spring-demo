package com.wer.dockerboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/docker")
    public String index() {
        return "Hello Docker!";
    }
}
