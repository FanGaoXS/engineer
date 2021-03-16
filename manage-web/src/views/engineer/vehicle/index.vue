<template>
  <div class="app-container">

    <!--<div class="filter-container">
      <el-button class="filter-item" type="primary" icon="el-icon-edit">
        添加
      </el-button>
    </div>-->

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" align="center" width="75">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>

      <el-table-column label="车牌号" align="center" width="250">
        <template slot-scope="scope">
          {{ scope.row.vehicleNumber | plateNumberFilter }}
        </template>
      </el-table-column>

      <el-table-column label="车辆图片" align="center" width="200">
        <template slot-scope="scope">
          <!--图片预览，支持放大-->
          <el-image
            style="width: 100px;height: 50px"
            :src="IMAGE_PREFIX_URL+scope.row.uuid"
            fit="cover"
            :preview-src-list="[IMAGE_PREFIX_URL+scope.row.uuid]">
          </el-image>
        </template>
      </el-table-column>



      <el-table-column label="车牌类型" align="center" width="150">
        <template slot-scope="scope">
          <el-tag :type=" scope.row.plateType | plateTypeFilter ">
            {{ scope.row.plateType }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="车辆类型" align="center" width="150">
        <template slot-scope="scope">
          {{ scope.row.model.name }}
        </template>
      </el-table-column>

      <el-table-column label="所属工程用具" align="center" width="150">
        <template slot-scope="scope">
          {{ scope.row.type }}
        </template>
      </el-table-column>

      <el-table-column label="百公里油耗" align="center">
        <template slot-scope="scope">
          7.5（升/百公里）
        </template>
      </el-table-column>

      <el-table-column label="驾驶员姓名" align="center" width="150">
        <template slot-scope="scope">
          {{ scope.row.driver.name }}
        </template>
      </el-table-column>

      <el-table-column label="驾驶员电话号码" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.driver.phone }}
        </template>
      </el-table-column>


      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <el-button type="primary" size="medium" @click="handleUpdate(scope.row,scope.$index)">修改</el-button>
          <el-button type="danger" size="medium" @click="handleDelete(scope.row,scope.$index)">删除</el-button>
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

    <!--修改车辆信息的对话框-->
    <el-dialog
      title="修改车辆信息"
      :visible.sync="dialogFormVisible"
      width="35%">
      <el-form :model="tempForm" label-width="150px">

        <el-form-item label="车辆编号" >
          <el-input :value="tempForm.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="车牌号" >
          <el-input v-model="tempForm.vehicleNumber"></el-input>
        </el-form-item>

        <el-form-item label="车牌类型" >
          <el-radio v-model="tempForm.plateType" label="汽油车" border>汽油车</el-radio>
          <el-radio v-model="tempForm.plateType" label="新能源" border>新能源</el-radio>
        </el-form-item>

        <el-form-item label="车辆类型" >
          <el-select v-model="tempForm.model.name" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.name"
              :label="item.name"
              :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="车辆或机械" >
          <el-input :value="tempForm.type" disabled></el-input>
        </el-form-item>

        <el-form-item label="芯片编号" >
          <el-input :value="tempForm.deviceId" disabled></el-input>
        </el-form-item>

        <el-form-item label="驾驶员编号" >
          <el-input :value="tempForm.driver.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="驾驶员姓名" >
          <el-input :value="tempForm.driver.name"></el-input>
        </el-form-item>

        <el-form-item label="驾驶员联系方式" >
          <el-input :value="tempForm.driver.phone"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateVehicle()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

import {
  plateNumberFilter
} from "@/utils/globalFilters";

import {
  deleteVehicle,  //删除车辆信息的网络请求
  getVehicleList, //获得所有车辆的网络请求
  updateVehicle   //修改车辆信息的网络请求
} from "@/api/engineer";

import {
  IMAGE_BASE_URL  //获取图片url的地址前缀
} from "@/utils/myRequest";

import {
  getModelByVehicle  //获取类型的网络请求
} from "@/api/model";

export default {
  filters: {
    //车牌类型过滤
    plateTypeFilter(plateType){
      // 如果是汽油车则显示灰色样式，新能源显示success样式
      const plateTypeMap = {
        '汽油车': '',
        '新能源': 'success',
      };
      return plateTypeMap[plateType];
    },

    plateNumberFilter(plateNumber){
      return plateNumberFilter(plateNumber); //执行globalFilters里的过滤器
    }

  },
  data() {
    return {
      dialogFormVisible: false,
      options:[
        {
          name: ''
        }
      ],
      tempForm: {
        model:{},
        driver:{}
      },
      listQuery:{
        currentPage: 1,
        pageSize: 10,
        totalSize: 0
      },
      list: [

      ],
      listLoading: true,
      IMAGE_PREFIX_URL: IMAGE_BASE_URL+'/image/car/'
    }
  },
  created() {
    //组件初始化完成后取得数据并且填充
    this.fetchOptions();
    this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize);
  },
  methods: {

    //分页组件中当前页改变时
    handleCurrentChange(){
      //根据当前页和每页记录数重新拉取数据
      this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
    },

    handleUpdate(row){
      /*console.logger('handleUpdate',row)
      console.logger('handleUpdate',index)*/
      this.dialogFormVisible = true;
      this.tempForm = Object.assign({}, row)
    },

    handleDelete(row,index){
      /*console.logger('handleDelete',row)
      console.logger('handleDelete',index)*/
      this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteVehicle(row,index)
      }).catch()
    },

    updateVehicle(){
      console.log('updateVehicle',this.tempForm);
      /*
      array.findIndex(v=> v.id === array1.id);
      这个函数就是查询数组对应的下标：返回值是 如果array.id=array1.id相等的然后该对象在array数组里的下标值
      */
      let index = this.list.findIndex(v=> v.id === this.tempForm.id)
      // console.log(index);
      updateVehicle(this.tempForm).then(res=>{
        console.log(res);
        this.dialogFormVisible = false;
        this.list.splice(index,1,this.tempForm); //替换掉原数组的对象
        this.$notify({
          type: 'success',
          message: '修改车辆信息成功！'
        })
      }).catch(error=>{
        console.log(error)
        this.$notify({
          type: 'error',
          message: '修改车辆信息失败请联系管理员'+error
        })
      });
    },

    deleteVehicle(row,index){
      this.$notify({
        type: 'warning',
        message: '暂不支持删除车辆信息！'
      })
      /*deleteVehicle(row).then(res=>{
        // console.logger(res);
        this.$notify({
          type: 'success',
          message: '删除车辆信息成功！'
        })
        this.list.splice(index,1);
      }).catch(error=> {
        console.logger(error);
        this.$notify({
          type: 'error',
          message: '删除车辆信息失败请联系管理员'+error
        })
      })*/
    },
    async fetchOptions() {
      const { data:modelList } = await getModelByVehicle() //同步获取类型列表
      this.options = modelList.items
    },
    async fetchList(currentPage,pageSize){
      this.listLoading = true
      const { data:vehicleList } = await getVehicleList(currentPage,pageSize) //同步获取车辆列表
      this.list = vehicleList.items
      this.listQuery.currentPage = vehicleList.currentPage
      this.listQuery.pageSize = vehicleList.pageSize
      this.listQuery.totalSize = vehicleList.totalSize
      this.listLoading = false;
    }
  },
}
</script>

<style scoped>

</style>
