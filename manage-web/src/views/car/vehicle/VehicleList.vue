<template>
  <el-container>
    <el-main>
      <el-table :data="vehicleList">
        <el-table-column type="expand"><!--可展开表格-->
          <template slot-scope="scope">
            <el-form label-position="left"  class="demo-table-expand">
              <el-form-item label="车辆编号">
                <span>{{scope.row.vehicleId}}</span>
              </el-form-item>
              <el-form-item label="车牌号">
                <span>{{scope.row.plateNumber}}</span>
              </el-form-item>
              <el-form-item label="车牌类型">
                <span>{{scope.row.plateType}}</span>
              </el-form-item>
              <el-form-item label="车辆类型">
                <span>{{scope.row.vehicleModel}}</span>
              </el-form-item>
              <el-form-item label="工程用具类型">
                <span>{{scope.row.car.type}}</span>
              </el-form-item>
              <el-form-item label="芯片编号">
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
        <el-table-column
                label="车辆"
                prop="vehicleId"/>
        <el-table-column
                label="芯片编号"
                prop="car.chipId"/>
        <el-table-column
                label="机械或车辆"
                prop="car.type"/>
        <el-table-column
                label="车辆类型"
                prop="vehicleModel"/>
        <el-table-column
                label="车牌号"
                prop="plateNumber"/>
        <el-table-column
                label="车牌类型"
                prop="plateType"
        />
        <el-table-column
                label="驾驶员"
                prop="driver.driverName"/>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="small" type="primary" @click="uploadVehicle(scope.$index,scope.row)">修改</el-button>
            <el-button size="small" type="danger" @click="deleteVehicle(scope.$index,scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </el-main>
  </el-container>
</template>

<script>

  import {
    getVehicleList
  } from "../../../network/vehicleRequest";

  export default {
    name: "VehicleList",
    data() {
      return {
        vehicleList: [
          {
            vehicleId: 13, // 车辆编号
            carId: 23,  //所属工程用具编号
            plateNumber: "藏CF8958", //车牌号
            plateType: "汽油车", //车牌类型
            vehicleModel: "皮卡车",  //车辆类型
            car: {  //所属工程用具
              carId: 23,
              type: "车辆", // 所属工程用具类型
              driverId: 23, // 驾驶员编号
              chipId: "10001",  //芯片编号
              driver: null
            },
            driver: { // 所属驾驶员
              driverId: 23,
              driverName: "袁世刚", // 驾驶员姓名
              driverPhone: "13698185376"  //驾驶员电话号码
            }
          },
        ]
      }
    },
    created() {
      getVehicleList().then(res=>{
        console.log('response->',res);
        console.log(res.data)
        this.vehicleList=res.data;
      }).catch(error=>{
        console.log(error);
      });
    },
    methods: {
      uploadVehicle(index,machineRow){
        console.log(index);
        console.log(machineRow);
      },
      deleteVehicle(index,machineRow){
        console.log(index);
        console.log(machineRow);
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