package com.blctek.userserver.response;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/01/16:33
 * @Description: 自定义响应结构：
 *               status：成功状态（true：成功，false：失败，默认为true）
 *               msg：返回信息
 *               data：返回数据
 *               timeStamp：返回时间戳
 */
public class ResultResponse implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态（true为成功，false为失败）
     */
    private boolean status;

    /**
     * 响应的消息
     */
    private String msg;

    /**
     * 响应的具体数据对象
     */
    private Object data;

    /**
     * 响应的时间戳
     */
    private Long timeStamp;

    public ResultResponse() {
        //status默认为true
        this.status = true;
        //data默认为null
        this.data = null;
        //timeStamp
        this.timeStamp = System.currentTimeMillis();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
