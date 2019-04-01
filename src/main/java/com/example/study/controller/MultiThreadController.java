package com.example.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiThreadController {

    @RequestMapping("get")
    public String hello() {
        return "Hello";
    }
}
