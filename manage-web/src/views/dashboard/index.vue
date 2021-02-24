<template>
  <div class="dashboard-container">
    <component is="adminDashboard"/>
    <!--<div class="dashboard-text">name: {{ name }}</div>-->
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import adminDashboard from './admin'

  export default {
    name: 'Dashboard',
    components:{
      adminDashboard
    },
    computed: {
      ...mapGetters([
        'name'
      ])
    }
  }
</script>

<style lang="scss" scoped>

</style>
