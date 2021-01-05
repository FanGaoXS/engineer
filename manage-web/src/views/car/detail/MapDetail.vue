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
      ><!--高德地图组件-->
        <el-amap-polyline
                :path="polylineArray.path"
        ><!--折线-->
        </el-amap-polyline>
      </el-amap>
    </el-main>
    <el-footer>
      <el-button @click="test">按钮</el-button>
    </el-footer>
  </el-container>
</template>

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
        polylineArray: {
          path: [],
        },
        timeArray: {
          timeList: []
        }
      }
    },
    created() {
      this.getPolylineArray(this.plateNumber,this.time);
      console.log(this.polylineArray.path);
    },
    methods: {
      getPolylineArray(plateNumber,time){
        getPolylineArray(plateNumber,time).then(res=>{
          // console.log(res.data);
          for (let i = 0; i < res.data.length; i++) { //循环遍历json并且分别将经纬度赋值
            /*console.log(res.data[i].longitude_amap);  //经度
            console.log(res.data[i].latitude_amap);   //纬度*/
            this.polylineArray.path[i] = [ res.data[i].longitude_amap , res.data[i].latitude_amap ];
          }
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