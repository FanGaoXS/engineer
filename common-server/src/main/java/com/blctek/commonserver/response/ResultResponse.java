package com.blctek.commonserver.response;


import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/01/16:33
 * @Description: 自定义响应结构：
 *               message：返回信息
 *               data：返回数据
 *               timeStamp：返回时间戳
 */
public class ResultResponse implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自定义响应状态码
     */
    private Integer code;

    /**
     * 响应的消息
     */
    private String message;

    /**
     * 响应的具体数据对象
     */
    private Object data;

    /**
     * 响应的时间戳
     */
    private Long timeStamp;

    public ResultResponse() {
        //status默认为2000
        this.code = 20000;
        //data默认为null
        this.data = null;
        //timeStamp
        this.timeStamp = System.currentTimeMillis();
    }

    public ResultResponse(Integer code, String message, Object data, Long timeStamp) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
