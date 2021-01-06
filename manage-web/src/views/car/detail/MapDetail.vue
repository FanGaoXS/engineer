<template>
  <el-container>
    <el-header>
      <h2>地图详情</h2>
      <!--<h2>{{$route.query.vehicleRow}}</h2>-->
    </el-header>
    <el-main>

      <el-amap
              vid="aMap"
              class="aMap"
              :zoom="zoom"
              :center="center"
      ><!--高德地图组件-->

        <el-amap-polyline
                :path="polyline.path"
                :lineJoin="'round'"
                strokeColor="#3366FF"
                strokeWeight="5"
                strokeStyle= "solid"
                borderWeight= "2"
                lineJoin="round"
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
    </el-main>
    <el-footer>
      <el-button @click="test">按钮</el-button>
    </el-footer>
  </el-container>
</template>

<!--引入高德地图JS api-->

<script>

  import {
    getPolylineArray
  } from "../../../network/polylineRequest";

  export default {
    name: "MapDetail",
    data() {
      return {
        plateNumber: '川A.1234C',
        time: '2021-01-04',
        zoom: 13,  //初始缩放级别（数字越大约精细，最大18）
        center: [104.066143,30.573095],   //地图中心点坐标（默认是成都市政府）
        polyline: { //一条折线对应一个折线对象，一个折线对象里有对应的轨迹点
          path: []
        },
        timeArray: [],
        startMarker: {  //起点标记
          position: [],
          icon: '',
          template: '<h2>起点</h2>'
        },
        endMarker: {  //终点标记
          position: [],
          icon: '',
          template: '<h2>终点</h2>'
        }
      }
    },
    created() {
      this.getPolylineArray(this.plateNumber,this.time);
    },
    methods: {
      //根据车牌号和日期获取车辆当日的地图轨迹折线
      getPolylineArray(plateNumber,time){
        getPolylineArray(plateNumber,time).then(res=>{
          // console.log(res.data);
          for (let i = 0; i < res.data.length; i++) { //循环遍历json并且分别将经纬度赋值
            /*console.log(res.data[i].longitude_amap);  //经度
            console.log(res.data[i].latitude_amap);   //纬度*/
            this.polyline.path[i] = [ res.data[i].longitude_amap , res.data[i].latitude_amap ];
            this.timeArray[i]= res.data[i].record_time; //将每个点记录时间赋给时间数组
          }
          this.center = this.polyline.path[this.polyline.path.length-1]; //  将地图中心坐标移动到终点
          this.startMarker.position = this.polyline.path[0]; //起点标记的坐标
          this.endMarker.position = this.polyline.path[this.polyline.path.length-1];  //终点标记坐标
        }).catch(error=>{
          console.log(error);
        });
      },
      test(){

      }
    },
  }
</script>

<style scoped>

  .aMap {
    height: 100%;
    width: 100%;
  }

</style>