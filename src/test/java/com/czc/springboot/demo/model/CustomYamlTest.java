package com.czc.springboot.demo.model;

import com.czc.springboot.demo.model.yaml.Family;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author czc
 * @date 2019/10/2 22:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomYamlTest {

    @Autowired
    Family family;

    @Value("${sql.password}")
    private String password;

//    @Test
//    public void hello(){
//        System.out.println(family.toString());
//    }

    @Test
    public void pass() {
        System.out.println(password);
    }
}