<template>
  <div class="app-container">
    <h2>query.name:{{$route.query.name}}</h2>
    <h2>query.age:{{$route.query.age}}</h2>
    <h2>query.object:{{$route.query.object}}</h2>
    <h2>params.name:{{$route.params.name}}</h2>
    <h2>params.age:{{$route.params.age}}</h2>
    <h2>params.object:{{$route.params.object}}</h2>
    <h2>page3</h2>
  </div>
</template>

<script>
  export default {
    name: "index"
  }
</script>

<style scoped>

</style>
