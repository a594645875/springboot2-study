package com.czc.springboot.demo.config;

import com.czc.springboot.demo.config.cuslistener.MyEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 这种测试环境MyListener1不生效
 * 用Controller的方式测试则所有MyListener正常生效
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomListenerTest {

    @Resource
    private
    ApplicationContext applicationContext;

    @Test
    public void testEvent(){
        applicationContext.publishEvent(new MyEvent("测试事件."));
    }
}