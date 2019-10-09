package com.czc.springboot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * article
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private Integer id;

    private String author;

    private String title;

    private String content;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}