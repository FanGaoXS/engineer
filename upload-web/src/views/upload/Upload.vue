<template>
  <el-container>
    <el-header>车辆信息上传</el-header>
    <el-main>
      <!--“车辆或者机械”按钮组-->
      <el-button-group>
        <el-button @click="changeIsNormalVehicle(true)" :type=" isVehicle ? 'primary' : '' " >车辆</el-button>
        <el-button @click="changeIsNormalVehicle(false)" :type=" isVehicle ? '' : 'primary' " >机械</el-button>
      </el-button-group>
      <!--普通车辆表单-->
      <el-form label-position="left" v-if="isVehicle">
        <!--车牌类型-->
        <el-form-item label="车牌类型">
          <!--开关-->
          <el-switch
                  v-model="formItem1.plateType"
                  active-color="rgb(56,181,63)"
                  inactive-color="rgb(17,17,131)"
                  :width="50"
                  @change="changeInputBoxType"
                  active-text="新能源"
                  inactive-text="汽油车">
          </el-switch>
        </el-form-item>
        <!--车辆类型-->
        <el-form-item label="车型">
          <!--下拉选择框-->
          <el-select v-model="formItem1.vehicleModel" placeholder="请选择">
            <!--普通车辆选择组-->
            <el-option-group v-for="group in vehicleOptionGroup" :key="group.label" :label="group.label" v-if="isVehicle">
              <!--选项-->
              <el-option v-for="item in group.options" :key="item" :label="item" :value="item"></el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <!--车牌号-->
        <el-form-item label="车牌号">
          <!-- 自定义车牌号输入框以及键盘组件 -->
          <input-car
                  v-if="isVehicle"
                  ref="inputCar"
                  @submit="getPlateNumber"
          ></input-car>
        </el-form-item>
        <!--驾驶员-->
        <el-form-item label="驾驶员">
          <el-input
                  type="text"
                  placeholder="请输入驾驶员姓名"
                  v-model.trim="formItem1.driverName"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input
                  type="number"
                  placeholder="请输入驾驶员手机号码"
                  v-model.trim="formItem1.driverPhone"
          ></el-input>
        </el-form-item>
        <!--图片上传-->
        <el-upload
                class="upload-demo"
                ref="uploadImg"
                action="#"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :file-list="fileList"
                :auto-upload="false"
                :before-upload="beforeUpload"
                :limit="1"
                :accept="'image/*'"
                :http-request="upload"
                list-type="picture">
          <el-button size="medium" type="primary">上传车辆照片<i class="el-icon-upload el-icon--right"></i></el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件
            <br>只能上传一张图片
            <br>且图片大小不能超过15M
          </div>
        </el-upload>
        <!-- 提交和重置按钮 -->
        <el-button type="primary" @click="submitForm()">提交</el-button>
      </el-form><!--普通车辆表单end-->

      <!--机械用具表单-->
      <el-form label-position="left" v-if="!isVehicle">
        <el-form-item label="机械类型">
          <el-select v-model="formItem2.machineModel" placeholder="请选择">
            <!--机械选择组-->
            <el-option-group v-for="group in machineOptionGroup" :key="group.label" :label="group.label" v-if="!isVehicle">
              <!--选项-->
              <el-option v-for="item in group.options" :key="item" :label="item" :value="item"></el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="机械型号">
          <el-input
                  type="text"
                  placeholder="请输入机械型号"
                  v-model.trim="formItem2.machineNumber"
          ></el-input>
        </el-form-item>
        <el-form-item label="发动机编号">
          <el-input
                  type="text"
                  placeholder="请输入发动机编号"
                  v-model.trim="formItem2.engineNumber"
          ></el-input>
        </el-form-item>
        <el-form-item label="驾驶员">
          <el-input
                  type="text"
                  placeholder="请输入驾驶员姓名"
                  v-model.trim="formItem2.driverName"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input
                  type="number"
                  placeholder="请输入驾驶员手机号码"
                  v-model.trim="formItem2.driverPhone"
          ></el-input>
        </el-form-item>
        <!--图片上传-->
        <el-upload
                class="upload-demo"
                ref="uploadImg"
                action="#"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :file-list="fileList"
                :auto-upload="false"
                :before-upload="beforeUpload"
                :limit="1"
                :accept="'image/*'"
                :http-request="upload"
                list-type="picture">
          <el-button size="medium" type="primary">上传车辆照片<i class="el-icon-upload el-icon--right"></i></el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件
            <br>只能上传一张图片
            <br>且图片大小不能超过15M
          </div>
        </el-upload>
        <el-button type="primary" @click="submitForm()">提交</el-button>
      </el-form><!--机械用具表单end-->
    </el-main>

    <el-footer>Copyright©blctek.com</el-footer>

  </el-container>
</template>

<script>

  // 引入网络请求的函数
  import {
    uploadImg,
    uploadVehicle,
    uploadMachine
  } from "../../network/uploadRequest";

  export default {
    name: "Upload",
    components: {

    },
    data() {
      return {
        // 限制上传图片的大小
        limitFileSize : 15,
        // 普通车辆或者机械
        isVehicle: true,
        // 芯片编号
        chipId: '10001',
        formItem1: {
          /*
          *   普通车辆表单
          *   plateType 车牌类型（true新能源，false汽油车）
          *   plateNumber 车牌号
          *   vehicleModel 车辆类型（皮卡、轻卡...）
          *   driverName 驾驶员姓名
          *   driverPhone 驾驶员手机号码
          * */
          plateType: false,
          vehicleModel: '',
          plateNumber: '',
          driverName: '',
          driverPhone: ''
        },
        formItem2: {
          /*
          *   机械表单
          *   machineModel 机械类型（压路机、装载机...）
          *   machineNumber 机械型号（轻卡320...）
          *   engineNumber 机械里发动机编号
          *   driverName 驾驶员姓名
          *   driverPhone 驾驶员手机号
          * */
          machineModel: '',
          machineNumber: '',
          engineNumber: '',
          driverName: '',
          driverPhone: ''
        },
        fileList: [
        ],
        vehicleOptionGroup:[
          //热门车型
          {
            label: '常见车型',
            // 热门车型里的options选项
            options:[
              '皮卡车',
              '双桥车',
            ]
          },
          //车型名
          {
            label: '车型名',
            // 车型名里的options选项
            options: [
              '130轻卡',
              '单桥车',
              '罐车',
              '皮卡车',
              '洒水车',
              '双桥车',
              '四桥车',
            ]
          }
        ],
        machineOptionGroup:[
          //热门车型
          {
            label: '常见机械',
            // 热门车型里的options选项
            options:[
              '挖机',
              '压路机',
            ]
          },
          //车型名
          {
            label: '机械名',
            // 车型名里的options选项
            options: [
                '平地机',
                '压路机',
                '装载机',
                '挖机'
            ]
          }
        ],
      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      /*  重置表单
      * */
      resetForm(){
        this.$refs.uploadImg.clearFiles();
      },
      /*  切换普通车辆或者机械
      * */
      changeIsNormalVehicle(value){
        this.$refs.uploadImg.clearFiles();
        this.isVehicle = value;
      },
      /*  修改车牌号输入框的类型 7<-->8
      * */
      changeInputBoxType(value){
        this.$refs.inputCar.changeDefaultType();
      },
      /*  输入框输入完成后的回调函数
      * */
      getPlateNumber(inputValue){
        console.log('输入的车牌号是->'+inputValue);
        this.formItem1.plateNumber=inputValue;
      },
      /*  比较上传的文件的大小函数（限制文件大小）
      *   满足限定的大小返回true，否则返回false
      * */
      compareFileSize(file,limitFileSize){
        // 文件大小（MB）
        let fileSize = file.size / 1024 / 1024 ;
        if (file.size>0&&file.size<=1024*1024){ //如果文件大小 0<size<=1M
          console.log('准备上传文件大小->',(file.size/1024).toFixed(2)+'Kb'); // 以Kb显示
        } else if (file.size>1024*1024) {
          console.log('准备上传文件大小->',(file.size/1024/1024).toFixed(2)+'M');// 以M显示
        }
        // 文件大小是否小于15M
        console.log('是否小于'+limitFileSize+'M->',fileSize<limitFileSize);
        /* 返回文件大小是否小于15M，是返回true，否则返回false。
         */
        return fileSize<limitFileSize ;
      },
      /* 上传文件前处理函数
      *  限制文件大小
      * */
      beforeUpload(file){
        // 限制上传图片的大小为15M
        let limitFileSize = this.limitFileSize;
        // 待上传的图片是否在范围之内，true小于限制，false大于限制
        let compareFileSizeResult = this.compareFileSize(file,limitFileSize);
        if (this.isVehicle){ // 普通车辆上传前处理
          if (this.chipId!==''
              && this.formItem1.plateType!==null
              && this.formItem1.plateNumber!==''
              && this.formItem1.vehicleModel!==''
              && this.formItem1.driverName!==''
              && this.formItem1.driverPhone!==''
              && compareFileSizeResult
          ) { // 车辆信息如果全部填写完成
            return true;
          } else { //并没有填写完成
            if (!compareFileSizeResult){ //上传的图片大于限制
              this.$notify({
                title: '失败',
                message: '请上传小于'+limitFileSize+'M的图片',
                type: 'error',
                offset: 100
              });
            } else {
              this.$notify({
                title: '失败',
                message: '请填写完整的车辆信息',
                type: 'error',
                offset: 100
              });
            }
            return false;
          }
        } else { // 机械上传前处理
          if (this.chipId!==0
              && this.formItem2.machineModel!==''
              && this.formItem2.machineNumber!==''
              && this.formItem2.engineNumber!==''
              && this.formItem2.driverName!==''
              && this.formItem2.driverPhone!==''
              && compareFileSizeResult
          ) { // 机械信息全部填写完成
            return true;
          } else { // 并没有填写完成
            if (!compareFileSizeResult){ //上传的图片大于限制
              this.$notify({
                title: '失败',
                message: '请上传小于'+limitFileSize+'M的图片',
                type: 'error',
                offset: 100
              });
            } else {
              this.$notify({
                title: '失败',
                message: '请填写完整的机械信息',
                type: 'error',
                offset: 100
              });
            }
            return false;
          }
        }
      },
      /* 上传文件函数
      *  params参数是el-upload里的fileList
      *  */
      upload(param){
        if (this.isVehicle){ // 上传车辆信息
          let formData = new FormData();
          formData.append('file',param.file);
          formData.append('chipId',this.chipId);
          formData.append('plateType',this.formItem1.plateType?'新能源':'汽油车');
          formData.append('plateNumber',this.formItem1.plateNumber);
          formData.append('vehicleModel',this.formItem1.vehicleModel);
          formData.append('driverName',this.formItem1.driverName);
          formData.append('driverPhone',this.formItem1.driverPhone);
          uploadVehicle(formData).then(res=>{ // request成功
            console.log('上传成功->',res);
            this.$notify({
              title: '成功',
              message: res.msg,
              type: 'success',
              offset: 100
            });
          }).catch(error=>{ // request失败
            console.log('上传失败->',error);
            this.$notify({
              title: '失败',
              message: '您的车辆信息上传失败，请联系管理员',
              type: 'error',
              offset: 100
            });
          });
        } else { //上传机械信息
          let formData = new FormData();
          formData.append('file',param.file);
          formData.append('chipId',this.chipId);
          formData.append('machineModel',this.formItem2.machineModel);
          formData.append('machineNumber',this.formItem2.machineNumber);
          formData.append('engineNumber',this.formItem2.engineNumber);
          formData.append('driverName',this.formItem2.driverName);
          formData.append('driverPhone',this.formItem2.driverPhone);
          uploadMachine(formData).then(res=>{ // request成功
            console.log('上传成功->',res);
            this.$notify({
              title: '成功',
              message: res.msg,
              type: 'success',
              offset: 100
            });
          }).catch(error=>{ // request失败
            console.log('上传失败->',error);
            this.$notify({
              title: '失败',
              message: '您的机械信息上传失败，请联系管理员',
              type: 'error',
              offset: 100
            });
          });
        }
      },
      /* 提交上传（调用上传文件函数） */
      submitForm() {
        // 有文件添加进待上传列表才能调用该方法
        this.$refs.uploadImg.submit();
      },
    }
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

  .el-button-group {
    margin-bottom: 20px;
  }

</style>