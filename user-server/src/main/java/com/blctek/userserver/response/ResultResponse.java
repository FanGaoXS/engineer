package com.blctek.userserver.response;

import lombok.AllArgsConstructor;
import lombok.Data;

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
@AllArgsConstructor
@Data
public class ResultResponse implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态（true为成功，false为失败）
     */
    private Boolean status;

    /**
     * 自定义响应状态码
     */
    private Integer code;

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
        //status默认为2000
        this.code = 20000;
        //data默认为null
        this.data = null;
        //timeStamp
        this.timeStamp = System.currentTimeMillis();
    }
}
