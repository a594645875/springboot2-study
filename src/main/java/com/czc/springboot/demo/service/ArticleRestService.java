package com.czc.springboot.demo.service;

import com.czc.springboot.demo.model.Article;

import java.util.List;

/**
 * @author czc
 * @date 2019/9/28 17:09
 */
public interface ArticleRestService {

    Article saveArticle(Article article);

    void deleteArticle(Long id);

    void updateArticle(Article article);

    Article getArticle(Long id);

    List<Article> getAll();
}
