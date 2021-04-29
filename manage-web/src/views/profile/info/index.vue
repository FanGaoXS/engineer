<template>
  <div class="app-container">
    <el-card shadow="hover">
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="6">
          <el-form :model="form" label-width="80px" :rules="rules" ref="form">
            <el-row><!--自适应的编号框-->
              <el-col>
                <el-form-item label="编号">
                  <span>{{form.id}}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row><!--自适应的用户名框-->
              <el-col>
                <el-form-item label="用户名">
                  <span>{{form.username}}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row><!--自适应的头像框-->
              <el-col>
                <el-form-item label="头像">
                  <el-avatar :size="80">{{form.name}}</el-avatar>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row><!--自适应的姓名框-->
              <el-col span="15">
                <el-form-item label="姓名" prop="name">
                  <span v-show="!isUpdate">{{form.name}}</span>
                  <el-input v-show="isUpdate" v-model="form.name" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row><!--自适应的联系方式框-->
              <el-col span="15">
                <el-form-item label="联系方式" prop="phone">
                  <span v-show="!isUpdate">{{form.phone}}</span>
                  <el-input v-show="isUpdate" v-model="form.phone" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row><!--自适应的角色框-->
              <el-col>
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
                v-permission="['admin','developer','supervisor']"
                round>修改信息</el-button>
              <el-button
                @click="$router.push('Password')"
                type="primary"
                icon="el-icon-edit"
                size="medium"
                v-permission="['admin','developer','supervisor']"
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
        </el-col>
        <el-col :xs="24" :sm="24" :md="16" :lg="18">
          <div class="filter-container">

            <el-select placeholder="根据客户端查询" v-model="listQuery.client" clearable @change="handleListQueryChange" style="margin-left: 15px">
              <el-option
                v-for=" item in clientOptions"
                :key="item"
                :label="item"
                :value="item"
              ></el-option>
            </el-select>

          </div>

          <el-table
            v-loading="listLoading"
            style="width: 100%"
            :data="list"
            element-loading-text="加载中"
            border
            fit
            highlight-current-row
            :default-sort = "{prop: 'time', order: 'descending'}"
          >

            <!--<el-table-column label="序号" align="center" width="100">
              <template slot-scope="scope">
                {{ scope.row.id }}
              </template>
            </el-table-column>-->

            <el-table-column label="操作名" align="center" min-width="200">
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>

            <el-table-column label="来源客户端" align="center" min-width="200">
              <template slot-scope="scope">
                {{ scope.row.client }}
              </template>
            </el-table-column>

            <!--<el-table-column label="操作者" align="center">
              <template slot-scope="scope">
                {{ scope.row.executor }}
              </template>
            </el-table-column>-->

            <el-table-column label="操作时间" align="center" min-width="300" sortable prop="time">
              <template slot-scope="scope">
                <i class="el-icon-time" />
                {{ scope.row.time | timeFilter }}
              </template>
            </el-table-column>

            <el-table-column label="ip地址" align="center" min-width="200">
              <template slot-scope="scope">
                {{ scope.row.ip }}
              </template>
            </el-table-column>

          </el-table>
          <el-pagination
            style="margin-top: 15px"
            background
            :total="listQuery.totalSize"
            :page-size="listQuery.pageSize"
            :current-page.sync="listQuery.currentPage"
            @current-change="handleListQueryChange"
            layout="total, prev, pager, next, jumper">
          </el-pagination>
        </el-col>
      </el-row>

    </el-card>
  </div>
</template>

<script>

  import { mapGetters } from 'vuex'

  import {
    timeFilter
  } from "@/utils/global-filters";

  import {
    getLoggerList
  } from "@/api/logger";

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

  import permission from "@/directive/permission/permission";

  export default {
    name: "index",
    directives:{
      permission
    },
    filters: {
      timeFilter
    },
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
        listLoading: true,
        list: [ ],
        listQuery: {
          currentPage: 1,
          pageSize: 10,
          totalSize: 0,
          client: undefined
        },
        clientOptions: [ ]
      }
    },
    created() {
      this.fetchData(this.id)
      this.fetchClientOptions()
      this.fetchLoggerList()
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
      handleListQueryChange(){
        this.fetchLoggerList()
      },
      async fetchLoggerList() {
        this.listLoading = true
        const { data:loggerList } = await getLoggerList(this.name,this.listQuery.client,this.listQuery.currentPage, this.listQuery.pageSize)
        this.list = loggerList.items
        this.listQuery.currentPage = loggerList.currentPage
        this.listQuery.pageSize = loggerList.pageSize
        this.listQuery.totalSize = loggerList.totalSize
        this.listLoading = false
      },
      fetchClientOptions(){
        this.clientOptions = [ 'manage-web', 'postman', 'upload-web', '未知' ];
      }
    },
    computed: {
      ...mapGetters([
        'id',
        'name'
      ])
    },
  }
</script>

<style scoped>
  @media (max-width:550px)  {
    .el-button {
      width: 80%;
      margin-top: 20px;
      margin-left: -10px;
    }
    .el-select {
      width: 45%;
    }
  }
</style>
