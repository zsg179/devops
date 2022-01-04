package com.example.devops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: sg zhu
 * @create: 2021-11-30 16:05
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        System.out.println("收到请求");
        return "dd";
    }

    @GetMapping("/test2")
    public String test2() {
        System.out.println("收到请求");
        return "测试请求";
    }
}
