<template>
  <div class="app-container">
    <el-card shadow="hover">
      <el-form :model="form" label-width="80px" :rules="rules" ref="form">
        <el-row>
          <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
            <el-form-item label="用户名" prop="username">
              <el-input :value="form.username" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
            <el-form-item label="旧密码" prop="oldPassword">
              <el-input v-model="form.oldPassword" type="password" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
            <el-form-item label="新密码" prop="newPassword">
              <el-input v-model="form.newPassword" type="password" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
            <el-form-item label="确认密码" prop="checkPassword">
              <el-input v-model="form.checkPassword" type="password" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button
            size="medium"
            round
            type="primary"
            @click="submitForm('form')"
            v-permission="['admin','developer','supervisor']"
            :loading="buttonLoading">提交</el-button>
          <el-button
            round
            size="medium"
            @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import {
    validPassword
  } from "@/utils/validate";
  import {
    updatePassword
  } from "@/api/user/profile";
  import permission from "@/directive/permission/permission";

  export default {
    name: "index",
    directives:{
      permission
    },
    data() {
      const validatorPassword = (rule, value, callback) => {
        if (!validPassword(value)){
          callback(new Error('请输入正确的密码：至少5位'))
        } else {
          callback()
        }
      }
      const validatorCheckPassword = (rule, value, callback) => {
        if (value===this.form.newPassword){
          callback()
        } else {
          callback(new Error('两次密码不一致！'))
        }
      }
      return {
        rules:{
          username:[
            { required: true, message: '请输入用户名', trigger: 'blur'}
          ],
          oldPassword: [
            { required: true, message: '请输入旧密码', trigger: 'blur'},
            { validator: validatorPassword, trigger: 'blur'}
          ],
          newPassword: [
            { required: true, message: '请输入新密码', trigger: 'blur'},
            { validator: validatorPassword, trigger: 'blur'}
          ],
          checkPassword: [
            { required: true, message: '请再次输入密码', trigger: 'blur'},
            { validator: validatorCheckPassword, trigger: 'blur'}
          ]
        },
        form: {
          id: undefined,
          username: undefined,
          oldPassword: undefined,
          newPassword: undefined,
          checkPassword: undefined
        },
        buttonLoading: false,
        formColSpan:{ xs:24, sm:18, md:12, lg:10 }
      }
    },
    created() {
      this.form.id = this.id
      this.form.username = this.username
    },
    methods: {
      //提交表单
      submitForm(formName) {
        this.$refs[formName].validate(valid=>{ //验证表单
          if (valid){ //验证通过
            this.buttonLoading = true //按钮进入加载
            //向后端发起修改密码的请求
            updatePassword(this.form).then(async res=>{ //请求成功
              this.$notify({
                type: res.data?'success':'error',
                title: res.data?'成功，请重新登录！':'失败',
                message: '修改密码'+(res.data===true?'成功':'失败')
              })
              if (res.data){ //如果修改密码成功，则强制退出，让用户重新登录
                await this.$store.dispatch('user/logout')
                this.$router.push('/login') //重定向到login
              }
            }).catch(error=>{ //请求失败
              this.buttonLoading = false
              this.resetForm('form') //清空表单
            })
          } else {
            console.log('错误的提交')
          }
        })
      },
      //清空表单
      resetForm(formName) {
        this.$refs[formName].resetFields()
      },
    },
    computed: {

      id() {
        return this.$store.state.user.id
      },
      username() {
        return this.$store.state.user.username
      }
    },
  }
</script>

<style scoped>

</style>
