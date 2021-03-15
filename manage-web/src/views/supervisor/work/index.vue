<template>
  <div class="app-container">

    <el-header style="margin-top: 10px"><!--页头（返回按钮）-->
      <el-page-header @back="routerBack" content="工时列表">
      </el-page-header>
    </el-header>

    <el-table
      v-loading="listLoading"
      :data="workList"
      element-loading-text="加载中···"
      :default-sort = "{prop: 'date', order: 'descending'}"
      border
      fit
    >
      <!--:default-sort = "{prop: 'date', order: 'descending'}"默认排序列-->
      <!--highlight-current-row选中行高亮-->
      <el-table-column label="序号" align="center" width="75">
        <template slot-scope="scope">
          {{ scope.$index+1+(listQuery.currentPage-1)*listQuery.pageSize }}
        </template>
      </el-table-column>

      <el-table-column label="车牌号" align="center" width="250">
        {{ $route.params.vehicleNumber | plateNumberFilter }}
      </el-table-column>

      <el-table-column label="日期" align="center" prop="date">
        <template slot-scope="scope">
          {{ scope.row.date | dateFilter }}
        </template>
      </el-table-column>

      <el-table-column label="里程数" align="center">
        <template slot-scope="scope">
          {{ scope.row.mileage | mileageFilter}}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="250">
        <template slot-scope="scope">
          <!--路由跳转-->
          <el-button
            type="primary"
            size="small"
            icon="el-icon-position"
            @click="routerToMap(scope.row)"
            :disabled="scope.row.mileage===0" >
            轨迹详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      style="margin-top: 15px"
      background
      :total="tempList.length"
      :page-size="listQuery.pageSize"
      :current-page="listQuery.currentPage"
      @current-change="handleCurrentChange"
      layout="total, prev, pager, next">
    </el-pagination>

  </div>
</template>


<script>

import {
  getWorkListByVehicleNumber,
  getPointListByVehicleNumberAndDate
} from "@/api/car";

import {
  plateNumberFilter,
  mileageFilter,
} from "@/utils/globalFilters";

import AMapLoader from '@/utils/AMap'

export default {
  filters: {
    plateNumberFilter(plateNumber){
      return plateNumberFilter(plateNumber); //使用globalFilters里的过滤器
    },
    mileageFilter(mileage){
      return mileageFilter(mileage); //使用globalFilters里的过滤器
    },
    /**
     * 将yyyy-mm-dd日期字符串转为yyyy/mm/dd类型的日期对象
     * @param date
     * @returns {string}
     */
    dateFilter(date){
      let objectDate = new Date(date);//直接将日期字符串转为日期对象
      return objectDate.toLocaleDateString();
    }
  },
  data() {
    return {
      tempList: [

      ],
      workList: [ //工时对象数组
        /*plateNumber,
        date,
        mileage*/
      ],
      listQuery:{
        currentPage: 1,
        pageSize: 10
      },
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
      this.fetchData(); //获取到数据并且填充
    }, e => {
      console.logger('高德地图api加载失败',e)
    })
  },
  methods: {
    handleCurrentChange(currentPage){
      // console.logger(currentPage)
      this.listQuery.currentPage = currentPage
      let startNumber = (currentPage-1)*this.listQuery.pageSize
      let endNumber = currentPage*this.listQuery.pageSize
      this.workList = this.tempList.slice(startNumber,endNumber)
    },
    async fetchData() {
      this.listLoading = true
      // console.log(this.$route.params);
      const vehicleNumber = this.$route.params.vehicleNumber;
      const { data:workList } = await getWorkListByVehicleNumber(vehicleNumber)
      // console.log(workList)
      for (const date of workList) {
          const { data:pointList } = await getPointListByVehicleNumberAndDate(vehicleNumber,date)
        // console.log(pointList)
        let lineArray = []
        for (const point of pointList) {
          lineArray.push([point.longitude_amap,point.latitude_amap]); //将坐标点的经纬度赋值按顺序添加到临时数组中
        }
        //将临时数组扔到高德原生js的路径长度计算工具（此函数是异步！！切记）
        let mileage = this.map.GeometryUtil.distanceOfLine(lineArray);
        this.tempList.push({vehicleNumber, date, mileage});
      }
      this.listLoading = false;
      this.workList =this.tempList.slice(0,this.listQuery.pageSize) //组件初始化时
    },
    // 路由前进
    routerToMap(row){
      if (row.mileage===0) return;
      this.$router.push({
        name: 'Map', //跳转到名为Map的路由去
        params: {    //携带以下参数
          vehicleNumber: row.vehicleNumber,
          date: row.date,
          mileage: row.mileage
        }
      })
    },
    // 路由回退
    routerBack(){
      this.$router.go(-1)
    }
  }
}
</script>
