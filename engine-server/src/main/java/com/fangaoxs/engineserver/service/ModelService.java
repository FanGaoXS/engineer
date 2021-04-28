package com.fangaoxs.engineserver.service;

import com.fangaoxs.engineserver.pojo.Model;
import com.fangaoxs.engineserver.vo.VoModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/14/18:48
 * @Description:
 */
@Service
public interface ModelService {
    /**
     * 新增类型
     * @param model         类型对象
     * @return              true成功，false失败
     */
    Boolean insertModel(Model model);

    /**
     * 根据编号删除类型
     * @param id            类型编号
     * @return              true成功，false失败
     */
    Boolean deleteModel(Integer id);

    /**
     * 修改类型
     * @param model         类型对象
     * @return              true成功，false失败
     */
    Boolean updateModel(Model model);

    /**
     * 根据车辆或机械查询类型（可分页）
     * @param type          机械或车辆
     * @param currentPage   当前页
     * @param pageSize      每页记录数
     * @return
     */
    List<VoModel> selectModelByType(String type,
                                    Integer currentPage,
                                    Integer pageSize);

    /**
     * 根据条件查询记录数
     * @param model         model对象
     * @return              记录数
     */
    Long selectTotalSize(Model model);
}
