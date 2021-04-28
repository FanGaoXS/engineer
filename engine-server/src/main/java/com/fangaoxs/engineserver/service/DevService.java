package com.fangaoxs.engineserver.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/11/18:05
 * @Description:
 */
@Service
public interface DevService {
    /**
     * 将一个设备与工程用具绑定
     * @param deviceId  设备编号
     * @return          Http请求的状态码
     */
    HttpStatus insertDev(String deviceId,String number);
}
