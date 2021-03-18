<template>
  <el-container>
    <h2>deviceId：{{deviceId}}</h2>
    <h2>vehicleNumber：{{vehicleForm.vehicleNumber}}</h2>
    <h2>modelId：{{vehicleForm.modelId}}</h2>
    <el-header>车辆信息上传</el-header>
    <el-main>
      <el-button-group>
        <el-button :type="isVehicleForm?'primary':''" @click="changeForm('vehicleForm')">车辆</el-button>
        <el-button :type="!isVehicleForm?'primary':''" @click="changeForm('machineForm')">机械</el-button>
      </el-button-group>
      <!--车辆信息表单-->
      <el-form label-position="left"
               :model="vehicleForm"
               ref="vehicleForm"
               :rules="vehicleFormRules"
               v-if="isVehicleForm">
        <!--设备编号输入框（从url中自动获取）-->
        <el-form-item label="设备编号" prop="deviceId">
          <el-input
                  type="text"
                  :value="deviceId"
                  disabled
          />
        </el-form-item>
        <!--车牌类型输入框-->
        <el-form-item label="车牌类型">
          <el-switch
                  v-model="vehicleForm.plateType"
                  active-color="rgb(56,181,63)"
                  inactive-color="rgb(17,17,131)"
                  :width="50"
                  active-text="新能源"
                  inactive-text="汽油车"
                  active-value="新能源"
                  inactive-value="汽油车"
                  @change="changePlateType"
          />
        </el-form-item>
        <!--车辆类型下拉选择框-->
        <el-form-item label="车辆类型" prop="modelId">
          <el-select placeholder="请选择" clearable v-model="vehicleForm.modelId">
            <el-option
                    v-for="item in vehicleModelOptions"
                    :key="item.name"
                    :label="item.name"
                    :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!--自定义的车牌号输入框-->
        <el-form-item label="车牌号" prop="vehicleNumber">
          <input-car
                  ref="inputCar"
                  @submit="getVehicleNumber"
          />
        </el-form-item>
        <!--驾驶员姓名输入框-->
        <el-form-item label="姓名" prop="driverName">
          <el-input
                  v-model="vehicleForm.driverName"
                  type="text"
                  placeholder="请输入驾驶员姓名"
          />
        </el-form-item>
        <!--驾驶员联系方式输入框-->
        <el-form-item label="联系方式" prop="driverPhone">
          <el-input
                  v-model="vehicleForm.driverPhone"
                  type="text"
                  placeholder="请输入驾驶员联系方式"
          />
        </el-form-item>
        <el-upload
                action="#"
                show-file-list
                list-type="picture"
                :limit="1"
                :accept="'image/png, image/jpeg'"
                :before-upload="beforeUpload"
                :http-request="upload"
        >
          <el-button size="medium" type="primary">上传车辆照片<i class="el-icon-upload el-icon--right"></i></el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过15MB</div>
        </el-upload>
        <el-form-item>
          <el-button type="primary" @click="submitForm('vehicleForm')">提交</el-button>
          <el-button @click="resetForm('vehicleForm')">重置</el-button>
        </el-form-item>
      </el-form>
      <!--机械信息表单-->
      <el-form label-position="left"
               :model="machineForm"
               ref="machineForm"
               :rules="machineFormRules"
               v-if="!isVehicleForm">
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
          <el-select placeholder="请选择" v-model="machineForm.modelId">
            <el-option
                    v-for="item in machineModelOptions"
                    :key="item.name"
                    :label="item.name"
                    :value="item.id"
            ></el-option>
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
        <el-form-item>
          <el-button type="primary" @click="submitForm('machineForm')">提交</el-button>
          <el-button @click="resetForm('machineForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-main>
    <el-footer>footer</el-footer>
  </el-container>
</template>

<script>

  import {
    uploadImage
  } from "../../api/image";

  import {
    validateName,
    validatePhone,
    validateVehicleNumber
  } from "../../utils/validate";

  import {
    getMachineModel,
    getVehicleModel
  } from "../../api/model";

  export default {
    name: "Upload2",
    data() {
      const validatorName = (rule, value, callback) => {
        if (!validateName(value)){
          callback(new Error('请输入正确的姓名'))
        } else {
          callback()
        }
      }
      const validatorPhone = (rule, value, callback) => {
        if (!validatePhone(value)){
          callback(new Error('请输入正确的联系方式'))
        } else {
          callback()
        }
      }
      const validatorVehicleNumber = (rule, value, callback) => {
        if (!validateVehicleNumber(value)){
          callback(new Error('请输入正确的车牌号码'))
        } else {
          callback()
        }
      }
      return {
        vehicleFormRules:{
          deviceId: [
            { required: true, message: '请输入设备编号', trigger: 'blur'}
          ],
          modelId: [
            { required: true, message: '请选择车辆类型', trigger: 'change'}
          ],
          vehicleNumber: [
            { required: true, validator: validatorVehicleNumber, }
          ],
          driverName: [
            { required: true, validator: validatorName, trigger: 'blur'}
          ],
          driverPhone: [
            { required: true, validator: validatorPhone, trigger: 'blur'}
          ]
        },
        machineFormRules:{
          deviceId: [
            { required: true, message: '请输入设备编号', trigger: 'blur'}
          ],
          modelId: [
            { required: true, message: '请选择机械类型', trigger: 'change'}
          ],
          machineNumber:[
            { required: true, message: '请输入机械型号', trigger: 'blur'}
          ],
          engineNumber: [
            { required: true, message: '请输入发动机编号', trigger: 'blur'}
          ],
          driverName: [
            { required: true, validator: validatorName, trigger: 'blur'}
          ],
          driverPhone: [
            { required: true, validator: validatorPhone, trigger: 'blur'}
          ]
        },
        vehicleForm:{
          uuid: '',
          deviceId: '',
          modelId: '',
          plateType: '',
          vehicleNumber: '',
          driverName: '',
          driverPhone: ''
        },
        machineForm:{
          uuid: '',
          deviceId: '',
          modelId: '',
          machineNumber: '',
          engineNumber: '',
          driverName: '',
          driverPhone: ''
        },
        vehicleModelOptions: [],
        machineModelOptions: [],
        limitFileSize: 15,  //限制上传文件的大小（单位：MB）
        isVehicleForm: true,
      }
    },
    created() {
      this.fetchVehicleModel() //填充车辆类型
      this.fetchMachineModel() //填充机械类型
    },
    methods: {
      async fetchVehicleModel(){
        const { data:vehicleModelList } = await getVehicleModel()
        this.vehicleModelOptions = vehicleModelList.items
      },
      async fetchMachineModel(){
        const { data:machineModelList } = await getMachineModel()
        this.machineModelOptions = machineModelList.items
      },
      //修改车辆表单或者机械表单
      changeForm(formName) {
        if (formName==='vehicleForm'){//切换到车辆信息表单
          this.resetForm('machineForm')
          this.isVehicleForm = true
        } else if (formName==='machineForm'){//切换到机械信息表单
          this.resetForm('vehicleForm')
          this.isVehicleForm = false
        }
      },
      submitForm(formName){
        console.log(formName)
        this.$refs[formName].validate(valid=>{
          console.log(valid)
        })
      },
      resetForm(formName){
        console.log(formName)
        this.$refs[formName].resetFields()  //清空表单
        this.$refs['inputCar'].cancel()     //清空车牌号输入框
      },
      //绑定switch修改车牌类型（同时更改车牌号输入的位数）
      changePlateType(plateType){
        this.$refs.inputCar.changeDefaultType()
      },
      //从车牌号输入框中获得车牌号
      getVehicleNumber(vehicleNumber){
        this.vehicleForm.vehicleNumber = vehicleNumber
      },
      //上传前的处理（返回true能够上传，返回false不能上传）
      beforeUpload(file){ //返回true就能上传，返回false不能上传
        let realFileSize = file.size //实际文件大小（单位字节）
        let limitFileSize = this.limitFileSize * 1024 * 1024 //限制文件大小（单位字节）
        return realFileSize<limitFileSize //上传文件的大小小于限制文件大小返回true，否则返回false
      },
      //自定义上传
      async upload(param){
        let formData = new FormData()
        formData.append('image',param.file) //param.file即是刚刚上传的文件
        const { data } = await uploadImage(formData)
        const uuid = data.uuid //从返回的数据中获得uuid
        this.vehicleForm.uuid = uuid //将uuid赋值到表单中
        this.machineForm.uuid = uuid //将uuid赋值到表单中
      },
      submitVehicleForm(){
        console.log('提交车辆表单')
      },
      resetVehicleForm(){
        console.log('重置车辆表单')
      },
      submitMachineForm(){
        console.log('提交机械表单')
      },
      resetMachineForm(){
        console.log('重置机械表单')
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