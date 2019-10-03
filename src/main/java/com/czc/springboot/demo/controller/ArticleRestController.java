package com.czc.springboot.demo.controller;

import com.czc.springboot.demo.common.AjaxResponse;
import com.czc.springboot.demo.model.Article;
import com.czc.springboot.demo.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author czc
 * @date 2019/9/27 22:23
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class ArticleRestController {

    @Resource
    ArticleRestService articleRestService;

    @RequestMapping(value = "/article", method = POST, produces = "application/json")
    public AjaxResponse saveArticle(@RequestBody Article article) {

        String save = articleRestService.save(article);
        log.info("articleRestService.saveArticle：{} ",save);
        log.info("saveArticle：{} ",article);
        return  AjaxResponse.success(article);
    }

    @RequestMapping(value = "/article/{id}", method = DELETE, produces = "application/json")
    public AjaxResponse deleteArticle(@PathVariable Long id) {

        log.info("deleteArticle：{}",id);
        return AjaxResponse.success(id);
    }

    @RequestMapping(value = "/article/{id}", method = PUT, produces = "application/json")
    public AjaxResponse updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setId(id);

        log.info("updateArticle：{}",article);
        return AjaxResponse.success(article);
    }

    @RequestMapping(value = "/article/{id}", method = GET, produces = "application/json")
    public AjaxResponse getArticle(@PathVariable Long id) {

        Article article1 = Article.builder().id(1L).author("zimug").content("spring boot 2.深入浅出").createTime(new Date()).title("t1").build();
        return AjaxResponse.success(article1);
    }
}