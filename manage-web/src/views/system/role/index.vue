<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button
        type="primary"
        size="medium"
        round
        icon="el-icon-upload"
        :disabled="!checkPermission(['admin','developer'])"
        @click="handleInsert">添加</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" align="center" width="100">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>

      <el-table-column label="角色名" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>

      <el-table-column label="角色中文名" align="center">
        <template slot-scope="scope">
          {{ scope.row.chineseName }}
        </template>
      </el-table-column>

      <el-table-column label="角色描述" align="center" width="300">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="medium"
            round
            icon="el-icon-edit"
            :disabled="!checkPermission(['admin','developer'])"
            @click="handleUpdate(scope.row,scope.$index)">修改</el-button>
          <el-button
            type="danger"
            size="medium"
            round
            icon="el-icon-delete"
            @click="handleDelete(scope.row,scope.$index)"
          :disabled="(scope.row.name==='admin')||!(checkPermission(['admin','developer']))">删除</el-button>
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

    <el-dialog
      :title="dialogType+'角色'"
      width="35%"
      @closed="dialogClose"
      :visible.sync="dialogFormVisible">
      <el-form :model="tempForm" label-width="100px" ref="dialogForm" :rules="rules">

        <el-form-item label="序号" prop="id" v-show="dialogType==='修改'" >
          <el-input v-model="tempForm.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="角色名" prop="name">
          <el-input v-model="tempForm.name" autocomplete="off" :disabled="dialogType==='修改'"></el-input>
        </el-form-item>

        <el-form-item label="角色中文名" prop="chineseName">
          <el-input v-model="tempForm.chineseName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="角色描述" prop="description">
          <el-input
            type="textarea"
            v-model="tempForm.description"
            maxlength="200"
            show-word-limit
            rows="8">
          </el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button
          size="medium"
          round
          @click="dialogFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          size="medium"
          round
          @click="dialogType==='新增'?insertRole():updateRole()"
          :loading="buttonLoading"
          :disabled="buttonLoading"
        >确定</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
  import {
    deleteRole,
    getRoleList,
    insertRole,
    updateRole
  } from "@/api/role";

  import {
    validIsEnglish,
    validIsChinese
  } from '@/utils/validate'

  import checkPermission from "@/utils/permission";

  export default {
    name: "index",
    data() {
      const validateRoleName = (rule, value, callback) => {
        if (!validIsEnglish(value)) {
          callback(new Error('请输入正确的角色名：只能是英文字母'))
        } else {
          callback()
        }
      }
      const validateRoleChineseName = (rule, value, callback) => {
        if (!validIsChinese(value)) {
          callback(new Error('请输入正确的角色中文名：只能是中文字符'))
        } else {
          callback()
        }
      }
      return {
        rules: {
          name: [
            { required: true, min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' },
            { trigger: 'blur', validator: validateRoleName }
          ],
          chineseName: [
            { required: true, min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' },
            { trigger: 'blur', validator: validateRoleChineseName }
          ],
          description: [
            { required: true, min: 3, max: 200, message: '长度在 3 到 200 个字符', trigger: 'blur' },
          ]
        },
        buttonLoading: false,
        tempForm: {
          id: undefined,
          name: undefined,
          chineseName: undefined,
          description: undefined
        },
        dialogFormVisible: false,
        dialogType: '',
        listQuery: {
          currentPage: 1,
          pageSize: 10,
          totalSize: 0
        },
        listLoading: true,
        list: []
      }
    },
    created() {
      this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
    },
    methods: {
      // 清空对话框表单
      resetTempForm(){
        this.tempForm = {
          id: undefined,
          name: undefined,
          chineseName: undefined,
          description: undefined
        }
      },
      //对话框关闭的回调钩子
      dialogClose(){
        this.resetTempForm()//对话框关闭时清空对话框表单
        this.$refs['dialogForm'].clearValidate() //清空对话框的验证
      },
      handleInsert(){
        this.dialogFormVisible = true
        this.dialogType = '新增'
      },
      handleUpdate(row,index){
        this.dialogFormVisible = true
        this.dialogType = '修改'
        this.tempForm = Object.assign({}, row) // copy obj
      },
      handleDelete(row,index){
        this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          this.deleteRole(row.id)
        }).catch(()=>{})
      },
      insertRole(){
        this.$refs['dialogForm'].validate(valid=>{
          if (valid){
            this.buttonLoading = true
            insertRole(this.tempForm).then(res=>{
              this.$notify.success({
                title: '成功',
                message: res.message + ' 成功！'
              })
              this.dialogFormVisible = false
              this.buttonLoading = false
              this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
            }).catch(error=>{
              this.buttonLoading = false
            })
          }
        })
      },
      updateRole(){
        this.$refs['dialogForm'].validate(valid=>{
          if (valid){
            this.buttonLoading = true
            updateRole(this.tempForm).then(res=>{
              this.$notify.success({
                title: '成功',
                message: res.message + ' 成功！'
              })
              this.dialogFormVisible = false
              this.buttonLoading = false
              this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
            }).catch(error=>{
              this.buttonLoading = false
            })
          }
        })
      },
      deleteRole(id){
        deleteRole(id).then(res=>{
          this.$notify.success({
            title: '成功',
            message: res.message + ' 成功！'
          })
          this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
        })
      },
      handleListQueryChange(){
        this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
      },
      async fetchList(currentPage,pageSize) {
        this.listLoading = true
        const { data:roleList } = await getRoleList(currentPage,pageSize)
        this.list = roleList.items
        this.listQuery.currentPage = roleList.currentPage
        this.listQuery.pageSize = roleList.pageSize
        this.listQuery.totalSize = roleList.totalSize
        this.listLoading = false
      },
      checkPermission
    },
  }
</script>

<style scoped>

</style>
