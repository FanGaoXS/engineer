<template>
  <div class="app-container">
    <el-form :model="info" label-width="80px">
      <el-form-item label="编号">
        <el-input :value="id" disabled></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="info.password" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="info.name" disabled></el-input>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="info.phone" disabled></el-input>
      </el-form-item>
      <el-form-item label="角色">
        <el-input :value="role" disabled></el-input>
      </el-form-item>
    </el-form>
    <h2>个人中心</h2>
    <h2>{{$store.state.user}}</h2>
    <h2>{{info}}</h2>
  </div>
</template>

<script>

  import {
    getUser,
  } from "@/api/user/user";

  import {
    updateInfo
  } from "@/api/user/profile";

  export default {
    name: "index",
    data() {
      return {
        info: {
          id: undefined,
          password: undefined,
          name: undefined,
          phone: undefined
        }
      }
    },
    created() {
      this.info.id = this.$store.state.user.id
      this.fetchData(this.$store.state.user.id)
    },
    methods: {
      async fetchData(id) {
        const { data } = await getUser(id)
        console.log(data)
      }
    },
    computed: {
      id(){
        return this.$store.state.user.id
      },
      role(){
        return this.$store.state.user.role
      }
    },
  }
</script>

<style scoped>

</style>
