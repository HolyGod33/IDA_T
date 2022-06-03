<template>
  <!-- 学者信息页 -->
  <div class="page-container">
    <!-- 左侧栏 -->
    <div class="left-part">
      <card :scholar-details="scholarDetails"/>
      <info :scholar-details="scholarDetails"/>
    </div>

    <!-- 中间栏 -->
    <div class="center-part">
      <interests/>
      <papers/>
    </div>

    <!-- 右侧栏 -->
    <div class="right-part">
      <relation/>
      <author-statistics/>
      <similar-authors/>

      <!-- 免责声明 -->
      <!--      <div class="statement-container">-->
      <!--        <span class="statement-title">数据免责声明</span>-->
      <!--        <div class="statement-info">-->
      <!--          页面数据均来自互联网公开来源、合作出版商和通过AI技术自动分析结果，我们不对页面数据的有效性、准确性、正确性、可靠性、完整性和及时性做出任何承诺和保证。若有疑问，可以通过电子邮件方式联系我们：report@aminer.cn-->
      <!--        </div>-->
      <!--      </div>-->
    </div>
  </div>
</template>

<script>
import { getScholarByScholarId } from '@/api/scholar-details'

const pinyin = require('js-pinyin')
pinyin.setOptions({
  checkPolyphone: false,
  charCase: 1
})

export default {
  components: {
    Card: () => import('./components/Card'),
    Info: () => import('./components/Info'),
    Interests: () => import('./components/Interests'),
    Papers: () => import('./components/PapersAndPatents'),
    Relation: () => import('./components/Relation'),
    AuthorStatistics: () => import('./components/AuthorStatistics'),
    SimilarAuthors: () => import('./components/SimilarAuthors')
  },
  created () {
    this.getScholarDetails()
  },
  data () {
    return {
      scholarDetails: {
        name: '',
        pyName: '',
        // 职称
        title: '',
        // 工作单位
        affiliation: '',
        email: '',
        homepage: '',
        address: '',
        // 工作经历
        experience: '',
        // 教育经历
        education: '',
        // 个人简介
        bio: ''
      }
    }
  },
  methods: {
    getScholarDetails () {
      getScholarByScholarId({ scholarId: this.$route.params.id }).then(res => {
        this.scholarDetails = res.data
        const name = res.data.name
        document.title = name + ' - 智能数据分析 | IDA'
        this.scholarDetails.pyName = pinyin.getFullChars(name.substring(1)) + ' ' + pinyin.getFullChars(name[0])
        if (!this.scholarDetails.title) {
          if (!this.scholarDetails.tutorType) {
            this.scholarDetails.position = ''
          } else {
            this.scholarDetails.position = this.scholarDetails.tutorType
          }
        } else {
          if (!this.scholarDetails.tutorType) {
            this.scholarDetails.position = this.scholarDetails.title
          } else {
            this.scholarDetails.position = this.scholarDetails.title + ' ' + this.scholarDetails.tutorType
          }
        }

        if (!this.scholarDetails.organization) {
          if (!this.scholarDetails.subOrganization) {
            this.scholarDetails.org = ''
          } else {
            this.scholarDetails.org = this.scholarDetails.subOrganization
          }
        } else {
          if (!this.scholarDetails.subOrganization) {
            this.scholarDetails.org = this.scholarDetails.organization
          } else {
            this.scholarDetails.org = this.scholarDetails.organization + ' , ' + this.scholarDetails.subOrganization
          }
        }

        if (!this.scholarDetails.experience) this.scholarDetails.experience = '待完善'
        if (!this.scholarDetails.education) this.scholarDetails.education = '待完善'
        if (!this.scholarDetails.bio) this.scholarDetails.bio = '待完善'
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.page-container {
  padding: 0 30px 100px 15px;
  margin: 0 auto;
  display: flex;
  max-width: 2000px;
  width: 2000px;
}

.left-part {
  //原值24%，为了调整页面改为32%
  min-width: 32%;
  padding-right: 2.6%;
  overflow: auto;
}

.center-part {
  min-width: 45.5%;
  margin-top: 40px;
  margin-right: 2.3%;
}

.right-part {
  min-width: 20.5%;
  margin-top: 40px;
}

.statement-container {
  margin: 10px;

  .statement-title {
    color: #6e6e6e;
  }

  .statement-info {
    margin-top: 10px;
    font-size: 10px !important;
    color: #7c7c7c;
  }
}
</style>
