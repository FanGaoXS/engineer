package com.blctek.commonserver.pojo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/04/15:44
 * @Description: 与数据库logger表交互的日志对象（与表头一一对应）
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Logger extends BasePojo {
    /**
     * 日志表的自增主键
     */
    private Integer id;

    /**
     * 该操作的名称
     */
    private String name;

    /**
     * 该操作发生的时间（年：月：日 时：分：秒）
     */
    private Date time;

    /**
     * 该操作的执行者
     */
    private String executor;

    /**
     * 该操作的ip地址
     */
    private String ip;

    /**
     * 该操作执行的客户端名称
     */
    private String client;

}