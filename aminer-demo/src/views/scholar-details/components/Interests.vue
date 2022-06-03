<template>
  <div class="container">
    <div class="title">
      <span>{{ $t('scholarDetails.researchInterests') }}</span>
    </div>

    <div class="content">
      <div ref="chart" style="height: 230px;"></div>
    </div>
  </div>
</template>

<script>
import { getInterestByScholarId } from '@/api/scholar-details'

export default {
  created () {
    this.getList()
  },
  data () {
    return {
      list: []
    }
  },
  methods: {
    getList () {
      getInterestByScholarId({ scholarId: this.$route.params.id }).then(res => {
        this.list = res.data
        this.setChart()
      })
    },
    setChart () {
      const myChart = this.$echarts.init(this.$refs.chart)
      const option = {
        // tooltip: {
        //   trigger: 'axis',
        //   axisPointer: {
        //     label: {
        //       formatter: function (params) {
        //         return (new Date(params.value).getFullYear()).toString()
        //       }
        //     }
        //   }
        // },
        color: ['rgb(31, 119, 180)', 'rgb(174, 199, 232)', 'rgb(255, 127, 14)', 'rgb(255, 187, 120)', 'rgb(44, 160, 44)'],
        legend: {
          icon: 'circle',
          left: '10%',
          top: '2%'
          // formatter: function (name) {
          //   if (name.length > 20) return name.substring(0, 20) + '...'
          //   else return name
          // }
        },
        singleAxis: {
          top: 50,
          bottom: 20,
          type: 'time',
          splitNumber: 8,
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            showMaxLabel: true,
            formatter: '{yyyy}'
          },
          splitLine: {
            show: true,
            lineStyle: {
              type: 'solid',
              opacity: 0.9
            }
          }
        },
        series: [
          {
            type: 'themeRiver',
            label: {
              show: false
            },
            itemStyle: {
              opacity: 0.7
            },
            emphasis: {
              label: {
                show: false
              },
              itemStyle: {
                opacity: 0.9
              }
            },
            data: this.list
          }
        ]
      }
      myChart.setOption(option)
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  border: 1px solid #eee;
  height: 290px;
  margin-top: 10px;
}

.title {
  height: 40px;
  background: #f5f5f5;
  padding-left: 20px;
  padding-right: 10px;
  font-size: 16px;
  line-height: 40px;
  font-weight: 700;
  display: flex;
  justify-content: space-between;
}

.content {
  padding: 5px;
  height: calc(100% - 40px);
}
</style>
