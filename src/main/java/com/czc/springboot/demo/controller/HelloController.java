package com.czc.springboot.demo.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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

    @RequestMapping(value="/uid",method = RequestMethod.GET)
    public @ResponseBody
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
