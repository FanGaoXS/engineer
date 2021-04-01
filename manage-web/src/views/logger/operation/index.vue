<template>
  <div class="app-container">
    <el-card shadow="hover">
      <div class="filter-container">

        <el-select placeholder="根据用户查询" v-model="listQuery.executor" clearable @change="handleListQueryChange">
          <el-option
            v-for=" item in executorOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name"
          ></el-option>
        </el-select>

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

        <el-table-column label="操作者" align="center">
          <template slot-scope="scope">
            {{ scope.row.executor }}
          </template>
        </el-table-column>

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
    </el-card>
  </div>
</template>

<script>
  import {
    timeFilter
  } from "@/utils/global-filters";

  import {
    getLoggerList
  } from "@/api/logger";
  import {getUserList} from "@/api/user/user";

  export default {
    name: "index",
    filters: {
      timeFilter
    },
    data() {
      return {
        listLoading: true,
        list: [ ],
        listQuery: {
          currentPage: 1,
          pageSize: 15,
          totalSize: 0,
          executor: undefined,
          client: undefined
        },
        executorOptions: [ ],
        clientOptions: [ ]
      }
    },
    created() {
      this.fetchClientOptions()
      this.fetchExecutorOptions()
      this.fetchList(this.listQuery.executor,this.listQuery.client,this.listQuery.currentPage, this.listQuery.pageSize)
    },
    methods: {
      handleListQueryChange(){
        this.fetchList(this.listQuery.executor,this.listQuery.client,this.listQuery.currentPage, this.listQuery.pageSize)
      },
      async fetchList(executor,client,currentPage,pageSize) {
        this.listLoading = true
        const { data:loggerList } = await getLoggerList(executor,client,currentPage,pageSize)
        this.list = loggerList.items
        this.listQuery.currentPage = loggerList.currentPage
        this.listQuery.pageSize = loggerList.pageSize
        this.listQuery.totalSize = loggerList.totalSize
        this.listLoading = false
      },
      fetchExecutorOptions(){
        getUserList().then(res=>{
          this.executorOptions = res.data.items;
        })
      },
      fetchClientOptions(){
        this.clientOptions = [ 'manage-web', 'postman', 'upload-web', '未知' ];
      }
    },
  }
</script>

<style scoped>
  @media (max-width:550px)  {
    .el-select {
      width: 45%;
    }
  }
</style>
