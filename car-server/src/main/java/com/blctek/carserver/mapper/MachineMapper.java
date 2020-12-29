package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Machine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/16:10
 * @Description:    对machine表的crud
 */
@Mapper
@Repository
public interface MachineMapper {

    /**
     *  查询所有机械
     * @return 机械以及它的工程信息集合
     */
    List<Machine> selectAllMachineAndCarAndDriver();

    /**
     *  新增机械
     * @param machine 机械对象
     * @return 新增机械的数量
     */
    int insertMachine(Machine machine);

    /**
     *  修改机械信息
     * @param machine 机械对象
     * @return  成功修改的数量
     */
    int updateMachine(Machine machine);

    /**
     *  删除机械信息（根据machineId）
     * @param machineId
     * @return  成功删除的数量
     */
    int deleteMachine(Integer machineId);
}
