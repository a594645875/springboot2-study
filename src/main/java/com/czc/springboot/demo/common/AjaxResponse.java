package com.czc.springboot.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author czc
 * @date 2019/9/27 22:24
 */
@Getter
@Setter
public class AjaxResponse {

    /**
     * 请求是否处理成功
     */
    private boolean isok;

    /**
     * 请求响应状态码（200、400、500）
     */
    private int code;

    /**
     * 请求结果描述信息
     */
    private String message;

    /**
     * 请求结果数据
     */
    private Object data;

    private AjaxResponse() {

    }

    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }

}