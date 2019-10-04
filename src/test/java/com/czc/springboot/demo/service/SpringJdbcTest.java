package com.czc.springboot.demo.service;

import com.czc.springboot.demo.dao.ArticleJDBCDAO;
import com.czc.springboot.demo.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author czc
 * @date 2019/10/3 16:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJdbcTest {

    @Resource
    private ArticleJDBCDAO articleJDBCDAO;
    @Resource
    private JdbcTemplate primaryJdbcTemplate;
    @Resource
    private JdbcTemplate secondaryJdbcTemplate;


    @Test
    public void testJdbc() {
        articleJDBCDAO.save(Article.builder().author("zimug").title("primaryJdbcTemplate").content("ceshi").createTime(new Date()).build(),
                primaryJdbcTemplate);
        articleJDBCDAO.save(Article.builder().author("zimug").title("secondaryJdbcTemplate").content("ceshi").createTime(new Date()).build(),
                secondaryJdbcTemplate);
    }


}
