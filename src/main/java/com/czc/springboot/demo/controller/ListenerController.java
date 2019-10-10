package com.czc.springboot.demo.controller;

import com.czc.springboot.demo.config.cuslistener.MyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author czc
 * @date 2019/10/10 15:27
 */
@RestController
public class ListenerController {

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("/publish")
    public String publish() {
        applicationContext.publishEvent(new MyEvent("listener controller publishing"));
        return "ok";
    }
}
