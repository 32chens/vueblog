package com.chenlf.vueblog.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 同意结果封装类
 * @author ChenLF
 * @date 2022/02/19 20:36
 **/
@Data
public class Result implements Serializable {
    private int code;   //200正常，400异常
    private String msg;
    private Object data;

    public static Result success(Object data){
        Result ret = new Result();
        ret.setCode(200);
        ret.setMsg("操作成功");
        ret.setData(data);
        return ret;
    }

    public static Result success(int code,String msg,Object data){
        Result ret = new Result();
        ret.setCode(code);
        ret.setMsg(msg);
        ret.setData(data);
        return ret;
    }

    public static Result fail(String msg){
        return fail(400,msg,null);
    }

    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }

    public static Result fail(int code,String msg,Object data){
        Result ret = new Result();
        ret.setCode(code);
        ret.setMsg(msg);
        ret.setData(data);
        return ret;
    }
}
