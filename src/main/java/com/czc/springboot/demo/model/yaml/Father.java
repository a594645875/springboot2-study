package com.czc.springboot.demo.model.yaml;

/**
 * @author czc
 * @date 2019/10/2 21:55
 */

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class Father {
    private String name;
    @Max(100)
    @Min(18)
    private int age;
}
