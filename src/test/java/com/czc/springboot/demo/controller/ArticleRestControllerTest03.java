package com.czc.springboot.demo.controller;

import com.czc.springboot.demo.model.Article;
import com.czc.springboot.demo.service.ArticleRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 使用Mockito编码完成接口测试
 * 轻量级装配
 * WebMvcTest只装配controller层的类，WebMvcTest(ArticleRestController.class)，之装配ArticleRestController
 * @author czc
 * @date 2019/9/28 16:34
 */
@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ArticleRestController.class)
public class ArticleRestControllerTest03 {

    @Resource
    private MockMvc mockMvc;

    /**
     * 伪造一个Service
     */
    @MockBean
    ArticleRestService articleRestService;

    @Test
    public void saveArticle() throws Exception {
        String article = "{\"id\":1,\"author\":\"zimug\",\"title\":\"手摸手教你开发spring boot\",\"content\":\"c\",\"createTime\":\"\",\"reader\":[{\"name\":\"zimug\",\"age\":18},{\"name\":\"kobe\",\"age\":37}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        Article articleObj = objectMapper.readValue(article, Article.class);

        //打桩
        when(articleRestService.saveArticle(articleObj)).thenReturn(null);

        /**
         * 在测试调用save方法saveResult为ok,但在ArticleRestController中调用的saveResult方法返回为null
         * 但是讲义中两者都为ok,不知道bug在哪?
         */
        Article saveResult = articleRestService.saveArticle(articleObj);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/rest/article")
                .contentType("application/json").content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("zimug"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(18))
                .andDo(print())
                .andReturn();

        log.info(result.getResponse().getContentAsString());

//        //模拟GET请求：
//        String userId = "";
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", userId));
//
//        //模拟Post请求：
//        List<Object> parameters = new ArrayList<>();
//        mockMvc.perform(MockMvcRequestBuilders.post("uri", parameters));
//
//        //模拟文件上传：
//        mockMvc.perform(MockMvcRequestBuilders.multipart("uri").file("fileName", "file".getBytes("UTF-8")));
//
//        //模拟session和cookie：
//        mockMvc.perform(MockMvcRequestBuilders.get("uri").sessionAttr("name", "value"));
//        mockMvc.perform(MockMvcRequestBuilders.get("uri").cookie(new Cookie("name", "value")));
//
//        //设置HTTP Header：
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("uri", parameters)
//                .contentType("application/x-www-form-urlencoded")
//                .accept("application/json")
//                .header("", ""));

    }
}