<template>
  <div class="card">Hello! Welcome to use this system!</div>
  <div class="card" style="margin-top: 10px; width: 50%" v-if="data.user.role === 'USER'">
    <el-timeline style="max-width: 600px">
      <div style="font-size: 15px; margin-bottom: 20px">System Notice</div>
      <el-timeline-item :timestamp="item.time" color="#0bb78d" placement="top" v-for="item in data.noticeData">
        <el-card>
          <h4>{{item.title}}}</h4>
          <p>{{item.content}}</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
  <div v-else style="margin-top: 10px; display: flex; grid-gap: 10px" >
    <div class="card" style="height: 400px; width: 50%" id="pie"></div>
    <div class="card" style="height: 400px; width: 50%" id="bar"></div>
  </div>
</template>

<script setup>
import {reactive, onMounted} from "vue"
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
import * as echarts from "echarts";

const data = reactive({
  user: JSON.parse(localStorage.getItem('code_user') || "{}"),
  noticeData: []
})

const loadNotice = () => {
  request.get('/notice/selectAll').then(res => {
    if (res.code === '200') {
      data.noticeData = res.data
      if (data.noticeData.length > 3){
        data.noticeData = data.noticeData.slice(0,3)
      }
    } else {
      ElMessage.error(res.msg)
    }
  })
}
loadNotice()

const loadPie = () => {
  request.get('/echarts/pie').then(res => {
    if (res.code === '200'){
      let chartDom = document.getElementById('pie')
      let myChart = echarts.init(chartDom)
      pieOptions.series[0].data = res.data
      myChart.setOption(pieOptions)
    }
  })
}

const loadBar = () => {
  request.get('/echarts/bar').then(res => {
    if (res.code === '200') {
      let chartDom = document.getElementById('bar')
      let myChart = echarts.init(chartDom)
      barOptions.xAxis.data = res.data.xAxis
      barOptions.series[0].data = res.data.yAxis
      myChart.setOption(barOptions)

    }
  })
}

onMounted(() => {
  loadPie()
  loadBar()
})

let pieOptions = {
  title: {
    text: '不同分类下用户发布旅游攻略帖子的数量', // 主标题
    subtext: '统计维度：攻略分类', // 副标题
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '数量占比', // 鼠标移上去显示内容
      type: 'pie',
      radius: '50%',
      center: ['50%', '60%'],
      data: [
        {value: 1048, name: '瑞幸咖啡'}, // 示例数据：name表示维度，value表示对应的值
        {value: 735, name: '雀巢咖啡'},
        {value: 580, name: '星巴克咖啡'},
        {value: 484, name: '栖巢咖啡'},
        {value: 300, name: '小武哥咖啡'}
      ]
    }
  ]
}

let barOptions = {
  title: {
    text: '不同用户发布帖子数量Top5', // 主标题
    subtext: '统计维度：用户昵称', // 副标题
    left: 'center'
  },
  grid : {   // ---------------------------增加这个属性，bottom就是距离底部的距离
    top: '20%',
    bottom : '20%'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  xAxis: {
    type: 'category',
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'], // 示例数据：统计的维度（横坐标）
    name: '用户昵称',
    axisLabel: {
      show: true, // 是否显示刻度标签，默认显示
      interval: 0, // 坐标轴刻度标签的显示间隔，在类目轴中有效；默认会采用标签不重叠的策略间隔显示标签；可以设置成0强制显示所有标签；如果设置为1，表示『隔一个标签显示一个标签』，如果值为2，表示隔两个标签显示一个标签，以此类推。
      rotate: -60, // 刻度标签旋转的角度，在类目轴的类目标签显示不下的时候可以通过旋转防止标签之间重叠；旋转的角度从-90度到90度
      inside: false, // 刻度标签是否朝内，默认朝外
      margin: 6, // 刻度标签与轴线之间的距离
    },
  },
  yAxis: {
    type: 'value',
    name: '攻略数量',
  },
  tooltip: {
    trigger: 'item',
  },
  series: [
    {
      data: [120, 200, 150, 80, 70, 110, 130], // 示例数据：横坐标维度对应的值（纵坐标）
      type: 'bar',
      itemStyle: {
        normal: {
          color: function () {
            return "#" + Math.floor(Math.random() * (256 * 256 * 256 - 1)).toString(16);
          }
        },
      },
    }
  ]
};
</script>