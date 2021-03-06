<template>
  <div class="app-container">

    <div class="filter-container">

      <el-button type="primary" size="medium" @click="handleInsert">
        添加
      </el-button>

    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="75">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>

      <el-table-column label="类型名" align="center" width="250">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>

      <el-table-column label="类型描述" :show-overflow-tooltip="true" width="800">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
        </template>
      </el-table-column>

      <el-table-column label="类型所属" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.type }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="medium" @click="handleUpdate(scope.row)">
            修改
          </el-button>
          <el-button type="danger" size="medium" @click="handleDelete(scope.row,scope.$index)">
            删除
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-pagination
      style="margin-top: 15px"
      background
      :total="listQuery.totalSize"
      :page-size="listQuery.pageSize"
      :current-page.sync="listQuery.currentPage"
      @current-change="handleCurrentChange"
      layout="total, prev, pager, next, jumper">
    </el-pagination>


    <!--新增或修改类型的对话框-->
    <el-dialog
      :title="titleMap[dialogType]"
      :visible.sync="dialogFormVisible"
      width="35%">
      <el-form :model="tempForm" label-width="80px">

        <el-form-item label="类型名" >
          <el-input v-model="tempForm.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="类型描述" >
          <el-input
            type="textarea"
            v-model="tempForm.description"
            maxlength="200"
            show-word-limit
            rows="8"></el-input>
        </el-form-item>


        <el-form-item label="类型所属" >
          <el-radio-group v-model="tempForm.type">
            <el-radio border label="车辆" disabled></el-radio>
            <el-radio border label="机械"></el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogType==='update'?updateModel():insertModel()">确 定</el-button>
      </div>
    </el-dialog>
    <!--新增车辆类型对话框-->

  </div>
</template>

<script>

import {
  getModelByMachine,
  insertModel,
  updateModel,
  deleteModel
} from "@/api/model";

export default {
  data() {
    return {
      listQuery:{
        currentPage: 1,
        pageSize: 10,
        totalSize: 0
      },
      list: [

      ],
      listLoading: true,
      dialogFormVisible: false,
      tempForm: { //  临时表
        id: 0,
        name: '',
        description: '',
        type: '机械'
      },
      dialogType: '',
      titleMap: { //  对话框标题映射
        'update': '修改类型',
        'insert': '新增类型'
      },
    }
  },
  created() {
    // 组件被创建时填充数据
    this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
  },
  methods: {
    handleCurrentChange(){
      this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
    },
    // 填充数据
    async fetchList(currentPage,pageSize) {
      this.listLoading = true
      const { data:modelList } = await getModelByMachine(currentPage,pageSize)
      this.list = modelList.items;
      this.listQuery.pageSize = modelList.pageSize
      this.listQuery.currentPage = modelList.currentPage
      this.listQuery.totalSize = modelList.totalSize
      this.listLoading = false;
    },
    // 清空临时表单
    resetTemp(){
      this.tempForm = {
        id: 0,
        name: '',
        description: '',
        type: '机械'
      }
    },
    // 处理新增（清空临时表，打开对话框）
    handleInsert(){
      this.dialogFormVisible = true;
      this.dialogType = 'insert';
      this.resetTemp();
    },
    // 处理修改（清空临时表，打开对话框，将该行数据填充）
    handleUpdate(row){
      this.dialogFormVisible = true;
      this.dialogType = 'update';
      this.resetTemp();
      this.tempForm = Object.assign({}, row) // copy obj
    },
    // 处理删除
    handleDelete(row,index) {
      this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteModel(row.id, index);
      }).catch()
    },
    //  新增类型
    insertModel(){
      insertModel(this.tempForm).then(res=>{
        this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize);//新增完成后重新加载数据
        this.dialogFormVisible = false;
        this.$notify({
          type: 'success',
          message: '新增类型成功！'
        })
        this.dialogFormVisible = false;
      }).catch(error=>{
        console.log(error);
        this.$notify({
          type: 'error',
          message: '新增类型失败，请联系管理员：'+error
        });
      })
    },
    //  修改类型
    updateModel(){
      updateModel(this.tempForm).then(res=>{
        this.dialogFormVisible = false;
        let index = this.list.findIndex(v=> v.id === this.tempForm.id )
        this.list.splice(index,1,this.tempForm); //用新增后的数据替换新增前的数据
        this.$notify({
          type: 'success',
          message: '修改类型成功！'
        })
      }).catch(error=>{
        console.log(error);
        this.$notify({
          type: 'error',
          message: '修改类型失败，请联系管理员：'+error
        });
      })
    },
    // 删除类型
    deleteModel(id){
      deleteModel(id).then(res=>{
        this.$notify({
          type: 'success',
          message: '删除类型成功'
        })
        this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
      }).catch(error=>{
        console.log(error);
        this.$notify({
          type: 'success',
          message: '删除类型失败，请联系管理员：'+error
        })
      })
    },
  }
}
</script>
