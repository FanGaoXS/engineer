package com.fangaoxs.commonserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/23/12:59
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoList<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<T> items;

}
