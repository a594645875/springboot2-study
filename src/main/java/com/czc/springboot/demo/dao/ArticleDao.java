package com.czc.springboot.demo.dao;

import com.czc.springboot.demo.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "article",path="articles")
public interface ArticleDao extends MongoRepository<Article,String> {
        //支持关键字查询，和JPA的用法一样
        Article findByAuthor(String author);
}