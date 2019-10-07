package com.czc.springboot.demo.service;

import com.czc.springboot.demo.model.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author czc
 * @date 2019/9/28 17:09
 */
public interface ArticleRestService {


    ArticleVO saveArticle(ArticleVO article);

    void deleteArticle(Long id);

    void updateArticle(ArticleVO article);

    ArticleVO getArticle(Long id);

    List<ArticleVO> getAll();
}
