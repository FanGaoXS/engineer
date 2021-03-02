<template>
  <div class="app-container">
    <h2>page2</h2>
    <h2>这个页面会向page3页面传递参数</h2>
    <el-input v-model="name"></el-input>
    <el-input-number v-model="age"></el-input-number>
    <br>
    <el-button @click="routerAheadQuery()">去下一个页面（query方式）</el-button>
    <el-button @click="routerAheadParams()">去下一个页面（params方式）</el-button>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        name: '',
        age: 0
      }
    },
    methods: {
      routerAheadQuery() {
        this.$router.push({
          // path: '/test/page3',
          name: 'Page3',
          query:{
            name: this.name,
            age: this.age,
            object: {
              name: this.name,
              age: this.age,
            }
          }
        })
      },
      routerAheadParams() {
        this.$router.push({
          // path: '/test/page3',
          name: 'Page3',
          params: {
            name: this.name,
            age: this.age,
            object: {
              name: this.name,
              age: this.age,
            }
          }
        })
      }
    },
  }
</script>

<style scoped>

</style>
