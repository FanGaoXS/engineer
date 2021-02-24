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
          {{ scope.$index+1 }}
        </template>
      </el-table-column>

      <el-table-column label="类型名" align="center" width="250">
        <template slot-scope="scope">
          {{ scope.row.modelName }}
        </template>
      </el-table-column>

      <el-table-column label="类型描述" :show-overflow-tooltip="true" width="800">
        <template slot-scope="scope">
          <span>{{ scope.row.modelDescription }}</span>
        </template>
      </el-table-column>

      <el-table-column label="类型所属" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.modelBelong }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="250">
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

    <div style="margin-top: 20px">
      <span>添加类型成功后重新扫描二维码即可看到刚刚添加的类型</span>
      <el-divider content-position="left">Tips</el-divider>
    </div>


    <!--新增或修改类型的对话框-->
    <el-dialog
      :title="titleMap[dialogType]"
      :visible.sync="dialogFormVisible"
      width="35%">
      <el-form :model="tempForm" label-width="80px">

        <el-form-item label="类型名" >
          <el-input v-model="tempForm.modelName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="类型描述" >
          <el-input
            type="textarea"
            v-model="tempForm.modelDescription"
            maxlength="200"
            show-word-limit
            rows="8"></el-input>
        </el-form-item>


        <el-form-item label="类型所属" >
          <el-radio-group v-model="tempForm.modelBelong">
            <el-radio border label="车辆"></el-radio>
            <el-radio border label="机械"  disabled></el-radio>
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
import { getList } from '@/api/table'
import {
  getModelByBelong,
  insertModel,
  updateModel,
  deleteModel
} from "@/api/model";

export default {
  filters: {

  },
  data() {
    return {
      modelBelong: '车辆',
      list: [
        /*{
          id,modelName,modelDescription,modelBelong,modelExampleImage
        }*/
      ],
      listLoading: true,
      dialogFormVisible: false,
      tempForm: { //  临时表
        modelName: '',
        modelDescription: '',
        modelBelong: '车辆'
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
    this.fetchData()
  },
  methods: {
    // 填充数据
    async fetchData() {
      this.listLoading = true
      const { data:modelList } = await getModelByBelong(this.modelBelong)
      this.list = modelList;
      this.listLoading = false;
    },
    // 清空临时表单
    resetTemp(){
      this.tempForm = {
        modelName: '',
        modelDescription: '',
        modelBelong: '车辆'
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
      // console.log(row);
      this.dialogFormVisible = true;
      this.dialogType = 'update';
      this.resetTemp();
      this.tempForm = Object.assign({}, row) // copy obj
    },
    // 处理删除
    handleDelete(row,index) {
      console.log(row);
      console.log(index);
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
        // console.log(res.data)
        // this.list.push(this.tempForm);
        this.fetchData();
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
      console.log(this.tempForm);
      updateModel(this.tempForm).then(res=>{
        // console.log(res.data)
        this.dialogFormVisible = false;
        let index = this.list.findIndex(v=> v.id === this.tempForm.id )
        // console.log(index);
        this.list.splice(index,1,this.tempForm);
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
    deleteModel(id,index){
      console.log(index)
      console.log(id)
      deleteModel(id).then(res=>{
        // console.log(res.data)
        this.$notify({
          type: 'success',
          message: '删除类型成功'
        })
        this.list.splice(index,1);
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
