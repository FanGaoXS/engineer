package com.blctek.engineserver.vo.list;

import com.blctek.engineserver.pojo.Model;
import com.blctek.engineserver.vo.VoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/14/20:10
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoModelList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<VoModel> items;

    public VoModelList(List<Model> modelList){
        this.items = new ArrayList<>();
        modelList.forEach(model -> this.items.add(new VoModel(model)));
    }
}
