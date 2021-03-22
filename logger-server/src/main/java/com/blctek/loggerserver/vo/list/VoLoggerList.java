package com.blctek.loggerserver.vo.list;

import com.blctek.commonserver.pojo.Logger;
import com.blctek.loggerserver.vo.VoLogger;
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
 * @Date: 2021/03/22/13:31
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoLoggerList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<VoLogger> items;

    public VoLoggerList(List<Logger> loggerList){
        this.items = new ArrayList<>();
        loggerList.forEach(logger -> this.items.add(new VoLogger(logger)));
    }

}
