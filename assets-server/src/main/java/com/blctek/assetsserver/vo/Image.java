package com.blctek.assetsserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/16/18:04
 * @Description:
 */
@NoArgsConstructor
@Data
public class Image {

    private String path;

    public Image(String imagePath){
        this.path = imagePath;
    }


}
