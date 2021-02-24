<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
// require('echarts/theme/macarons') // echarts theme
require('./chartTheme/westeros')
import resize from './mixins/resize'


const animationDuration = 1000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    },
  },
  data() {
    return {
      chart: null,
      options: [],
      title: {}
    }
  },

  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  watch: {
    options(newValue,oldValue){
      /*console.log(oldValue)
      console.log(newValue)*/
      let xAxisDataList = []
      let seriesDataList = [];
      for (const item of newValue) {
        xAxisDataList.push(item.xAxis)
        seriesDataList.push(item.series)
      }
      this.chart.setOption({
        xAxis:[{
          data:xAxisDataList
        }],
        series:[{
          data:seriesDataList
        }]
      })
    },
    title(newValue,oldValue){
      /*console.log(oldValue)
      console.log(newValue)*/
      this.chart.setOption({
        title: {
          text: newValue.text,
          subtext: newValue.subtext
        }
      })
    }
  },
  methods: {
    fetchData(options,title){
      // 从父组件里接收到的值然后赋值给data发生变化，从而watcher生效然后setOptions数据到echart上
      /*console.log(options)
      console.log(title)*/
      this.options = options
      this.title = title
    },

    initChart() {
      // 初始化条状图的样式
      // this.chart = echarts.init(this.$el, 'macarons')
      this.chart = echarts.init(this.$el, 'westeros')
      this.chart.setOption({
        title:{
          text: '标题',
          subtext: '副标题'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          },
        },
        grid: {
          top: 80,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: [ '星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天' ],
          axisTick: {
            alignWithLabel: true
          }
        },
        ],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: [{
          // name: 'pageA',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          label: {
            show: true,
            position: 'outside'
          },
          data: [79, 52, 200, 334, 390, 330, 220],
          animationDuration
        }]
      })
    }
  }
}
</script>
