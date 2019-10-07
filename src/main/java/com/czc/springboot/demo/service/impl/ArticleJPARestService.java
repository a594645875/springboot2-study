package com.czc.springboot.demo.service.impl;

import com.czc.springboot.demo.jpa.testdb1.ArticleRepository;
import com.czc.springboot.demo.jpa.testdb1.Article;
import com.czc.springboot.demo.model.ArticleVO;
import com.czc.springboot.demo.service.ArticleRestService;
import com.czc.springboot.demo.utils.DozerUtils;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleJPARestService implements ArticleRestService {

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private Mapper dozerMapper;

    @Override
    public ArticleVO saveArticle(ArticleVO article) {

        Article articlePO = dozerMapper.map(article,Article.class);
        articleRepository.save(articlePO);

        return  article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void updateArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article,Article.class);
        articleRepository.save(articlePO);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return dozerMapper.map(article.get(),ArticleVO.class);
    }

    @Override
    public List<ArticleVO> getAll() {
        List<Article> articleLis = articleRepository.findAll();

        return DozerUtils.mapList(articleLis,ArticleVO.class);

    }
}