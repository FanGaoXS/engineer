package com.fangaoxs.assetsserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/16/18:04
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    private String path;

    private String uuid;

}
