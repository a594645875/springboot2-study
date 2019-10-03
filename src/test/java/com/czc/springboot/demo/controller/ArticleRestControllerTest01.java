package com.czc.springboot.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 使用Mockito编码完成接口测试
 * 不要在 Spring Boot 集成测试中使用 @Transactional
 * @author czc
 * @date 2019/9/28 16:34
 */
@Slf4j
@SpringBootTest
public class ArticleRestControllerTest01 {

    private MockMvc mockMvc;

    /**
     * 使用MockMvc装在ArticleRestController
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ArticleRestController.class).build();
    }

    @Test
    public void saveArticle() throws Exception {
        String article = "{\"id\":1,\"author\":\"zimug\",\"title\":\"手摸手教你开发spring boot\",\"content\":\"c\",\"createTime\":\"\",\"reader\":[{\"name\":\"zimug\",\"age\":18},{\"name\":\"kobe\",\"age\":37}]}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/rest/article")
                .contentType("application/json").content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("zimug"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(18))
                .andDo(print())
                .andReturn();

        log.info(result.getResponse().getContentAsString());
    }
}