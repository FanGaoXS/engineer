package com.fangaoxs.loggerserver.vo;

import com.fangaoxs.commonserver.pojo.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/13:33
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoLogger implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Date time;

    private String executor;

    private String ip;

    private String client;

    public VoLogger(Logger logger){
        this.id = logger.getId();
        this.name = logger.getName();
        this.time = logger.getTime();
        this.executor = logger.getExecutor();
        this.ip = logger.getIp();
        this.client = logger.getClient();
    }

}
