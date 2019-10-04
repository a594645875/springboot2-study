package com.czc.springboot.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    /**
     * jta数据库配置
     */
    //jta数据源primarydb
    @Bean(initMethod="init", destroyMethod="close", name="primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "primarydb")
    public DataSource primaryDataSource() {
        //这里是关键，返回的是AtomikosDataSourceBean，所有的配置属性也都是注入到这个类里面
        return new AtomikosDataSourceBean();
    }

    //jta数据源secondarydb
    @Bean(initMethod="init", destroyMethod="close", name="secondaryDataSource")
    @ConfigurationProperties(prefix = "secondarydb")
    public DataSource secondaryDataSource()  {
        return new AtomikosDataSourceBean();
    }

    @Bean
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }

    @Bean
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        return new JdbcTemplate(secondaryDataSource);
    }

    /**
     * 原生的数据库配置
     */
//    @Primary
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.primary")
//    public DataSource primaryDataSource() {
//            return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name="primaryJdbcTemplate")
//    public JdbcTemplate primaryJdbcTemplate (
//        @Qualifier("primaryDataSource") DataSource dataSource ) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean(name="secondaryJdbcTemplate")
//    public JdbcTemplate secondaryJdbcTemplate(
//            @Qualifier("secondaryDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}