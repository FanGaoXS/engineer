<template>
  <div class="app-container">
    <el-card shadow="hover">
      <div class="filter-container">
        <el-select placeholder="根据角色查询" v-model="listQuery.roleId" clearable @change="handleListQueryConditionChange">
          <el-option
            v-for=" item in roleOptions"
            :key="item.name"
            :label="item.chineseName"
            :value="item.id"
          ></el-option>
        </el-select>
        <el-button
          type="primary"
          size="medium"
          round
          icon="el-icon-upload"
          v-permission="['admin','developer']"
          @click="handleInsert" style="margin-left: 15px">添加</el-button>
      </div>
      <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="加载中"
        border
        fit
        highlight-current-row
      >
        <!--<el-table-column label="序号" align="center" width="150">
          <template slot-scope="scope">
            {{ scope.row.id }}
          </template>
        </el-table-column>-->

        <el-table-column label="姓名" align="center" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>

        <el-table-column label="用户名" align="center" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.username }}
          </template>
        </el-table-column>

        <el-table-column label="联系方式" align="center" min-width="300">
          <template slot-scope="scope">
            {{ scope.row.phone }}
          </template>
        </el-table-column>

        <el-table-column label="角色" align="center">
          <template slot-scope="scope">
            {{ scope.row.role.chineseName }}
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" min-width="250">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="medium"
              round
              icon="el-icon-edit"
              :disabled="scope.row.role.name==='admin'"
              @click="handleUpdate(scope.row,scope.$index)"
              v-permission="['admin','developer']">
            修改</el-button>
            <el-button
              type="danger"
              size="medium"
              round
              icon="el-icon-delete"
              @click="handleDelete(scope.row,scope.$index)"
              :disabled="scope.row.role.name==='admin'"
              v-permission="['admin','developer']">
            删除</el-button>
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

      <!--新增或修改的对话框-->
      <el-dialog
        :title="dialogType+'用户'"
        custom-class="dialogWidth"
        @closed="dialogClose"
        :visible.sync="dialogFormVisible">
        <el-form :model="tempForm" label-width="100px" ref="dialogForm" :rules="rules">

          <el-form-item label="序号" prop="id" v-show="dialogType==='修改'" >
            <el-input v-model="tempForm.id" disabled></el-input>
          </el-form-item>

          <el-form-item label="用户名" prop="username">
            <el-input v-model="tempForm.username" autocomplete="off" :disabled="dialogType==='修改'"></el-input>
          </el-form-item>

          <el-form-item label="姓名" prop="name">
            <el-input v-model="tempForm.name" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="联系方式" prop="phone">
            <el-input v-model="tempForm.phone" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item :label="dialogType==='新增'?'分配角色':'修改角色'" prop="roleId">
            <el-select placeholder="选择角色" v-model="tempForm.roleId" clearable>
              <el-option
                v-for=" item in roleOptions"
                :key="item.name"
                :label="item.chineseName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>

        </el-form>

        <div v-show="dialogType==='新增'" style="margin-top: 30px">
          <el-divider content-position="left">
            初始登录密码为：用户名+123
          </el-divider>
        </div>

        <div slot="footer" class="dialog-footer">
          <el-button
            size="medium"
            round
            @click="dialogFormVisible = false">取消</el-button>
          <el-button
            type="primary"
            size="medium"
            round
            @click="dialogType==='新增'?insertUser():updateUser()"
            :loading="buttonLoading"
            :disabled="buttonLoading"
          >确定</el-button>
        </div>

      </el-dialog>
    </el-card>
  </div>
</template>

<script>
  import {
    getUserList,
    insertUser,
    deleteUser,
    updateUser
  } from "@/api/user/user";
  import {
    getRoleList
  } from "@/api/role";

  import {
    validUsername,
    validateName,
    validatePhone
  } from "@/utils/validate";

  import permission from "@/directive/permission/permission";

  export default {
    directives:{
      permission
    },
    name: "index",
    data() {
      const validatorUsername = (rule, value, callback) => {
        if (!validUsername(value)) {
          callback(new Error('请输入正确的用户名：5到16位（字母，数字，下划线，减号'))
        } else {
          callback()
        }
      }
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
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur'},
            { validator: validatorUsername, trigger: 'blur', }
          ],
          name: [
            { required: true, validator: validatorName, trigger: 'blur'}
          ],
          phone: [
            { required: true, validator: validatorPhone, trigger: 'blur'}
          ],
          roleId: [
            { required: true, message: '请选择角色', trigger: 'blur'}
          ]
        },
        buttonLoading: false,
        dialogFormVisible: false,
        dialogType: '',
        tempForm: {
          id: undefined,
          username: undefined,
          name: undefined,
          phone: undefined,
          roleId: undefined
        },
        listLoading: false,
        listQuery:{
          roleId: undefined,
          currentPage: 1,
          pageSize: 10,
          totalSize: 0
        },
        list: [],
        roleOptions: []
      }
    },
    created() {
      this.fetchRoleOptions();
      this.fetchList();
    },
    methods: {
      // 清空对话框表单
      resetTempForm(){
        this.tempForm = {
          id: undefined,
          username: undefined,
          name: undefined,
          phone: undefined,
          roleId: undefined
        }
      },
      dialogClose(){
        this.$refs['dialogForm'].clearValidate() //清空对话框的验证
        this.resetTempForm()//对话框关闭时清空对话框表单
      },
      handleInsert(){
        this.dialogFormVisible = true
        this.dialogType = '新增'
      },
      handleUpdate(row,index){
        // console.log(row)
        this.dialogFormVisible = true
        this.dialogType = '修改'
        this.tempForm = {
          id: row.id,
          username: row.username,
          name: row.name,
          phone: row.phone,
          roleId: row.role.id
        } // copy obj
      },
      handleDelete(row,index){
        this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          this.deleteUser(row.id)
        }).catch(()=>{})
      },
      insertUser(){
        this.$refs['dialogForm'].validate(valid=>{
          if (valid) { //表单验证通过
            this.buttonLoading = true
            insertUser(this.tempForm).then(res=>{
              this.$notify.success({
                title: '成功',
                message: res.message + ' 成功！'
              })
              this.dialogFormVisible = false
              this.buttonLoading = false
              this.fetchList(this.listQuery.roleId,this.listQuery.currentPage,this.listQuery.pageSize);
            }).catch(error=>{
              console.log(error)
              this.buttonLoading = false
            })
          }
        })
      },
      updateUser(){
        this.$refs['dialogForm'].validate(valid=>{
          if (valid) { //验证通过
            this.buttonLoading = true
            updateUser(this.tempForm).then(res=>{
              this.$notify.success({
                title: '成功',
                message: res.message + ' 成功！'
              })
              this.dialogFormVisible = false
              this.buttonLoading = false
              this.fetchList(this.listQuery.roleId,this.listQuery.currentPage,this.listQuery.pageSize);
            }).catch(error=>{
              console.log(error)
              this.buttonLoading = false
            })
          }
        })
      },
      deleteUser(id){
        deleteUser(id).then(res=>{
          this.$notify.success({
            title: '成功',
            message: res.message + ' 成功！'
          })
          this.fetchList(this.listQuery.roleId,this.listQuery.currentPage,this.listQuery.pageSize);
        })
      },
      handleListQueryConditionChange(){ //利用关键字查询分页时，将当前页置为1再重新请求数据
        this.listQuery.currentPage = 1
        this.fetchList()
      },
      handleListQueryChange(){
        this.fetchList();
      },
      //拉取表格数据
      async fetchList() {
        this.listLoading = true
        const { data:userList } = await getUserList(this.listQuery.roleId,this.listQuery.currentPage,this.listQuery.pageSize)
        this.list = userList.items
        this.listQuery.currentPage = userList.currentPage
        this.listQuery.pageSize = userList.pageSize
        this.listQuery.totalSize = userList.totalSize
        this.listLoading = false
      },
      //拉取角色数据
      fetchRoleOptions(){
        getRoleList(null,null).then(res=>{
          this.roleOptions = res.data.items;
        })
      }
    },
  }
</script>

<style>
  .dialogWidth{
    width: 35%;
  }
  @media (max-width:1000px)  {
    .el-select {
      width: 45%;
    }
    .dialogWidth{
      width: 90%;
    }
  }
</style>
