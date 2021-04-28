package com.fangaoxs.engineserver.vo;

import com.fangaoxs.engineserver.pojo.Model;
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
public class VoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private String name;

    private String type;

    private String description;

    //Model对象可以直接通过构造转为VoModel
    public VoModel(Model model) {
        this.id = model.getId();
        this.uuid = model.getUuid();
        this.name = model.getName();
        this.type = model.getType();
        this.description = model.getDescription();
    }
}
