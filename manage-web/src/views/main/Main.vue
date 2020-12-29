<template>
  <!--最外层容器-->
  <el-container>
    <el-header></el-header>
    <el-main>
      <el-container style="height: 800px; border: 1px solid #eee;">
        <!--左边侧边栏-->
        <el-aside width="200px" style="background-color: rgb(238, 241, 246);">
          <el-menu
                  default-active="developList"
                  :router="true"
          ><!--菜单栏组-->
            <el-submenu index="1"><!--第一组菜单栏-->
              <template slot="title"><i class="el-icon-s-custom"></i>系统用户管理</template>
              <el-submenu index="1-1"><!--开发部-->
                <template slot="title">开发部</template>
                <el-menu-item index="developList">开发人员列表</el-menu-item>
                <el-menu-item index="developInsert">新增开发人员</el-menu-item>
              </el-submenu>
              <el-submenu index="1-2"><!--财务部-->
                <template slot="title">财务部</template>
                <el-menu-item index="1-2-1">财务人员列表</el-menu-item>
                <el-menu-item index="1-2-2">新增财务人员</el-menu-item>
              </el-submenu>
              <el-submenu index="1-3"><!--测试部-->
                <template slot="title">测试部</template>
                <el-menu-item index="1-3-1">测试人员列表</el-menu-item>
                <el-menu-item index="1-3-2">新增测试人员</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-submenu index="2"><!--第一组菜单栏-->
              <template slot="title"><i class="el-icon-menu"></i>工程用具管理</template>
              <el-submenu index="2-1"><!--车辆-->
                <template slot="title">普通车辆</template>
                <el-menu-item index="vehicleList">车辆列表</el-menu-item>
              </el-submenu>
              <el-submenu index="2-2"><!--机械-->
                <template slot="title">工程机械</template>
                <el-menu-item index="machineList">机械列表</el-menu-item>
              </el-submenu>
            </el-submenu>
          </el-menu>
        </el-aside><!--左边侧边栏end-->
        <!--右边内容显示-->
        <router-view/>
      </el-container>
    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>

<script>
  export default {
    name: "Main",
    components:{
    },
    methods: {
      jumpTo(nextPath) {
        // 当前path
        let currentPath=this.$route.path;
        if (nextPath===currentPath) return;
        this.$router.push({
          path: nextPath
        });
      }
    },
  }
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  /*.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }*/
</style>