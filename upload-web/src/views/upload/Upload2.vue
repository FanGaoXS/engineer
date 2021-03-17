<template>
  <el-container>
    <h2>deviceId：{{deviceId}}</h2>
    <el-header>车辆信息上传</el-header>
    <el-main>
      <el-button-group>
        <el-button :type="isVehicleForm?'primary':''" @click="changeForm('vehicleForm')">车辆</el-button>
        <el-button :type="!isVehicleForm?'primary':''" @click="changeForm('machineForm')">机械</el-button>
      </el-button-group>
      <!--车辆信息表单-->
      <el-form label-position="left" :model="vehicleForm" ref="vehicleForm" v-if="isVehicleForm">
        <!--设备编号输入框（从url中自动获取）-->
        <el-form-item label="设备编号">
          <el-input
                  type="text"
                  :value="deviceId"
                  disabled
          />
        </el-form-item>
        <!--车牌类型输入框-->
        <el-form-item label="车牌类型">
          <el-switch
                  active-color="rgb(56,181,63)"
                  inactive-color="rgb(17,17,131)"
                  active-text="新能源"
                  inactive-text="汽油车"
          />
        </el-form-item>
        <!--车辆类型下拉选择框-->
        <el-form-item label="车辆类型">
          <el-select placeholder="请选择" clearable value="车辆类型">
            <!--<el-option></el-option>-->
          </el-select>
        </el-form-item>
        <!--自定义的车牌号输入框-->
        <el-form-item label="车牌号">
          <input-car/>
        </el-form-item>
        <!--驾驶员姓名输入框-->
        <el-form-item label="姓名">
          <el-input
                  type="text"
                  placeholder="请输入驾驶员姓名"
          />
        </el-form-item>
        <!--驾驶员联系方式输入框-->
        <el-form-item label="联系方式">
          <el-input
                  type="text"
                  placeholder="请输入驾驶员联系方式"
          />
        </el-form-item>
      </el-form>
      <!--机械信息表单-->
      <el-form label-position="left" ref="machineForm" v-if="!isVehicleForm">
        <!--设备编号输入框（从url中自动获取）-->
        <el-form-item label="设备编号">
          <el-input
                  type="text"
                  :value="deviceId"
                  disabled
          />
        </el-form-item>
        <!--机械类型下拉选择框-->
        <el-form-item label="机械类型">
          <el-select placeholder="请选择" value="机械类型">
            <!--<el-option></el-option>-->
          </el-select>
        </el-form-item>
        <!--机械型号输入框-->
        <el-form-item label="机械型号">
          <el-input
                  type="text"
                  placeholder="请输入机械型号"
          />
        </el-form-item>
        <!--发动机编号输入框-->
        <el-form-item label="发动机编号">
          <el-input
                  type="text"
                  placeholder="请输入发动机编号"
          />
        </el-form-item>
        <!--驾驶员姓名输入框-->
        <el-form-item label="姓名">
          <el-input
                  type="text"
                  placeholder="请输入驾驶员姓名"
          />
        </el-form-item>
        <!--驾驶员联系方式输入框-->
        <el-form-item label="联系方式">
          <el-input
                  type="text"
                  placeholder="请输入驾驶员联系方式"
          />
        </el-form-item>
      </el-form>
    </el-main>
    <el-footer>footer</el-footer>
  </el-container>
</template>

<script>
  export default {
    name: "Upload2",
    data() {
      return {
        isVehicleForm: true,
        vehicleForm:{
          deviceId: '',
          modelId: 0,
          plateType: '',
          vehicleNumber: '',
          driverName: '',
          driverPhone: ''
        },
        machineForm:{
          deviceId: '',
          modelId: 0,
          machineNumber: '',
          engineNumber: '',
          driverName: '',
          driverPhone: ''
        }
      }
    },
    methods: {
      changeForm(formName) {
        if (formName==='vehicleForm'){//切换到车辆信息表单
          this.isVehicleForm = true
        } else if (formName==='machineForm'){//切换到机械信息表单
          this.isVehicleForm = false
        }
      }
    },
    computed: {
      //从url中获取的deviceId
      deviceId() {
        this.vehicleForm.deviceId = this.$route.params.deviceId
        this.machineForm.deviceId = this.$route.params.deviceId
        return this.$route.params.deviceId;
      }
    },
  }
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #629efa;
    color: #ffffff;
    text-align: center;
    line-height: 60px;
    border-radius: 20px;
  }

  .el-main {
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 50px;
    margin-bottom: 50px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
</style>