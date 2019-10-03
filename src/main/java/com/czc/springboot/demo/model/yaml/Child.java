package com.czc.springboot.demo.model.yaml;

import lombok.Data;

import java.util.List;

/**
 * @author czc
 * @date 2019/10/2 21:55
 */
@Data
public class Child {

    private String name;
    private int age;
    private List<Friend> friends;
}
