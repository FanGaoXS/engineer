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
        <el-table-column label="机械型号" prop="machineNumber"/>
        <el-table-column label="机械类型" prop="machineModel"/>
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

      <!--修改机械信息的对话框-->
      <el-dialog title="修改机械信息" :visible.sync="dialogFormVisible" ref="dialogForm">

        <el-form :model="dialogForm" label-position="left" label-width="150px">
          <el-form-item label="机械编号">
            <el-input v-model="dialogForm.machineId" disabled/>
          </el-form-item>
          <el-form-item label="机械型号">
            <el-input v-model="dialogForm.machineNumber" disabled/>
          </el-form-item>
          <el-form-item label="机械类型">
            <el-input v-model="dialogForm.machineModel"/>
          </el-form-item>
          <el-form-item label="发动机编号">
            <el-input v-model="dialogForm.engineNumber" disabled/>
          </el-form-item>
          <el-form-item label="所属工程用具编号">
            <el-input v-model="dialogForm.carId" disabled/>
          </el-form-item>
          <el-form-item label="机械或车辆">
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
          <el-form-item label="驾驶员手机号">
            <el-input v-model="dialogForm.driverPhone"/>
          </el-form-item>
        </el-form>
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="modifyMachine">确定</el-button>
      </el-dialog>

    </el-main>
  </el-container>
</template>

<script>

  import {
    getMachineList,
    removeMachineRow,
    modifyMachineRow
  } from "../../../network/machineRequest";

  export default {
    name: "MachineList",
    data() {
      return {
        dialogFormVisible: false,
        dialogForm: {
          machineId: 0,
          machineNumber : '',
          machineModel: '',
          engineNumber: '',

          carId: 0,
          type: '',
          chipId: '',

          driverId: 0,
          driverName: '',
          driverPhone: ''
        },
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
      this.getMachineList(); //组件初始化时从后端获取机械数据
    },
    methods: {
      //从后端获取机械数据
      getMachineList(){
        getMachineList().then(res=>{
          console.log('response->',res);
          this.machineList=res.data;
        }).catch(error=>{
          console.log(error);
        });
      },
      //打开修改的对话框
      openModifyDialog(index,machineRow){
        this.dialogFormVisible = true;
        this.dialogForm.machineId = machineRow.machineId;
        this.dialogForm.machineNumber = machineRow.machineNumber;
        this.dialogForm.machineModel = machineRow.machineModel;
        this.dialogForm.engineNumber = machineRow.engineNumber;
        this.dialogForm.carId = machineRow.car.carId;
        this.dialogForm.type = machineRow.car.type;
        this.dialogForm.chipId = machineRow.car.chipId;
        this.dialogForm.driverId = machineRow.driver.driverId;
        this.dialogForm.driverName = machineRow.driver.driverName;
        this.dialogForm.driverPhone = machineRow.driver.driverPhone;
      },
      //关闭修改的对话框
      closeModifyDialog(){
        this.dialogFormVisible = false;
      },
      //打开删除的确认框
      openRemoveConfirm(index,machineRow){
        let machineId = machineRow.machineId; //  从行中获取到machineId
        let carId = machineRow.car.carId; // 从行中获取到carId
        let driverId = machineRow.driver.driverId;  // 从行中获取driverId
        this.$confirm('此操作将永久删除该行车辆记录，确认删除？','提示',{
          confirmButtonText: '确定',  //确认按钮的文字
          cancelButtonText: '取消',   //取消按钮的文字
          type: "warning"
        }).then(()=>{ // 确认按钮
          this.removeMachine(machineId,carId,driverId,index);
        }).catch(()=>{  //取消按钮
        });
      },
      //向后端发起请求修改机械信息
      modifyMachine(){
        modifyMachineRow(this.dialogForm).then(res=>{//请求成功
          this.$message({ //request请求删除成功
            showClose: true,
            message: res.msg+'成功！',
            type: "success",
          });
          this.closeModifyDialog(); //关闭对话框
          this.getMachineList(); // 重新从后端获取机械数据
        }).catch(error=>{//请求失败
          this.$message({
            showClose: true,
            message: error.msg,
            type: "error",
          });
        });
      },
      //向后端发起请求删除机械信息
      removeMachine(machineId,carId,driverId,index){
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