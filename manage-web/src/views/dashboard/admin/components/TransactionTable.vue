<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="车牌号" min-width="200">
      <template slot-scope="scope">
        {{ scope.row.vehicleNumber | plateNumberFilter}}
      </template>
    </el-table-column>
    <el-table-column label="驾驶员姓名" align="center">
      <template slot-scope="scope">
        {{ scope.row.driver.name  }}
      </template>
    </el-table-column>
    <el-table-column label="驾驶员联系方式" min-width="250" align="center">
      <template slot-scope="scope">
        {{ scope.row.driver.phone  }}
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

import {plateNumberFilter} from "@/utils/global-filters";

export default {
  filters: {
    plateNumberFilter(plateNumber){
      return plateNumberFilter(plateNumber)
    }
  },
  data() {
    return {
      list: []
    }
  },
  created() {
    // this.fetchData()
  },
  methods: {
    fetchData(list) {
      // console.logger(list)
      // 从父组件里获取到的数据然后只取前面5个
      this.list = list.splice(0,5);
    }
  }
}
</script>
