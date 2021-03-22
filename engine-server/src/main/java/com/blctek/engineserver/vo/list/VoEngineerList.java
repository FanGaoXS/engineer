package com.blctek.engineserver.vo.list;

import com.blctek.engineserver.pojo.Engineer;
import com.blctek.engineserver.vo.VoEngineer;
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
 * @Date: 2021/03/10/17:27
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoEngineerList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<VoEngineer> items;

    public void setItems(List<Engineer> engineerList){
        this.items = new ArrayList<>();  //初始化items
        //将List<Engineer>对象迭代得到engineer然后将engineer转为VoEngineer然后在放到items集合中
        engineerList.forEach(engineer -> this.items.add(new VoEngineer(engineer)));
    }
}
