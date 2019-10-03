package com.czc.springboot.demo.model.yaml;

import com.czc.springboot.demo.config.MixPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author czc
 * @date 2019/10/2 21:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "family")
@PropertySource(value = {"classpath:family.yml"},factory = MixPropertySourceFactory.class)
@Validated
public class Family {

    //@Value("${family.family-name}")
    @NotNull
    private String familyName;
    private Father father;
    private Mother mother;
    private Child child;
}
