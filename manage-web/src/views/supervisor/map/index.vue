<template>
  <div class="app-container">
    <el-card shadow="hover">
      <el-header style="margin-top: 10px">
        <el-page-header @back="routerBack" content="轨迹详情">
        </el-page-header>
      </el-header>

      <el-amap vid="aMap"
               class="aMap"
               :plugin="plugins"
               :center="center"
               :zoom="zoom"><!--高德地图地图容器组件-->
        <el-amap-polyline
          :path="path"
          :lineJoin="'round'"
          strokeColor="#409EFF"
          strokeOpacity="0.8"
          strokeWeight="5"
          strokeStyle= "solid"
        /><!--轨迹折线-->

        <el-amap-marker
          vid="startMarker"
          :position="startMarker.position"
          title="起点"
        /><!--起点标记-->

        <el-amap-text
          text="起点"
          :position="startMarker.position"
          :offset="[0,-50]"
        /><!--起点文字-->

        <el-amap-marker
          vid="endMarker"
          :position="endMarker.position"
          title="终点"
        /><!--终点标记-->

        <el-amap-text
          text="终点"
          :position="endMarker.position"
          :offset="[0,-50]"
        /><!--终点文字-->

      </el-amap>

      <el-row :gutter="10" style="margin-top: 15px"><!--响应式第一行-->
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="vehicleNumber | plateNumberFilter" readonly>
            <template slot="prepend">车牌号</template>
          </el-input>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="mileage | mileageFilter " readonly>
            <template slot="prepend">里程</template>
          </el-input>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="aveAltitude" readonly>
            <template slot="prepend">平均海拔</template>
          </el-input>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="estimateFuelConsumptionAll" readonly>
            <template slot="prepend">总耗油（估算）</template>
          </el-input>
        </el-col>
      </el-row>

      <el-row :gutter="10"><!--响应式第二行-->
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="startPoint | pointFilter" readonly>
            <template slot="prepend">起点</template>
          </el-input>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="endPoint | pointFilter" readonly>
            <template slot="prepend">终点</template>
          </el-input>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="startTime | dateFilter" readonly>
            <template slot="prepend">起点时间</template>
          </el-input>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <el-input :value="endTime | dateFilter" readonly>
            <template slot="prepend">终点时间</template>
          </el-input>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>

import {
  plateNumberFilter,
  mileageFilter,
} from "@/utils/global-filters";

import {
  getPointListByVehicleNumberAndDate
} from "@/api/engineer";

export default {
  computed: {
    // 车牌号
    vehicleNumber() {
      return this.$route.params.vehicleNumber; //取从上个页面传递过来的params中的参数
    },
    // 平均海拔
    aveAltitude() {
      let total = 0;
      for (let point of this.pointList) { //遍历出坐标点对象数组里的海拔
        total += point.altitude;
      }
      let ave = total/(this.pointList.length); //求平均
      return ave.toFixed(3)+'米';
    },
    mileage() {
      return this.$route.params.mileage; //取从上个页面传递过来的params中的参数
    },
    // 估算总耗油量（升/百公里）
    estimateFuelConsumptionAll() {
      return ((this.mileage/1000/100)*7.5).toFixed(3)+'升';
    },
    startPoint() {
      return this.path[0];
    },
    endPoint() {
      return this.path[this.path.length-1];
    },
    startTime() {
      if (this.pointList.length===0) return ;
      return this.pointList[0].record_time;
    },
    endTime() {
      if (this.pointList.length===0) return ;
      return this.pointList[this.pointList.length-1].record_time;
    }
  },
  filters: {
    // 将标准UTC时间转为易读的时间
    dateFilter(date){
      let objectDate = new Date(date);
      return objectDate.toLocaleString();
    },
    // 经纬度过滤器
    pointFilter(point){
      if (point===undefined) return ;
      return '经度：'+point[0]+'，纬度：'+point[1];
    },
    plateNumberFilter,
    mileageFilter
  },
  data() {
    return {
      plugins:[
        { //地图类型插件（卫星或普通地图）
          pName: 'MapType',
          defaultType: 0, // 0是普通地图，1是卫星。默认是0
        },
        { // 比例尺插件
          pName: 'Scale',
        },
        { // 左侧工具条插件
          pName: 'ToolBar',
        }
      ],
      zoom: 14,  //初始缩放级别（数字越大约精细，最大18）
      center: [104.066143,30.573095],   //地图中心点坐标（默认是成都市政府）
      path:[ //符合高德地图规范的经纬度数组
        // [latitude_amap,longitude_amap]
      ],
      pointList: [  //坐标点对象数组
        /*{ //坐标点对象
          altitude,       //坐标点海拔高度
          latitude_amap,  //坐标点纬度
          longitude_amap, //坐标点经度
          record_time     //坐标点记录的时间
        }*/
      ],
      startMarker: { //起点坐标对象
        position: [104.066143,30.573095] //必须给点标记一个初始坐标，否则会报错（）
      },
      endMarker: { //终点坐标对象
        position: [104.066143,30.573095]
      }
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      let vehicleNumber = this.$route.params.vehicleNumber;
      let date = this.$route.params.date;
      const { data:pointList } = await getPointListByVehicleNumberAndDate(vehicleNumber,date)
      for (let i = 0; i < pointList.length; i++) {
        /*console.logger(pointList[i].longitude_amap);  //经度
        console.logger(pointList[i].latitude_amap);   //纬度*/
        //将坐标点的经纬度以[经度，纬度]的形式放到path数组里（高德地图规范）
        this.path.push([pointList[i].longitude_amap,pointList[i].latitude_amap]);
      }
      this.pointList = pointList
      this.center = this.path[0]; //将地图中心移动到path数组的起点
      this.startMarker.position = this.path[0]; //起点坐标为path数组的第一个
      this.endMarker.position = this.path[this.path.length-1];  //终点坐标为path数组的最后一个
    },
    // 路由回退
    routerBack(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

  .aMap {
    height: 600px;
    width: 100%;
  }

  .el-col {
    margin-top: 5px;
    padding: 5px;
  }

  /*如果屏幕宽度小于550px则地图容器高度为300px*/
  @media (max-width:550px)  {
    .aMap {
      height: 300px;
    }
  }

</style>
