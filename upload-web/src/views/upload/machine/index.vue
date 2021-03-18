<template>
  <!--机械信息表单-->
  <el-form label-position="left"
           :model="machineForm"
           ref="machineForm"
           :rules="machineFormRules">
    <!--设备编号输入框（从url中自动获取）-->
    <el-form-item label="设备编号" prop="deviceId">
      <el-input
              type="text"
              :value="deviceId"
              disabled
      />
    </el-form-item>
    <!--机械类型下拉选择框-->
    <el-form-item label="机械类型" prop="modelId">
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
    <el-form-item label="机械型号" prop="machineNumber">
      <el-input
              v-model="machineForm.machineNumber"
              type="text"
              placeholder="请输入机械型号"
      />
    </el-form-item>
    <!--发动机编号输入框-->
    <el-form-item label="发动机编号" prop="engineNumber">
      <el-input
              v-model="machineForm.engineNumber"
              type="text"
              placeholder="请输入发动机编号"
      />
    </el-form-item>
    <!--驾驶员姓名输入框-->
    <el-form-item label="姓名" prop="driverName">
      <el-input
              v-model="machineForm.driverName"
              type="text"
              placeholder="请输入驾驶员姓名"
      />
    </el-form-item>
    <!--驾驶员联系方式输入框-->
    <el-form-item label="联系方式" prop="driverPhone">
      <el-input
              v-model="machineForm.driverPhone"
              type="text"
              placeholder="请输入驾驶员联系方式"
      />
    </el-form-item>
    <el-upload
            ref="upload"
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
    <el-form-item style="margin-top: 20px">
      <el-button type="primary" @click="submitForm('machineForm')">提交</el-button>
      <el-button @click="resetForm('machineForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>

  import {
    validateName,
    validatePhone
  } from "../../../utils/validate";

  import {
    getMachineModel
  } from "../../../api/model";

  import {
    insertMachine
  } from "../../../api/engineer";

  import {
    uploadImage
  } from "../../../api/image";

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
      return {
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
        machineForm:{
          uuid: '',
          deviceId: '',
          modelId: '',
          machineNumber: '',
          engineNumber: '',
          driverName: '',
          driverPhone: ''
        },
        machineModelOptions: [],
        limitFileSize: 15,  //限制上传文件的大小（单位：MB）
      }
    },
    created() {
      this.fetchMachineModel()
    },
    methods: {
      async fetchMachineModel(){
        const { data:machineModelList } = await getMachineModel()
        this.machineModelOptions = machineModelList.items
      },
      submitForm(formName){
        this.$refs[formName].validate(valid=>{
          if (valid&&this.machineForm.uuid!==''){ //如果所有表单验证通过
            insertMachine(this.machineForm).then(res => {
              if (res.data){
                this.$notify.success({
                  title: '成功',
                  message: res.message + ' 成功!'
                })
              } else {
                this.$notify.error({
                  title: '失败',
                  message: res.message + ' 失败!'
                })
              }
            })
          }
        })
      },
      resetForm(formName){
        this.$refs[formName].resetFields()  //清空表单
        this.$refs['upload'].clearFiles()   //清空已上传的文件列表
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
        this.machineForm.uuid = uuid //将uuid赋值到表单中
      },
    },
    computed: {
      //从url中获取的deviceId
      deviceId() {
        this.machineForm.deviceId = this.$route.params.deviceId
        return this.$route.params.deviceId;
      }
    },
  }
</script>

<style scoped>

</style>