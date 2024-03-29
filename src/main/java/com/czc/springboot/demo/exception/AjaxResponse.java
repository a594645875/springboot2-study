package com.czc.springboot.demo.exception;

import lombok.Data;

@Data
public class AjaxResponse {

    private boolean isok; // ajax请求是否成功
    private int code;        // http status code
    private String message; //请求失败的的提示信息。
    private Object data;     //请求成功时，需要响应给前端的数据

    private AjaxResponse() {

    } 
    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomException e) {

        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if(e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage() + ",系统出现异常，请联系管理员电话：13756108723进行处理!");
        }else{
            resultBean.setMessage("系统出现未知异常，请联系管理员电话：13756108723进行处理!");
        }
        return resultBean;
    }
    //请求成功时的响应数据封装，没有响应数据（比如删除修改成功）
    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }
    //请求成功时的响应数据封装，有响应数据（比如查询成功）
    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }

}