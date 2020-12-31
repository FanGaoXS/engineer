<template>
  <el-container>
    <el-main>
      <el-table :data="machineList">
        <el-table-column type="expand"><!--可展开表格-->
          <template slot-scope="scope">
            <el-form label-position="left"  class="demo-table-expand">
              <el-form-item label="机械编号">
                <span>{{scope.row.machineId}}</span>
              </el-form-item>
              <el-form-item label="机械型号">
                <span>{{scope.row.machineNumber}}</span>
              </el-form-item>
              <el-form-item label="发动机编号">
                <span>{{scope.row.engineNumber}}</span>
              </el-form-item>
              <el-form-item label="机械类型">
                <span>{{scope.row.machineModel}}</span>
              </el-form-item>
              <el-form-item label="工程用具类型">
                <span>{{scope.row.car.type}}</span>
              </el-form-item>
              <el-form-item label="设备编号">
                <span>{{scope.row.car.chipId}}</span>
              </el-form-item>
              <el-form-item label="驾驶员编号">
                <span>{{scope.row.driver.driverId}}</span>
              </el-form-item>
              <el-form-item label="驾驶员姓名">
                <span>{{scope.row.driver.driverName}}</span>
              </el-form-item>
              <el-form-item label="驾驶员电话号码">
                <span>{{scope.row.driver.driverPhone}}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>

        <!--常规表格-->
        <el-table-column label="机械编号" prop="machineId"/>
        <el-table-column label="设备编号" prop="car.chipId"/>
        <el-table-column label="机械或车辆" prop="car.type"/>
        <el-table-column label="机械类型" prop="machineModel"/>
        <el-table-column label="机械型号" prop="machineNumber"/>
        <el-table-column label="驾驶员" prop="driver.driverName"/>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="small" type="primary" @click="modifyMachine(scope.$index,scope.row)">修改</el-button>
            <el-button size="small" type="danger" @click="removeMachine(scope.$index,scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </el-main>
  </el-container>
</template>

<script>

  import {
    getMachineList,
    removeMachineRow
  } from "../../../network/machineRequest";

  export default {
    name: "MachineList",
    data() {
      return {
        machineList: [
          /*{
            machineId: 8, //机械编号
            carId: 26,  //所属工程用具编号
            machineNumber: "卡特320D",  //机械型号
            engineNumber: "100000000000001",  //发动机编号
            machineModel: "挖机", //机械类型
            car: { //所属工程用具
              carId: 26,
              type: "机械", //所属工程用具类型
              driverId: 26, //驾驶员编号
              chipId: "10001",  //芯片编号
              driver: null
            },
            driver: { //所属驾驶员信息
              driverId: 26, //所属驾驶员编号
              driverName: "杨非", //所属驾驶员姓名
              driverPhone: "17760340437"  //所属驾驶员电话号码
            }
          },*/
        ]
      }
    },
    created() {
      getMachineList().then(res=>{
        console.log('response->',res);
        this.machineList=res.data;
      }).catch(error=>{
        console.log(error);
      });
    },
    methods: {
      modifyMachine(index,machineRow){
        console.log(index);
        console.log(machineRow);
      },
      /**
       *  删除一行机械记录
       * @param index 该行记录所在的下标
       * @param machineRow  该行记录对象
       */
      removeMachine(index,machineRow){
        let machineId = machineRow.machineId; //  从行中获取到machineId
        let carId = machineRow.car.carId; // 从行中获取到carId
        let driverId = machineRow.driver.driverId;  // 从行中获取driverId
        this.$confirm('此操作将永久删除该行车辆记录，确认删除？','提示',{
          confirmButtonText: '确定',  //确认按钮的文字
          cancelButtonText: '取消',   //取消按钮的文字
          type: "warning"
        }).then(()=>{ // 确认按钮
          removeMachineRow(machineId,carId,driverId).then(res=>{
            this.$message({ //request请求成功
              showClose: true,
              message: res.msg+'成功！',
              type: "success",
            });
            this.machineList.splice(index,1); // 从数组对象中动态移除该行记录
          }).catch(error=>{ //request请求失败
            this.$message({
              showClose: true,
              message: error.msg,
              type: "error",
            });
          });
        }).catch(()=>{  //取消按钮

        });

      }
    },
  }
</script>

<style scoped>
  .el-container{
    /*width: 1600px;*/
  }

  .el-header, .el-footer {
    /*background-color: #B3C0D1;*/
    color: #333;
    text-align: center;
    line-height: 40px;
  }

  .el-main {
    background-color: #E9EEF3;
    /*color: #333;*/
    /*text-align: center;*/
    /*line-height: 160px;*/
  }

  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

</style>