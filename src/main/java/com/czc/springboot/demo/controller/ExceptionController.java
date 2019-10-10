package com.czc.springboot.demo.controller;

import com.czc.springboot.demo.exception.AjaxResponse;
import com.czc.springboot.demo.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author czc
 * @date 2019/10/10 17:14
 */
@RestController
public class ExceptionController {

    @Autowired
    private ExceptionService exceptionService;

    @GetMapping("/systemBizError")
    public AjaxResponse systemBizError() {
        exceptionService.systemBizError();
        return AjaxResponse.success();
    }

    @GetMapping("/userBizError/{num}")
    public AjaxResponse userBizError(@Valid @PathVariable @Max(10) int num) {
        exceptionService.userBizError(num);
        return AjaxResponse.success();
    }
}
