package com.blctek.engineserver.vo.list;

import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.vo.VoDriver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/14/19:47
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoDriverList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<VoDriver> items;

    public VoDriverList(List<Driver> driverList){
        this.items = new ArrayList<>();
        //将driverList对象迭代得到对应的driver对象，然后构造成VoDriver对象再add进list
        driverList.forEach(driver -> this.items.add(new VoDriver(driver)));
    }
}
