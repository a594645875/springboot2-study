package com.czc.springboot.demo.service;

import com.czc.springboot.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author czc
 * @date 2019/9/28 17:09
 */
@Slf4j
@Service
public class ArticleRestService {

    public String save(Article article) {
        log.info("saveArticle：{}",article);
        return "save method";
    }
}
