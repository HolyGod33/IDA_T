<template>
  <div class="container">
    <el-tabs type="border-card">
      <el-tab-pane :label="$t('scholarDetails.egoNetwork')">
        <div ref="treeChart" style="height:360px; width:310px;"></div>
      </el-tab-pane>

      <!-- 找不到D-Core是怎么算的，故删除此模块 -->
      <!--      <el-tab-pane label="D-Core">-->
      <!--        <div ref="lineChart" style="height:360px; width:340px; transform:rotate(90deg); transform-origin: 47% 47%;"></div>-->
      <!--      </el-tab-pane>-->

      <el-tab-pane label="Co-Author" :lazy="true">
        <force-graph :width="340" :height="410" :force-graph-data="forceGraphData"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { getRelationByScholarId, getCoAuthorByScholarId } from '@/api/scholar-details'

export default {
  components: {
    ForceGraph: () => import('@/components/ForceGraph')
  },
  created () {
    this.getNetworkData()
    this.getForceGraphData()
  },
  data () {
    return {
      networkData: [{
        name: '',
        label: {
          color: 'rgb(55, 126, 184)',
          position: 'bottom',
          rotate: 0,
          distance: 10
        },
        itemStyle: {
          color: 'rgb(55, 126, 184)'
        },
        children: []
      }],
      forceGraphData: {}
    }
  },
  methods: {
    setTreeChart () {
      const myChart = this.$echarts.init(this.$refs.treeChart)
      const option = {
        series: [
          {
            type: 'tree',
            data: this.networkData,
            width: '140',
            center: [-35, 30],
            layout: 'radial',
            symbol: 'circle',
            symbolSize: 10,
            expandAndCollapse: false,
            itemStyle: {
              color: 'rgb(77, 175, 74)'
            },
            label: {
              color: 'rgb(77, 175, 74)',
              fontWeight: 500,
              fontSize: 10
            },
            lineStyle: {
              color: 'rgb(77, 175, 74)',
              curveness: 0
            },
            emphasis: {
              focus: 'none'
            }
          }
        ]
      }
      myChart.setOption(option)
    },
    setLineChart () {
      const myChart = this.$echarts.init(this.$refs.lineChart)
      const option = {
        xAxis: {
          name: 'In Citations',
          nameLocation: 'center',
          nameRotate: 180,
          nameGap: 30,
          nameTextStyle: {
            fontSize: 12
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            rotate: 90,
            fontSize: 12
          },
          splitLine: {
            show: false
          },
          min: 0,
          max: 600,
          interval: 50
        },
        yAxis: {
          name: 'Out Citations',
          nameLocation: 'center',
          nameGap: 15,
          nameTextStyle: {
            fontSize: 12
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            rotate: 90,
            fontSize: 12
          },
          splitLine: {
            show: false
          },
          min: 0,
          max: 600,
          interval: 100
        },
        splitLine: {
          show: false
        },
        nameTextStyle: {
          lineHeight: 25
        },
        series: [
          {
            type: 'line',
            symbol: 'none',
            lineStyle: {
              color: '#000'
            },
            data: [[0, 3], [1, 3], [2, 3], [3, 3], [4, 3], [5, 3], [6, 3], [7, 3], [8, 3], [9, 3], [10, 3], [11, 3], [12, 3], [13, 3], [14, 3], [15, 3], [16, 3], [17, 3], [18, 3], [19, 3], [20, 3], [21, 3], [22, 3], [23, 3], [24, 3], [25, 3], [26, 3], [27, 3], [28, 3], [29, 3], [30, 3], [31, 2], [32, 2], [33, 2], [34, 2], [35, 2], [36, 2], [37, 2], [38, 2], [39, 2], [40, 2], [41, 2], [42, 2], [43, 2], [44, 2], [45, 2], [46, 2], [47, 2], [48, 2], [49, 2], [50, 2], [51, 2], [52, 2], [53, 2], [54, 2], [55, 2], [56, 2], [57, 2], [58, 2], [59, 2], [60, 2], [61, 2]]
          }
        ]
      }
      myChart.setOption(option)
    },
    // handleTabClick (component) {
    //   if (component.index === '1') {
    //     this.getForceGraphData()
    //     console.log('do getForceGraphData')
    //   }
    // },
    getNetworkData () {
      getRelationByScholarId({ scholarId: this.$route.params.id }).then(res => {
        this.networkData[0].name = res.data[0].name
        const str = res.data[0].relation.substring(1, res.data[0].relation.length - 1)
        const relation = str.split(',')
        relation.forEach(item => {
          if (item !== res.data[0].name) {
            this.networkData[0].children.push({ name: item })
          }
        })
        this.setTreeChart()
      })
    },
    getForceGraphData () {
      getCoAuthorByScholarId({ scholarId: this.$route.params.id }).then(res => {
        const idList = []
        const orgList = []
        const nodes = []
        const links = []
        idList.push(res.data[0].sourceID)
        orgList.push(res.data[0].sourceOrg)
        nodes.push({
          id: res.data[0].source,
          group: 1
        })
        res.data.forEach(item => {
          if (orgList.indexOf(item.targetOrg) === -1) {
            orgList.push(item.targetOrg)
          }
        })

        res.data.forEach(item => {
          // 这里需要对nodes进行去重
          if (idList.indexOf(item.targetID) === -1) {
            idList.push(item.targetID)
            nodes.push({
              id: item.target,
              group: orgList.indexOf(item.targetOrg) + 1
            })
          }

          links.push({
            source: item.source,
            target: item.target,
            value: item.value
          })
        })

        this.forceGraphData.nodes = nodes
        this.forceGraphData.links = links
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 400px;
}

::v-deep .el-tabs--border-card {
  height: 100%;
  min-width: 300px;
  background: #FFF;
  border: 1px solid #eee;
  box-shadow: 0 2px 4px hsla(0, 0%, 92.5%, .5);
}

::v-deep .el-tabs--border-card > .el-tabs__header {
  background-color: #FFF;
  border: 0;
  border-bottom: 1px solid #d5d5d5;
  margin: 0;
}

::v-deep .el-tabs--border-card > .el-tabs__header .el-tabs__item.is-active {
  color: #1679ff;
  background-color: #FFF;
  border-right-color: #FFF;
  border-left-color: #FFF;
  border-bottom-color: #1679ff;
}

::v-deep .el-tabs__content {
  height: 100%;
}

::v-deep .el-tab-pane {
  height: 100%;
}
</style>
