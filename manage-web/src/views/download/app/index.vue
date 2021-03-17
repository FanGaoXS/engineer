<template>
  <el-container>

    <!--<el-header>&lt;!&ndash;header&ndash;&gt;</el-header>-->

    <el-main>
      <el-table
        :data="tableData"
        border
        style="width: 100%"><!--表格-->
        <el-table-column
          type="index"
          width="100">
        </el-table-column>
        <el-table-column
          prop="fileName"
          label="文件名"
          width="100">
        </el-table-column>
        <el-table-column
          prop="fileDes"
          label="文件描述"
          width="250">
        </el-table-column>
        <el-table-column
          label="文件下载">
          <template slot-scope="scope">
            <a :href="scope.row.fileHref">
              <el-button type="primary"><i class="el-icon-download"></i> 下载</el-button>
            </a>
          </template>
        </el-table-column>
      </el-table>
    </el-main>

    <el-footer><!--footer--></el-footer>

  </el-container>
</template>

<script>
  import {
    ZIP_PREFIX_URL
  } from "@/utils/global-variable";

  export default {
    name: "index",
    data() {
      return {
        msg: 'HelloVuejs!',
        tableData:[
          {
            fileName: 'vpconf.zip',
            fileDes: '车辆管理系统固定站设备配置程序',
            fileHref: ZIP_PREFIX_URL+'vpconf.zip'//下载地址
          }
        ]
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
    /*width: 50%;*/
    text-align: center;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
</style>
