package com.czc.springboot.demo.generator.db2;

import com.czc.springboot.demo.model.Article;
import com.czc.springboot.demo.model.ArticleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper2 {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}