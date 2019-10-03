package com.czc.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czc
 * @date 2019/9/27 22:14
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world! 热部署";
    }
}
