package com.czc.springboot.demo.dao;

import com.czc.springboot.demo.model.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleJDBCDAO {

    @Resource
    private JdbcTemplate primaryJdbcTemplate;

    //保存文章
    public void save(Article article,JdbcTemplate paramJdbcTemplate) {
        if(paramJdbcTemplate == null){
            paramJdbcTemplate= primaryJdbcTemplate;
        }
        //primaryJdbcTemplate.update适合于insert 、update和delete操作；
        paramJdbcTemplate.update("INSERT INTO article(author, title,content,create_time) values(?, ?, ?, ?)",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime());

    }

    //删除文章
    public void deleteById(Long id) {
        //primaryJdbcTemplate.update适合于insert 、update和delete操作；
        primaryJdbcTemplate.update("DELETE FROM article WHERE id = ?",new Object[]{id});

    }

    //更新文章
    public void updateById(Article article) {
        //primaryJdbcTemplate.update适合于insert 、update和delete操作；
        primaryJdbcTemplate.update("UPDATE article SET author = ?, title = ? ,content = ?,create_time = ? WHERE id = ?",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime(),
                article.getId());

    }

    //根据id查找文章
    public Article findById(Long id) {
        //queryForObject用于查询单条记录返回结果
        return (Article) primaryJdbcTemplate.queryForObject("SELECT * FROM article WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper(Article.class));
    }

    //查询所有
    public List<Article> findAll(){
        //query用于查询结果列表
        return (List<Article>) primaryJdbcTemplate.query("SELECT * FROM article ",  new BeanPropertyRowMapper(Article.class));
    }


}
