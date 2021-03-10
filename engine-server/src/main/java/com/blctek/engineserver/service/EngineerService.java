package com.blctek.engineserver.service;

import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.pojo.Engineer;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/09/16:40
 * @Description:    对engineer表的具体业务
 */
@Service
public interface EngineerService {

    /**
     * 新增一条工程用具记录（机械或车辆）以及驾驶员信息
     * @param engineer              工程用具对象
     * @param driver                驾驶员信息对象
     * @return                      true成功，false失败
     */
    Boolean insertEngineerAndDriver(Driver driver,Engineer engineer);

    /**
     * 根据id删除一条工程用具记录
     * @param id                    工程用具编号
     * @return                      true成功，false失败
     */
    Boolean deleteEngineer(Integer id);

    /**
     * 根据id修改一条工程用具记录
     * @param engineer              工程用具对象
     * @return                      true成功，false失败
     */
    Boolean updateEngineer(Engineer engineer);

    /**
     * （机械或车辆）来查询（且可以分页）
     * @param engineer  工程用具对象（可包含条件或分页）
     * @return  工程用具列表
     */
    List<Engineer> selectList(Engineer engineer);

    /**
     * 查询一条工程用具记录
     * @param engineer  工程用具对象（可条件）
     * @return  工程用具
     */
    Engineer selectOne(Engineer engineer);
    /**
     * 查询列表的记录数
     * @param engineer  工程用具对象（可包含条件或分页）
     * @return  记录数
     */
    Long selectTotalSize(Engineer engineer);
}
