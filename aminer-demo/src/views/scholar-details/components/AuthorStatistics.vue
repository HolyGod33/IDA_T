<template>
  <div class="container">
    <div class="title">
      <span>{{ $t('scholarDetails.authorStatistics') }}</span>
    </div>

    <div class="content">
      <div ref="chart" style="height: 200px;"></div>

      <div class="data_content">
        <p><span class="label">#Papers: </span><span class="num">{{ radarData[0] }}</span></p>
        <p><span class="label">#Citation: </span><span class="num">{{ radarData[6] }}</span></p>
        <p><span class="label">H-Index: </span><span class="num">{{ radarData[5] }}</span></p>
        <p><span class="label">G-Index: </span><span class="num">{{ radarData[4] }}</span></p>
        <p><span class="label">Sociability: </span><span class="num">{{ radarData[3] }}</span></p>
        <p><span class="label">Diversity: </span><span class="num">{{ radarData[2] }}</span></p>
        <p><span class="label">Activity: </span><span class="num">{{ radarData[1] }}</span></p></div>
    </div>
  </div>
</template>

<script>
import { getStatisticsByScholarId } from '@/api/scholar-details'

export default {
  created () {
    this.getRadarData()
  },
  data () {
    return {
      radarData: [0, 0, 0, 0, 0, 0, 0]
    }
  },
  methods: {
    getRadarData () {
      getStatisticsByScholarId({ scholarId: this.$route.params.id }).then(res => {
        this.radarData = [
          res.data.papers,
          res.data.activity,
          res.data.diversity,
          res.data.sociability,
          res.data.gIndex,
          res.data.hIndex,
          res.data.citation
        ]

        this.setChart()
      })
    },
    setChart () {
      const myChart = this.$echarts.init(this.$refs.chart)
      const option = {
        radar: {
          center: ['35%', '50%'],
          radius: [40, 90],
          axisName: {
            color: '#000',
            fontWeight: 500
          },
          splitNumber: 4,
          shape: 'circle',
          nameGap: -33,
          scale: false,
          splitLine: {
            lineStyle: {
              color: '#d0d0cf'
            }
          },
          splitArea: {
            areaStyle: {
              color: ['#ebeba6', '#efefb9', '#f4f4ce', '#f9f9e5']
            }
          },
          indicator: [
            {
              name: '#Papers',
              max: 200
            },
            {
              name: 'Activity',
              max: 5,
              min: -1
            },
            {
              name: 'Diversity',
              max: 5,
              min: -1
            },
            {
              name: 'Sociability',
              max: 5,
              min: -1
            },
            {
              name: 'G-Index',
              max: 50
            },
            {
              name: 'H-Index',
              max: 50
            },
            {
              name: '#Citation',
              max: 2000
            }
          ]
        },
        series: [
          {
            type: 'radar',
            animation: false,
            data: [
              {
                value: this.radarData
              }
            ],
            itemStyle: {
              opacity: 0
            },
            lineStyle: {
              color: '#78be54'

            },
            areaStyle: {
              color: new this.$echarts.graphic.RadialGradient(0.1, 0.6, 1, [
                {
                  color: '#78be54',
                  offset: 0
                }
              ])
            }
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
  position: relative;
  margin-top: 20px;

  .title {
    margin-bottom: 10px;
    color: #000e28;
    font-weight: 600;
    font-size: 16px;
  }

  & > .content {
    height: 220px;
    padding: 10px;
    border: 1px solid #d4d4d4;
    border-radius: 4px;
    overflow: hidden;
  }

  .data_content {
    position: absolute;
    right: 10px;
    top: 35px;

    p {
      margin-bottom: 10px;
      line-height: 16px;
      display: flex;

      .label {
        width: 78px;
        text-align: right;
        margin-right: 5px;
      }

      .num {
        background: #ebebeb;
        padding: 0 5px;
      }
    }
  }
}
</style>
