package com.czc.springboot.demo.config.cuslistener;

import org.springframework.context.ApplicationEvent;

/**
 * @author czc
 * @date 2019/10/10 14:53
 */
@SuppressWarnings("serial")
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
