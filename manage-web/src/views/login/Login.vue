<template>
  <div id="login">
    <el-container>
      <el-header></el-header>
      <el-main>
        <!--登录表单-->
        <el-form label-position="right" label-width="80px" v-model="form" class="login-box">
          <h3 class="login-title">请登录</h3>
          <el-form-item label="用户名"><!--用户名输入框-->
            <el-input
                    type="text"
                    placeholder="请输入用户名"
                    v-model="form.username"
            />
          </el-form-item>
          <el-form-item label="密码"><!--密码输入框-->
            <el-input
                    type="password"
                    placeholder="请输入密码"
                    v-model="form.password"
            />
          </el-form-item>
          <!--登录注册按钮组-->
          <el-button type="primary" @click="onSubmit()">登录</el-button>
          <!--<el-button type="primary">注册</el-button>-->
        </el-form>

      </el-main>
      <el-footer>Copyright©blctek.com</el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        form: {
          username: '',
          password: ''
        }
      }
    },
    methods: {
      onSubmit() {
        if (this.form.username!==''&&this.form.password!==''){
          this.$router.push('/main');
        }
      }
    },
  }
</script>

<style scoped>
  .el-header, .el-footer {
    /*background-color: #B3C0D1;*/
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-main {
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 100px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 20px #909399;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }

</style>