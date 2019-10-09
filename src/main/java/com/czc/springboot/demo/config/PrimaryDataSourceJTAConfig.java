package com.czc.springboot.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 带多数据源事务管理的数据源配置
 */
@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@MapperScan(basePackages = "com.czc.springboot.demo.generator.db1",
        sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class PrimaryDataSourceJTAConfig {

    @Bean("primaryDataSource")
    @ConfigurationProperties(prefix = "primarydb")
    public DataSource primaryDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean("primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //因为Mapper和Mapper.xml我放在同一个文件夹所以不用设资源路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:generator/*.xml"));
        bean.setTypeAliasesPackage("com.czc.springboot.demo.generator.db1");  //这里需要修改为你的扫描类路径
        return bean.getObject();
    }

    @Bean("primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}