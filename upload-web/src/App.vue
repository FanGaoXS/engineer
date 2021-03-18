<template>
  <el-container>
    <el-header>
      车辆信息上传
    </el-header>
    <el-main>
      <el-button-group>
        <el-button :type="isVehicleForm?'primary':''" @click="routerToVehicle">车辆</el-button>
        <el-button :type="!isVehicleForm?'primary':''" @click="routerToMachine">机械</el-button>
      </el-button-group>
      <router-view/>
    </el-main>
    <el-footer>
      Copyright©blctek.com
    </el-footer>
  </el-container>
</template>

<script>

  export default {
    name: 'app',
    data() {
      return {
        isVehicleForm: true
      }
    },
    methods: {
      routerToVehicle() {
        this.isVehicleForm = true
        const currentPage = this.$route.path; //当前页面的url
        if (currentPage.indexOf('/vehicle') !== -1) return
        this.$router.push({
          name: 'Vehicle',
        })
      },
      routerToMachine() {
        this.isVehicleForm = false
        const currentPage = this.$route.path; //当前页面的url
        if (currentPage.indexOf('/machine') !== -1) return
        this.$router.push({
          name: 'Machine',
        })
      }
    },
  }
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #629efa;
    color: #ffffff;
    text-align: center;
    line-height: 60px;
    border-radius: 20px;
  }

  .el-main {
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 50px;
    margin-bottom: 50px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
</style>
