package com.czc.springboot.demo.service.impl;

import com.czc.springboot.demo.jpa.testdb1.ArticleRepository;
import com.czc.springboot.demo.jpa.testdb1.Article;
import com.czc.springboot.demo.jpa.testdb2.Message;
import com.czc.springboot.demo.jpa.testdb2.MessageRepository;
import com.czc.springboot.demo.model.ArticleVO;
import com.czc.springboot.demo.service.ArticleRestService;
import com.czc.springboot.demo.utils.DozerUtils;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleJPARestService implements ArticleRestService {

    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private Mapper dozerMapper;
    @Resource
    private MessageRepository messageRepository;

    @Override
    @Transactional
    public ArticleVO saveArticle(ArticleVO article) {

        Article articlePO = dozerMapper.map(article,Article.class);
        articleRepository.save(articlePO);
        Message message = new Message();
        message.setName("123");
        message.setContent("abc");
        messageRepository.save(message);
        //模拟代码出错
        //int a= 2/0;
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