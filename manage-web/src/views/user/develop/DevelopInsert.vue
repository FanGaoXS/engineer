<template>
  <el-container>
    <el-main>
      <el-form label-position="right" label-width="80px" :model="formItem">
        <el-form-item label="姓名" required>
          <el-input
                  type="text"
                  v-model="formItem.commonName"
                  placeholder="请输入姓名"
                  autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="别名" required>
          <el-input
                  type="text"
                  v-model="formItem.surName"
                  placeholder="请输入别名"
                  autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="用户名" required>
          <el-input
                  type="text"
                  v-model="formItem.userName"
                  placeholder="请输入用户名"
                  autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="密码" required>
          <el-input
                  type="password"
                  v-model="formItem.passWord"
                  placeholder="请输入密码"
                  autocomplete="off"
          />
        </el-form-item>
        <el-form-item size="medium">
          <el-button type="primary" @click="submitForm()">立即注册</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
  import {
    insertDevelop
  } from "../../../network/developRequset";

  export default {
    name: "DevelopInsert",
    data() {
      return {
        formItem: {
          commonName: '',
          surName: '',
          userName: '',
          passWord: ''
        }
      }
    },
    methods: {
      // 提交表单
      submitForm() {
        let formData = new FormData();
        formData.append('commonName',this.formItem.commonName);
        formData.append('surName',this.formItem.surName);
        formData.append('userName',this.formItem.userName);
        formData.append('passWord',this.formItem.passWord);
        insertDevelop(formData).then(res=>{
          console.log(res);
        }).catch(error=>{
          console.log(error);
        })
      }
    },
  }
</script>

<style scoped>

  .el-container {
    text-align: center;
  }

  .el-header, .el-footer {
    /*background-color: #B3C0D1;*/
    color: #333;
    line-height: 40px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
  }

  .el-form {
    text-align: left;
    width: 450px;
  }
</style>