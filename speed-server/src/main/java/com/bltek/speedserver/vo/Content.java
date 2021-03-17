package com.bltek.speedserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/17/11:52
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long contentLength;
}
