package com.czc.springboot.demo.controller;

import com.czc.springboot.demo.exception.AjaxResponse;
import com.czc.springboot.demo.exception.ModelView;
import com.czc.springboot.demo.model.ArticleVO;
import com.czc.springboot.demo.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

/**
 * @author czc
 * @date 2019/10/10 17:14
 */
//@Controller
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

//    @ModelView
//    @GetMapping("/freemarker")
//    public String index(Model model) {
//        //产生自定义异常
//        exceptionService.userBizError(-1);
//        return "fremarkertemp.html";
//    }
}
