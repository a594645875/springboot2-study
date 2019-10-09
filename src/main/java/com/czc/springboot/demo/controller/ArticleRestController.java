package com.czc.springboot.demo.controller;

import com.czc.springboot.demo.common.AjaxResponse;
import com.czc.springboot.demo.model.Article;
import com.czc.springboot.demo.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author czc
 * @date 2019/9/27 22:23
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class ArticleRestController {

    @Autowired
    private MongoRepository<Article,String> mongoRepository;

    @RequestMapping(value = "/article", method = POST, produces = "application/json")
    public AjaxResponse saveArticle(@RequestBody Article article) {
        mongoRepository.save(article);
        log.info("saveArticle：{} ",article);
        return  AjaxResponse.success(article);
    }

    @RequestMapping(value = "/article/{id}", method = DELETE, produces = "application/json")
    public AjaxResponse deleteArticle(@PathVariable String id) {
        mongoRepository.deleteById(id);
        log.info("deleteArticle：{}",id);
        return AjaxResponse.success(id);
    }

    @RequestMapping(value = "/article/{id}", method = PUT, produces = "application/json")
    public AjaxResponse updateArticle(@PathVariable String id, @RequestBody Article article) {
        article.setId(id);
        mongoRepository.save(article);
        log.info("updateArticle：{}",article);
        return AjaxResponse.success(article);
    }

    @RequestMapping(value = "/article/{id}", method = GET, produces = "application/json")
    public AjaxResponse getArticle(@PathVariable String id) {
        Optional<Article> byId = mongoRepository.findById(id);
        return AjaxResponse.success(byId.get());
    }

    @RequestMapping(value = "/article", method = GET, produces = "application/json")
    public AjaxResponse getAll() {
        List<Article> all = mongoRepository.findAll();
        return AjaxResponse.success(all);
    }
}