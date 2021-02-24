<template>
  <div class="dashboard-editor-container" v-loading="loading">

    <panel-group ref="panelGroup"/>

    <el-row :gutter="32">

      <el-col
        :xs="24"
        :sm="24"
        :lg="12">
        <div class="chart-wrapper" >
          <bar-chart ref="workDaysBarChart"/>
        </div>
      </el-col>

      <el-col
        :xs="24"
        :sm="24"
        :lg="12">
        <div class="chart-wrapper" >
          <bar-chart ref="mileageBarChart"/>
        </div>
      </el-col>

      <el-col
        :xs="24"
        :sm="24"
        :lg="12">
        <div class="chart-wrapper" >
          <bar-chart ref="fuelBarChart"/>
        </div>
      </el-col>

      <el-col
        :xs="24"
        :sm="24"
        :lg="12"
        style="padding-right:8px;margin-bottom:30px;">
        <transaction-table ref="driverTransTable" />
      </el-col>

    </el-row>

  </div>
</template>

<script>

  import PanelGroup from "./components/PanelGroup";
  import BarChart from "./components/BarChart";
  import TransactionTable from "./components/TransactionTable";
  import AMapLoader from "@/utils/AMap";
  import {
    getPointListByPlateNumberAndDate,
    getVehicleList,
    getWorkListByPlateNumber
  } from "@/api/car";

  export default {
    name: "index",

    created() {
      AMapLoader().then(AMap => {
        this.map = AMap; // 加载成功后将异步加载的高德原生js赋给this.map
        this.fetchData(); //  填充数据
      }, e => {
        console.log('高德地图api加载失败',e)
      })

    },
    methods: {
      async fetchData() {
        let plateNumber;
        let workDays;
        let mileage = 0;
        let fuel;
        let carList = []
        const { data:vehicleList } = await getVehicleList()
        for (const vehicle of vehicleList) {
          plateNumber = vehicle.plateNumber;
          const { data:workList } = await getWorkListByPlateNumber(plateNumber)
          workDays = workList.length
          for (const date of workList) {
            const { data:pointList } = await getPointListByPlateNumberAndDate(plateNumber,date)
            let lineArray = [];
            for (const point of pointList) {
              lineArray.push([point.longitude_amap,point.latitude_amap])
            }
            mileage += this.map.GeometryUtil.distanceOfLine(lineArray);
          }
          mileage = mileage/1000 //单位米->公里
          fuel = (mileage/100)*7.5 //单位升/百公里
          // console.log(plateNumber,workDays,mileage.toFixed(2),fuel.toFixed(2))
          carList.push({plateNumber, workDays, mileage, fuel})
        }
        let mileageOptions = [];
        let workDaysOptions = [];
        let fuelOptions = [];
        for (const car of carList) {
          mileageOptions.push({
            xAxis:car.plateNumber,
            series:(car.mileage).toFixed(2)
          })
          workDaysOptions.push({
            xAxis:car.plateNumber,
            series:car.workDays
          })
          fuelOptions.push({
            xAxis:car.plateNumber,
            series:(car.fuel).toFixed(2)
          })
        }
        let workDaysTitle = {
          text: '总工作天数',      //主标题
          subtext: '（单位：天）'  //副标题
        }
        let mileageTitle = {
          text: '里程',
          subtext: '（单位：公里）'
        }
        let fuelTitle = {
          text: '理想油耗',
          subtext: '（单位：升）'
        }
        // 把相应的数据传递给子组件
        this.$refs.panelGroup.fetchData(carList);
        this.$refs.workDaysBarChart.fetchData(workDaysOptions,workDaysTitle);
        this.$refs.mileageBarChart.fetchData(mileageOptions,mileageTitle);
        this.$refs.fuelBarChart.fetchData(fuelOptions,fuelTitle);
        this.$refs.driverTransTable.fetchData(vehicleList);
        this.loading = false;
      }
    },
    data() {
      return {
        map: null,
        loading: true
      }
    },
    components:{
      PanelGroup,
      BarChart,
      TransactionTable
    },
  }
</script>

<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }

  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }

</style>
