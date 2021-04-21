<template>
  <!--车辆信息表单-->
  <el-form label-position="left"
           :model="vehicleForm"
           ref="vehicleForm"
           :rules="vehicleFormRules">
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
            ref="upload"
            action="/assets-server/img/insertEngineer"
            show-file-list
            list-type="picture"
            name="image"
            :limit="1"
            :accept="'image/png, image/jpeg'"
            :before-upload="beforeUpload"
            :on-success="successUpload"
            :on-error="errorUpload"
    >
      <el-button size="medium" type="primary">上传车辆照片<i class="el-icon-upload el-icon--right"></i></el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过15MB</div>
    </el-upload>
    <el-form-item style="margin-top: 20px">
      <el-button type="primary" @click="submitForm('vehicleForm')">提交</el-button>
      <el-button @click="resetForm('vehicleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {
    validateName,
    validatePhone,
    validateVehicleNumber
  } from "../../../utils/validate";

  import {
    getVehicleModel
  } from "../../../api/model";

  import {
    uploadImage
  } from "../../../api/image";

  import {
    insertVehicle
  } from "../../../api/engineer";

  export default {
    name: "index",
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
        limitFileSize: 15,  //限制上传文件的大小（单位：MB）
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
        vehicleForm:{
          uuid: '',
          deviceId: '',
          modelId: '',
          plateType: '',
          vehicleNumber: '',
          driverName: '',
          driverPhone: ''
        },
        vehicleModelOptions: [],
      }
    },
    created() {
      this.fetchVehicleModel() //组件初始化时获取车辆类型
    },
    methods: {
      async fetchVehicleModel(){
        const { data:vehicleModelList } = await getVehicleModel()
        this.vehicleModelOptions = vehicleModelList.items
      },
      submitForm(formName){
        this.$refs[formName].validate(valid=>{
          if (valid&&this.vehicleForm.uuid!==''){ //如果所有表单验证通过
            insertVehicle(this.vehicleForm).then(res => {
              console.log(res)
              this.$notify({
                type: res.data?'success':'error',
                title: res.data?'成功':'失败',
                message: res.message
              })
            }).catch(error=>{
              console.log('新增车辆失败：',error)
              this.$notify.error({
                title: '失败',
                message: error
              })
            })
          }
        })
      },
      resetForm(formName){
        this.$refs[formName].resetFields()  //清空表单
        this.$refs['inputCar'].cancel()     //清空车牌号输入框
        this.$refs['upload'].clearFiles()   //清空已上传的文件列表
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
      //上传成功的钩子函数
      successUpload(response, file, fileList){
        const { data } = response
        this.vehicleForm.uuid = data.uuid //将uuid赋值到表单中
        this.$notify.success({
          title: '成功',
          message: '图片上传成功！'
        })
      },
      //上传失败的钩子函数
      errorUpload(error, file, fileList){
        console.log('图片上传失败->',error)
        this.$notify.error({
          title: '失败',
          message: '图片上传失败！请联系管理员'
        })
      },
      //自定义上传
      /*async upload(param){
        let formData = new FormData()
        formData.append('image',param.file) //param.file即是刚刚上传的文件
        const { data } = await uploadImage(formData)
        const uuid = data.uuid //从返回的数据中获得uuid
        this.vehicleForm.uuid = uuid //将uuid赋值到表单中
      },*/
    },
    computed: {
      //从url中获取的deviceId
      deviceId() {
        this.vehicleForm.deviceId = this.$route.params.deviceId
        return this.$route.params.deviceId;
      }
    },
  }
</script>

<style scoped>

</style>
