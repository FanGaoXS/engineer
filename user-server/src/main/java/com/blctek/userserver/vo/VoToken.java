package com.blctek.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/03/11:27
 * @Description: 与前端视图直接交互的对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;
}
