<template>
  <div class="app-container">
    <el-form :model="form" label-width="80px" :rules="rules" ref="form">
      <el-row><!--自适应的编号框-->
        <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
          <el-form-item label="编号">
            <span>{{form.id}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row><!--自适应的用户名框-->
        <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
          <el-form-item label="用户名">
            <span>{{form.username}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row><!--自适应的头像框-->
        <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
          <el-form-item label="头像">
            <el-avatar :size="80">{{form.name}}</el-avatar>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row><!--自适应的姓名框-->
        <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
          <el-form-item label="姓名" prop="name">
            <span v-show="!isUpdate">{{form.name}}</span>
            <el-input v-show="isUpdate" v-model="form.name" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row><!--自适应的联系方式框-->
        <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
          <el-form-item label="联系方式" prop="phone">
            <span v-show="!isUpdate">{{form.phone}}</span>
            <el-input v-show="isUpdate" v-model="form.phone" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row><!--自适应的角色框-->
        <el-col :xs="formColSpan.xs" :sm="formColSpan.sm" :md="formColSpan.md" :lg="formColSpan.lg">
          <el-form-item label="角色">
            <span>{{form.role.chineseName}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>

        </el-col>
      </el-row>
      <el-form-item v-show="!isUpdate">
        <el-button
          @click="isUpdate = true"
          type="primary"
          icon="el-icon-edit"
          size="medium"
          :disabled="$store.state.user.roles[0]==='ghost'"
          round>修改信息</el-button>
        <el-button
          @click="$router.push('Password')"
          type="primary"
          icon="el-icon-edit"
          size="medium"
          :disabled="$store.state.user.roles[0]==='ghost'"
          round>修改密码</el-button>
      </el-form-item>
      <el-form-item v-show="isUpdate">
        <el-button
          @click="submitUpdate('form')"
          type="primary"
          icon="el-icon-check"
          :loading="buttonLoading"
          :disabled="buttonLoading"
          size="medium"
          round>确认修改</el-button>
        <el-button
          @click="cancelUpdate('form')"
          type="danger"
          icon="el-icon-delete"
          size="medium"
          round>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

  import {
    getUser,
  } from "@/api/user/user";

  import {
    updateInfo
  } from "@/api/user/profile";
  import {
    validateName,
    validatePhone
  } from "@/utils/validate";

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
        rules:{
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { validator: validatorName, trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '请输入联系方式', trigger: 'blur' },
            { validator: validatorPhone, trigger: 'blur' }
          ]
        },
        form: {
          id: undefined,
          name: undefined,
          phone: undefined,
          avatar: undefined,
          role:{
            chineseName: undefined
          }
        },
        isUpdate: false,
        buttonLoading: false,
        formColSpan:{ xs:24, sm:18, md:12, lg:10 }
      }
    },
    created() {
      this.fetchData(this.id)
    },
    methods: {
      submitUpdate(formName){
        this.$refs[formName].validate(valid=>{
          if (valid){
            this.buttonLoading = true
            updateInfo(this.form).then(res=>{
              this.$notify({
                type: res.data===true?'success':'error',
                title: res.data===true?'成功':'失败',
                message: '修改个人信息 '+(res.data===true?'成功':'失败')
              })
              this.refreshView(2)
            }).catch(error=>{
              this.$notify.error({
                title: '失败',
                message: '修改个人信息失败，请联系管理员：'+error
              })
              this.buttonLoading = false
              this.isUpdate = false
            })
          }
        })
      },
      cancelUpdate(formName){
        this.isUpdate = false
        this.$refs[formName].clearValidate()
        this.fetchData(this.id)
      },
      // 根据id填充个人信息表单
      async fetchData(id) {
        const { data } = await getUser(id)
        this.form = data
      },
      //刷新页面（目的是重新请求info，重新加载vuex中的全局个人信息）
      refreshView(second=2){
        setTimeout(()=>{
          this.isUpdate = false
          this.buttonLoading = false
          this.$router.go(0)
        },1000*second) //多少秒后刷新当前页
      },
    },
    computed: {
      id(){
        return this.$store.state.user.id
      },
    },
  }
</script>

<style scoped>

</style>
