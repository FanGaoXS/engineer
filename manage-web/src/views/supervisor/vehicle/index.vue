<template>
  <div class="app-container">
    <el-card shadow="hover">
      <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="加载中"
        :default-sort = "{prop: 'id', order: 'ascending'}"
        border
        fit
      >

        <!--<el-table-column label="序号" align="center" width="75" prop="id">
          <template slot-scope="scope">
            {{ scope.row.id }}
          </template>
        </el-table-column>-->

        <el-table-column label="车牌号" align="center" min-width="250">
          <template slot-scope="scope">
            {{ scope.row.vehicleNumber | plateNumberFilter }}
          </template>
        </el-table-column>

        <el-table-column label="驾驶员姓名" align="center" min-width="150">
          <template slot-scope="scope">
            {{ scope.row.driverName }}
          </template>
        </el-table-column>

        <el-table-column label="驾驶员联系方式" align="center" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.driverPhone }}
          </template>
        </el-table-column>

        <el-table-column label="总工作天数" align="center" >
          <template slot-scope="scope">
            {{ scope.row.totalWorkDays  }}
          </template>
        </el-table-column>

        <el-table-column label="总里程数" align="center" min-width="250">
          <template slot-scope="scope">
            {{ scope.row.totalMileage | mileageFilter }}
          </template>
        </el-table-column>

        <el-table-column label="操作" width="250" align="center" min-width="250">
          <template slot-scope="scope">
            <!--路由跳转（跳转到名为Work的路由，并且将车牌号作为参数传递过去）-->
            <el-button
              round
              :disabled="scope.row.totalMileage===0"
              type="primary"
              size="medium"
              icon="el-icon-time"
              @click="routerToWork(scope.row)">
              查看工时
            </el-button>
          </template>
        </el-table-column>

      </el-table>

      <el-pagination
        style="margin-top: 15px"
        background
        :total="listQuery.totalSize"
        :page-size="listQuery.pageSize"
        :current-page.sync="listQuery.currentPage"
        @current-change="handleCurrentChange"
        layout="total, prev, pager, next, jumper">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
import {
  plateNumberFilter,
  mileageFilter
} from "@/utils/global-filters";

import {
  getVehicleList,
  getWorkListByVehicleNumber,
  getPointListByVehicleNumberAndDate
} from "@/api/engineer";

import AMapLoader from "@/utils/AMap";

export default {
  filters: {
    plateNumberFilter,
    mileageFilter
  },
  data() {
    return {
      listQuery:{
        currentPage: 1,
        pageSize: 10,
        totalSize: 0
      },
      list: [

      ],//车辆对象数组
      listLoading: true,
      map: null
    }
  },
  created() {
    // 异步加载utils里的高德地图官方js
    // 因为这个页面需要利用高德官方组件GeometryUtil.distanceOfLine()来计算里程
    AMapLoader().then(AMap => {
      this.map = AMap; // 加载成功后将异步加载的高德原生js赋给this.map
      // console.logger('高德地图api加载成功');
      this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize); //组件初始化完成后取得数据并且填充
    }, e => {
      console.logger('高德地图api加载失败',e)
    })
  },
  methods: {
    handleCurrentChange(){
      this.fetchList(this.listQuery.currentPage,this.listQuery.pageSize)
    },
    async fetchList(currentPage,pageSize) {
      let tempList = [];
      this.listLoading = true;
      const { data:list } = await getVehicleList(currentPage,pageSize); //同步获得车辆列表
      const vehicleList = list.items;
      this.listQuery.totalSize = list.totalSize
      this.listQuery.currentPage = list.currentPage
      this.listQuery.pageSize = list.pageSize
      // console.logger('vehicleList',vehicleList);
      for (let i = 0; i < vehicleList.length; i++) {
        let id = vehicleList[i].id
        let vehicleNumber = vehicleList[i].vehicleNumber
        let driverName = vehicleList[i].driver.name
        let driverPhone = vehicleList[i].driver.phone
        const { data:workList } = await getWorkListByVehicleNumber(vehicleNumber) //根据车牌号同步获取该车工作了多少天
        // console.logger(plateNumber+'workList',workList);
        let totalMileage = 0;
        let totalWorkDays = workList.length;
        for (let j = 0; j < workList.length; j++) {
          let date = workList[j]
          const { data:pointList } = await getPointListByVehicleNumberAndDate(vehicleNumber,date); //根据车牌号和日期同步获取该车当日的坐标点集合
          // console.logger(plateNumber,date,'pointList',pointList);
          let lineArray = []
          for (let k = 0; k < pointList.length; k++) {
            let point = pointList[k];
            lineArray.push([point.longitude_amap,point.latitude_amap])
          }
          let mileage = this.map.GeometryUtil.distanceOfLine(lineArray); // 利用AMap的官方工具计算里程
          totalMileage+=mileage;
        }
        tempList.push({id,driverName,driverPhone,vehicleNumber,totalWorkDays,totalMileage})
        /*
        * 将数据一个一个一次通过数组的push方法动态放置进vue上的list
        * （list会一条一条记录的刷新）
        * */
        /*this.list.push({driverName,driverPhone,plateNumber,totalWorkDays,totalMileage})
        this.listLoading = false;*/
      }
      /*
      * 直接将临时数组的数据赋值给挂载到vue上的list
      * （list全部获取完成后页面才渲染出数据）
      * 两种方法只是视觉效果上有差异
      * */
      this.list = tempList;
      this.listLoading = false;
    },
    // 路由前进
    routerToWork(row){
      if (row.totalMileage===0) return
      this.$router.push({
        name: 'Work',
        params: {
          vehicleNumber: row.vehicleNumber,
        }
      })
    }
  },
}
</script>

<style scoped>

</style>
