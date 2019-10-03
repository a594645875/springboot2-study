package com.czc.springboot.demo.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * JsonIgnore 排除属性不做序列化与反序列化
 * JsonProperty 为属性换一个名
 * JsonPropertyOrder(value={"pname1","pname2"}) 改变json子属性的默认定义的顺序
 * JsonInclude(JsonInclude.Include.NON_NULL) 排除为空的元素不做序列化反序列化
 * JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 指定属性格式
 * @author czc
 * @date 2019/9/27 22:22
 */
@JsonPropertyOrder(value={"content","title"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    /**
     * 排除属性不做序列化与反序列化
     */
    @JsonIgnore
    private long id;

    /**
     * 属性换一个名
     */
    //@JsonProperty("auther")
    private String author;
    private String title;
    private String content;

    /**
     * JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     * 通常会对日期类型转换，进行全局配置，而不是在每一个java bean里面配置
     * spring:
     *     jackson:
     *         date-format: yyyy-MM-dd HH:mm:ss
     *         time-zone: GMT+8
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;
    private List<ReaderBean> reader;

    public static class ReaderBean {
        /**
         * name : zimug
         * age : 18
         */

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
