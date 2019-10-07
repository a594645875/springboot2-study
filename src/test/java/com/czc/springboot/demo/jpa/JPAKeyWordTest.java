package com.czc.springboot.demo.jpa;

import com.czc.springboot.demo.jpa.testdb1.Article;
import com.czc.springboot.demo.jpa.testdb1.ArticleRepository;
import com.czc.springboot.demo.jpa.testdb2.Message;
import com.czc.springboot.demo.jpa.testdb2.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPAKeyWordTest {

    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private MessageRepository messageRepository;
    
    @Test
    public void userTest() {
        Article article = articleRepository.findByAuthor("zimug");
        System.out.println(article);
    }

    @Test
    public void messageTest() {
        Article article = articleRepository.findByAuthor("zimug");
        System.out.println(article);
        Message message = new Message();
        message.setName("123");
        message.setContent("abc");
        messageRepository.save(message);
    }

}