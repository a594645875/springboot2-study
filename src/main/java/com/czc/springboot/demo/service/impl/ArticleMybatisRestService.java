package com.czc.springboot.demo.service.impl;

import com.czc.springboot.demo.generator.ArticleMapper;
import com.czc.springboot.demo.model.Article;
import com.czc.springboot.demo.model.ArticleVO;
import com.czc.springboot.demo.service.ArticleRestService;
import com.czc.springboot.demo.utils.DozerUtils;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleMybatisRestService implements ArticleRestService {

    @Resource
    protected Mapper dozerMapper;

    @Resource
    private ArticleMapper articleMapper;


    @Override
    public ArticleVO saveArticle(ArticleVO article) {
        article.setCreateTime(new Date());
        Article articlePO = dozerMapper.map(article,Article.class);
        articleMapper.insert(articlePO);
        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleMapper.deleteByPrimaryKey(Math.toIntExact(id));
    }

    @Override
    public void updateArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article,Article.class);
        articleMapper.updateByPrimaryKeySelective(articlePO);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        return dozerMapper.map(articleMapper.selectByPrimaryKey(Math.toIntExact(id)),ArticleVO.class);
    }

    @Override
    public List<ArticleVO> getAll() {
        List<Article> articles = articleMapper.selectByExample(null);
        return DozerUtils.mapList(articles,ArticleVO.class);

    }
}