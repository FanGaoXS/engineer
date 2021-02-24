<template>
  <div>
    <h2>我是子组件</h2>
    <h2>{{list}}</h2>
    <el-button @click="changeList">子组件按钮：添加list</el-button>
  </div>
</template>

<script>
  export default {
    name: "child",
    props: {
      list: {
        type: Array,
        default: []
      },
    },
    methods: {
      changeList() {
        //子组件调用父组件的changeIsShow函数，并且传递false作为参数
        this.$emit('update:syncList',{
          name: 'wqk4',
          phone: '231312'
        })
      }
    },
  }
</script>

<style scoped>

</style>
