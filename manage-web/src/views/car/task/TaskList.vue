<template>
  <div>
    <h2>工时列表</h2>
    <h2>{{$route.query.vehicleRow}}</h2>
    <el-button size="" type="info" @click="toMapDetail($route.query.vehicleRow)">查看</el-button>
  </div>
</template>

<script>
  export default {
    name: "TaskList",
    data() {
      return {
      }
    },
    methods: {
      toMapDetail(vehicleRow) {
        this.$router.push({
          path: '/main/mapDetail',
          query: {
            vehicleRow
          }
        })
      }
    },
  }
</script>

<style scoped>

</style>