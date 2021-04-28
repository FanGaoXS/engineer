package com.fangaoxs.engineserver.vo;

import com.fangaoxs.engineserver.pojo.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/10/14:35
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoDriver implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private String name;

    private String phone;

    //Driver可以直接通过构造转为VoDriver
    public VoDriver(Driver driver) {
        this.id = driver.getId();
        this.uuid = driver.getUuid();
        this.name = driver.getName();
        this.phone = driver.getPhone();
    }
}
