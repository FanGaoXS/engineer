<template>
  <el-container>
    <el-main>
      <el-table :data="developList" style="width: 100%">
        <el-table-column type="expand"><!--可展开类型的行-->
          <template slot-scope="scope"><!--行内数据的可使用范围-->
            <el-form label-position="left" class="demo-table-expand">
              <el-form-item label="姓名">
                <span>{{scope.row.cn}}</span>
              </el-form-item>
              <el-form-item label="曾用名">
                <span>{{scope.row.sn}}</span>
              </el-form-item>
              <el-form-item label="用户名">
                <span>{{scope.row.uid}}</span>
              </el-form-item>
              <el-form-item label="密码">
                <span>{{scope.row.password}}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label=姓名 prop="cn"/>
        <el-table-column label="用户名" prop="uid"/>
        <el-table-column label="密码" prop="password"/>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="small" type="danger" @click="test(scope.$index,scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
  import {
    getDevelopList,
    deleteDevelop
  } from "../../../network/developRequset";

  export default {
    name: "DevelopList",
    data() {
      return {
        developList: []
      }
    },
    created() {
      getDevelopList().then(res=>{
        console.log(res);
        this.developList=res.data;
      }).catch(error=>{
        console.log(error);
      })
    },
    methods: {
      test(index,developRow){
        console.log(index);
        console.log(developRow);
      },
      deleteDevelop(index,developer) {
        console.log(index);
        console.log(developer);
        let that=this;
        deleteDevelop(developer.cn).then(res=>{
          console.log(res);
          //从数据下标为index的开始，删除一个元素
          that.developList.splice(index,1);
        },error=>{
          console.log(error);
        });
      }
    },
  }
</script>

<style scoped>
  .el-container{
    /*width: 1600px;*/
  }

  .el-header, .el-footer {
    /*background-color: #B3C0D1;*/
    color: #333;
    text-align: center;
    line-height: 40px;
  }

  .el-main {
    background-color: #E9EEF3;
    /*color: #333;*/
    /*text-align: center;*/
    /*line-height: 160px;*/
  }

  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

</style>