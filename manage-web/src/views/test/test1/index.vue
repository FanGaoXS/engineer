<template>
  <div class="app-container">
    <el-button @click="changeList">父组件按钮：添加list</el-button>
    <child
      :isShow.sync="isShow"
      :list="list"
      @update:syncList="obj=>list.push(obj)"></child>
  </div>
</template>

<script>
  import Child from "./component/Child";
  export default {
    name: "index",
    components:{
      Child
    },
    data() {
      return {
        list: [
          {
            name: 'wuqingke',
            phone: '13594510001'
          },
          {
            name: 'wuqingke2',
            phone: '13709468881'
          }
        ],
        isShow: false
      }
    },
    methods: {
      changeList() {
        this.list.push({
          name: 'wuqingke2',
          phone: '15095800102'
        })
      }
    },
  }
</script>

<style scoped>

</style>
