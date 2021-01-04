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

              <el-form-item label="所属工程用具编号">
                <span>{{scope.row.car.carId}}</span>
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
        <el-table-column label="车牌号" prop="plateNumber"/>
        <el-table-column label="车牌类型" prop="plateType"/>
        <el-table-column label="车辆类型" prop="vehicleModel"/>

        <el-table-column label="机械或车辆" prop="car.type"/>

        <el-table-column label="驾驶员" prop="driver.driverName"/>
        <el-table-column label="驾驶员联系方式" prop="driver.driverPhone"/>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="small" type="primary" @click="openModifyDialog(scope.$index,scope.row)">修改</el-button>
            <el-button size="small" type="danger" @click="openRemoveConfirm(scope.$index,scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--修改车辆信息的对话框-->
      <el-dialog title="修改车辆信息" :visible.sync="dialogFormVisible" ref="dialogForm">

        <el-form :model="dialogForm" label-position="left" label-width="150px">
          <el-form-item label="车辆编号">
            <el-input v-model="dialogForm.vehicleId" disabled/>
          </el-form-item>
          <el-form-item label="车牌号">
            <el-input type="text" v-model="dialogForm.plateNumber" disabled/>
          </el-form-item>
          <el-form-item label="车牌类型">
            <el-input type="text" v-model="dialogForm.plateType"/>
          </el-form-item>
          <el-form-item label="车辆类型">
            <el-input v-model="dialogForm.vehicleModel"/>
          </el-form-item>
          <el-form-item label="所属工程用具编号">
            <el-input v-model="dialogForm.carId" disabled/>
          </el-form-item>
          <el-form-item label="所属工程用具类型">
            <el-input v-model="dialogForm.type" disabled/>
          </el-form-item>
          <el-form-item label="设备编号">
            <el-input v-model="dialogForm.chipId" disabled/>
          </el-form-item>
          <el-form-item label="驾驶员编号">
            <el-input v-model="dialogForm.driverId" disabled/>
          </el-form-item>
          <el-form-item label="驾驶员姓名">
            <el-input v-model="dialogForm.driverName"/>
          </el-form-item>
          <el-form-item label="驾驶员电话号码">
            <el-input v-model="dialogForm.driverPhone"/>
          </el-form-item>
        </el-form>
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="modifyVehicle">确定</el-button>
      </el-dialog>

    </el-main>
  </el-container>
</template>

<script>

  import {
    getVehicleList,
    removeVehicleRow,
    modifyVehicleRow
  } from "../../../network/vehicleRequest";

  export default {
    name: "VehicleList",
    data() {
      return {
        dialogFormVisible: false,
        dialogForm: {
          vehicleId: 0,
          plateNumber: '',
          plateType: '',
          vehicleModel: '',

          carId: 0,
          type: '',
          chipId: '',

          driverId: 0,
          driverName: '',
          driverPhone: ''
        },
        vehicleList: [
          /*{
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
          },*/
        ]
      }
    },
    created() {
      this.getVehicleList(); // 组件初始化时获得车辆数据
    },
    methods: {
      // 向后端发起请求，获取车辆列表的数据
      getVehicleList() {
        getVehicleList().then(res=>{
          console.log('res->',res);
          this.vehicleList=res.data; // 后端的车辆列表数据赋给vehicleList
        }).catch(error=>{
          console.log('error->',error);
        });
      },
      //  打开修改车辆信息的对话框
      openModifyDialog(index,vehicleRow){
        this.dialogFormVisible = true; // 将对话框打开
        this.dialogForm.vehicleId = vehicleRow.vehicleId;
        this.dialogForm.plateNumber = vehicleRow.plateNumber;
        this.dialogForm.plateType = vehicleRow.plateType;
        this.dialogForm.vehicleModel = vehicleRow.vehicleModel;
        this.dialogForm.carId = vehicleRow.car.carId;
        this.dialogForm.type = vehicleRow.car.type;
        this.dialogForm.chipId = vehicleRow.car.chipId;
        this.dialogForm.driverId = vehicleRow.driver.driverId;
        this.dialogForm.driverName = vehicleRow.driver.driverName;
        this.dialogForm.driverPhone = vehicleRow.driver.driverPhone;
      },
      //  关闭修改车辆信息的对话框
      closeModifyDialog(){
        this.dialogFormVisible = false; // 将对话框关闭
      },
      // 打开删除确认框
      openRemoveConfirm(index,vehicleRow){
        let vehicleId = vehicleRow.vehicleId; //  从行中获取到vehicleId
        let carId = vehicleRow.car.carId; // 从行中获取到carId
        let driverId = vehicleRow.driver.driverId;  // 从行中获取driverId
        this.$confirm('此操作将永久删除该行车辆记录，确认删除？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: "warning"
        }).then(()=>{   //点击“confirm”
          this.removeVehicle(vehicleId,carId,driverId,index);

        }).catch(()=>{}); // 点击"cancel"
      },
      // 向后端发起请求修改该行车辆信息
      modifyVehicle(){
        modifyVehicleRow(this.dialogForm).then(res=>{
          this.$message({ //request请求删除成功
            showClose: true,
            message: res.msg+'成功！',
            type: "success",
          });
          this.closeModifyDialog(); //关闭对话框
          this.getVehicleList();    // 重新从后端获取车辆列表，达到刷新页面的目的
        }).catch(error=>{
          this.$message({
            showClose: true,
            message: error.msg,
            type: "error",
          });
        });
      },
      /**
       * 向后端发起请求删除该行车辆信息
       * @param vehicleId
       * @param carId
       * @param driverId
       * @param index 该行记录所在的数组下标
       */
      removeVehicle(vehicleId,carId,driverId,index){
        removeVehicleRow(vehicleId,carId,driverId).then(res=>{
          this.$message({ //request请求删除成功
            showClose: true,
            message: res.msg+'成功！',
            type: "success",
          });
          this.vehicleList.splice(index,1); // 从数组对象中动态移除该行记录
        }).catch(error=>{ //request请求删除失败
          this.$message({
            showClose: true,
            message: error.msg,
            type: "error",
          });
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