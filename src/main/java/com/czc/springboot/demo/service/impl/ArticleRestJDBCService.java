package com.czc.springboot.demo.service.impl;

import com.czc.springboot.demo.dao.ArticleJDBCDAO;
import com.czc.springboot.demo.model.Article;
import com.czc.springboot.demo.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author czc
 * @date 2019/10/3 15:40
 */
@Slf4j
@Service
public class ArticleRestJDBCService implements ArticleRestService {

    @Resource
    private
    ArticleJDBCDAO articleJDBCDAO;
    @Resource
    private JdbcTemplate primaryJdbcTemplate;
    @Resource
    private JdbcTemplate secondaryJdbcTemplate;

    @Override
    @Transactional
    public Article saveArticle(Article article) {
        articleJDBCDAO.save(article,primaryJdbcTemplate);
        articleJDBCDAO.save(article,secondaryJdbcTemplate);
        int a = 2/0;
        return article;
    }

    @Override
    public void deleteArticle(Long id){
        articleJDBCDAO.deleteById(id);
    }

    @Override
    public void updateArticle(Article article){
        articleJDBCDAO.updateById(article);
    }

    @Override
    public Article getArticle(Long id){
        return articleJDBCDAO.findById(id);
    }

    @Override
    public List<Article> getAll(){
        return articleJDBCDAO.findAll();
    }
}