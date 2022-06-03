<template>
  <div class="container">
    <div class="menu_paper">
      <!-- 标签栏 -->
      <h2 class="part_title">
        <span @click="checkPaper">
          <button class="title switch-btn" :class="{'select': showPaper}">
            {{ $t('scholarDetails.papers.title') }}
          </button>
        </span>

        <span @click="checkPatent" style="margin-left: 20px;">
          <button class="title switch-btn" :class="{'select': showPatent}">
            {{ $t('scholarDetails.patents.title') }}
          </button>
        </span>
      </h2>

      <!-- 论文 -->
      <div class="part_content" v-show="showPaper">
        <!-- 排序 -->
        <div class="opr_line">
          <div class="pubs_sort">
            <div class="pubs_sort_line">
              <!-- 排序标题 -->
              <span class="label">{{ $t('scholarDetails.papers.sort') }}</span>
              <div class="sorts">
                <!-- 按年份排序 -->
                <span class="sort" :class="{ 'active' : this.paperParams.orderBy === 'year' }"
                      @click="orderPaperByYear">
                  {{ $t('scholarDetails.papers.byYear') }}
                </span>
                <!-- 按引用量排序 -->
                <span class="sort" :class="{ 'active' : this.paperParams.orderBy === 'cite' }"
                      @click="orderPaperByCiteCount">
                  {{ $t('scholarDetails.papers.byCitation') }}
                </span>
              </div>
            </div>
            <div class="pubs_sort_line">
              <span class="label"></span>
              <div></div>
            </div>
          </div>

          <!-- 添加论文按钮 -->
          <!--          <div class="opr_pubs desktop_device">-->
          <!--            <div class="add_paper" @click="addPaper">-->
          <!--              <span class="info">-->
          <!--                <svg-icon icon-class="add" class-name="icon_add"/>{{ $t('scholarDetails.papers.addPaper') }}-->
          <!--              </span>-->
          <!--            </div>-->
          <!--          </div>-->
        </div>

        <!-- 年份 -->
        <div class="pubs_year">
          <!-- 年份标题 -->
          <span class="label">{{ $t('scholarDetails.papers.year') }}</span>
          <div class="years">
            <div>
              <span class="year" :class="{ 'active' : this.paperParams.limit === 0 && this.paperParams.year === ''}"
                    @click="getAllPaper">{{ $t('scholarDetails.papers.all') }}</span>
              <span class="year" :class="{ 'active' : this.paperParams.limit === 10 }"
                    @click="getRecentPaper">{{ $t('scholarDetails.papers.top') }}</span>
            </div>
            <!-- 选择年份 -->
            <div class="select_year" v-show="this.paperParams.orderBy==='year'">
              <div class="select_year_inner">
                <span class="year_bar" v-for="(item,index) in yearCount" :key="index" :ref="`paper-${item.year}`"
                      @click="getPaperByYear(item.year)">
                  <span class="bar" style="max-height: 100%;" :style="`height: ${item.count/maxCount*100}%;`"></span>
                  <span class="bar_label">{{ item.year }}</span>
                </span>
              </div>
            </div>
          </div>
        </div>

        <!-- 论文列表 -->
        <div class="publication_list">
          <div class="paper_item end" v-for="(item,index) in this.paperList" :key="index">
            <div class="content">
              <!-- 论文标题 -->
              <div class="title">
                <div class="title_line">
                  <a
                    :href="$store.getters.preUrl+'/pub/'+item.id"
                    class="title-link" target="_blank">
                    <svg-icon v-show="item.showPDF" icon-class="pdf" class-name="icon_pdf"/>
                    <span class="paper-title">
                      <span>{{ item.title }}</span>
                    </span>
                  </a>
                </div>
              </div>

              <!-- 论文作者 -->
              <div class="authors">
                <span class="author" v-for="(s,index) in item.scholarList" :key="index">
                  <span class="no_id" :class="{'highLight' : s.name === $route.params.name}">
                    <span><a :href="$store.getters.preUrl+'/profile/'+s.id+'/'+s.name" target="_blank">{{ s.name }}</a></span>
                  </span>
                  <span class="mr">,</span>
                </span>
              </div>

              <div class="conf">
                <div class="venue-line">
                  {{
                    item.journalAttr
                  }}<span>{{ item.journalAttr !== null ? ',  (' + item.year + ')' : '(' + item.year + ')' }}</span>
                </div>
              </div>

              <div class="oprs">
                <span class="cited">
                  <span>{{ $t('scholarDetails.papers.cited') }}</span>
                  <span>{{ item.citeCount }}</span>
                </span>

                <span class="bibtex">
                  <svg-icon icon-class="cited" class-name="icon_cited"/>
                  <span>{{ $t('scholarDetails.papers.bibtex') }}</span>
                </span>

                <span class="urlPart">
                  <a class="url" href="" target="_black">
                    <svg-icon icon-class="global" class-name="icon_global"/>
                  </a>
                </span>
              </div>
            </div>
          </div>
        </div>

        <!-- 查看全部 -->
        <div class="more_paper" v-show="this.paperParams.limit!==0">
          <span class="more_btn">
            <span @click="getAllPaper">{{ $t('scholarDetails.papers.viewAll') }}</span>
          </span>
        </div>
      </div>

      <!-- 专利 -->
      <div class="part_content" v-show="showPatent">
        <!-- 排序 -->
        <div class="opr_line">
          <div class="pubs_sort">
            <div class="pubs_sort_line">
              <span class="label">{{ $t('scholarDetails.patents.sort') }}</span>
              <div class="sorts">
                <!-- 目前只能按年份排序，所以这里写死 -->
                <span class="sort active">{{ $t('scholarDetails.patents.byYear') }}</span>
              </div>
            </div>
            <div class="pubs_sort_line">
              <span class="label"></span>
              <div></div>
            </div>
          </div>

          <!-- 添加专利 -->
          <!--          <div class="opr_pubs desktop_device">-->
          <!--            <div class="add_paper" @click="addPatent">-->
          <!--              <span class="info">-->
          <!--                <svg-icon icon-class="add" class-name="icon_add"/>{{ $t('scholarDetails.patents.addPatent') }}-->
          <!--              </span>-->
          <!--            </div>-->
          <!--          </div>-->
        </div>

        <!-- 年份 -->
        <div class="pubs_year">
          <!-- 年份标题 -->
          <span class="label">{{ $t('scholarDetails.patents.year') }}</span>
          <div class="years">
            <div>
              <span class="year" :class="{ 'active' : this.patentParams.limit === 0 && this.patentParams.year === ''}"
                    @click="getAllPatent">{{ $t('scholarDetails.patents.all') }}</span>
              <span class="year" :class="{ 'active' : this.patentParams.limit === 10 }"
                    @click="getRecentPatent">{{ $t('scholarDetails.patents.top') }}</span>
            </div>
            <!-- 选择年份 -->
            <div class="select_year">
              <div class="select_year_inner">
                <span class="year_bar" v-for="(item,index) in patentYearCount" :key="index" :ref="`patent-${item.year}`"
                      @click="getPatentByYear(item.year)">
                  <span class="bar" style="max-height: 100%;" :style="`height: ${item.count/maxCount*100}%;`"></span>
                  <span class="bar_label">{{ item.year }}</span>
                </span>
              </div>
            </div>
          </div>
        </div>

        <!-- 专利列表 -->
        <div class="publication_list">
          <div class="paper_item end" v-for="(item,index) in this.patentList" :key="index">
            <div class="content">
              <!-- 专利标题 -->
              <div class="title">
                <div class="title_line">
                  <span class="paper-title">
                    <span>{{ item.name }}</span>
                  </span>
                </div>
              </div>

              <!-- 专利作者 -->
              <div class="authors">
                <!-- 第一作者 -->
                <span class="author">
                  <span class="no_id" :class="{'highLight' : item.firstScholar.name === $route.params.name}">
                    <span>
                      <a :href="'/#/profile/'+item.firstScholar.id+'/'+item.firstScholar.name" target="_blank">
                        {{ item.firstScholar.name + '(' + $t('scholarDetails.patents.firstInventor') + ')' }}
                      </a>
                    </span>
                  </span>
                  <span class="mr">,</span>
                </span>

                <!-- 其他作者 -->
                <span class="author" v-for="(s,index) in item.scholarList" :key="index">
                  <span class="no_id" :class="{'highLight' : s.name === $route.params.name}">
                    <span><a :href="'/#/profile/'+s.id+'/'+s.name" target="_blank">{{ s.name }}</a></span>
                  </span>
                  <span class="mr">,</span>
                </span>
              </div>

              <div class="conf">
                <div class="venue-line">
                  {{
                    item.applicant
                  }}<span>{{
                    item.applicant !== null && item.organization ? ',  ' + item.organization : item.organization
                  }}</span>
                </div>
              </div>

              <div class="oprs">
                <!-- 专利类型 -->
                <span class="cited">
                  <span>{{ item.type }}</span>
                </span>

                <!-- 专利状态 -->
                <span class="cited2" v-show="item.state">
                  <span>{{ item.state }}</span>
                </span>

                <!-- 专利号 -->
                <span class="cited2" v-show="item.applyNumber">
                  <span>{{ item.applyNumber }}</span>
                </span>
              </div>
            </div>
          </div>
        </div>

        <!-- 查看全部 -->
        <div class="more_paper" v-show="this.patentParams.limit!==0">
          <span class="more_btn">
            <span @click="getAllPatent">{{ $t('scholarDetails.patents.viewAll') }}</span>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getArticleList,
  getPublishArticleCountByScholarId,
  getPatentList,
  getPublishPatentCountByScholarId
} from '@/api/scholar-details'
import { articleIndex } from '@/constant'

export default {
  data () {
    return {
      // 论文部分
      yearCount: [],
      maxCount: 10,
      paperParams: {
        scholarId: this.$route.params.id,
        orderBy: 'year',
        limit: 10,
        year: ''
      },
      paperList: [],
      // 专利部分
      patentYearCount: [],
      patentMaxCount: 10,
      patentParams: {
        scholarId: this.$route.params.id,
        limit: 10,
        year: ''
      },
      patentList: [],
      // 其他
      showPaper: true,
      showPatent: false
    }
  },
  created () {
    this.getPublishArticleCount()
    this.getPublishPatentCount()
    this.getPaper()
  },
  methods: {
    // 论文部分
    getPublishArticleCount () {
      getPublishArticleCountByScholarId({ scholarId: this.$route.params.id }).then(res => {
        this.yearCount = res.data.rows
        this.maxCount = res.data.maxCount
      })
    },
    getPaper () {
      getArticleList(this.paperParams).then(res => {
        res.data.forEach(article => {
          article.showPDF = articleIndex.indexOf(article.title) !== -1
        })
        this.paperList = res.data
      })
    },
    orderPaperByYear () {
      if (this.paperParams.year !== '') this.$refs[`paper-${this.paperParams.year}`][0].classList.remove('active')
      this.paperParams.orderBy = 'year'
      this.paperParams.limit = 10
      this.paperParams.year = ''
      this.getPaper()
    },
    orderPaperByCiteCount () {
      if (this.$store.getters.logged) {
        if (this.paperParams.year !== '') this.$refs[`paper-${this.paperParams.year}`][0].classList.remove('active')
        this.paperParams.orderBy = 'cite'
        this.paperParams.limit = 10
        this.paperParams.year = ''
        this.getPaper()
      } else {
        // 打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },
    getAllPaper () {
      if (this.$store.getters.logged) {
        if (this.paperParams.year !== '') this.$refs[`paper-${this.paperParams.year}`][0].classList.remove('active')
        this.paperParams.limit = 0
        this.paperParams.year = ''
        this.getPaper()
      } else {
        // 打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },
    getRecentPaper () {
      if (this.paperParams.year !== '') this.$refs[`paper-${this.paperParams.year}`][0].classList.remove('active')
      this.paperParams.limit = 10
      this.paperParams.year = ''
      this.getPaper()
    },
    getPaperByYear (year) {
      if (this.$store.getters.logged) {
        if (this.paperParams.year !== '') this.$refs[`paper-${this.paperParams.year}`][0].classList.remove('active')
        this.$refs[`paper-${year}`][0].classList.add('active')
        this.paperParams.limit = 0
        this.paperParams.year = year
        this.getPaper()
      } else {
        // 打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },

    // 专利部分
    getPublishPatentCount () {
      getPublishPatentCountByScholarId({ scholarId: this.$route.params.id }).then(res => {
        this.patentYearCount = res.data.rows
        this.patentMaxCount = res.data.maxCount
      })
    },
    getPatent () {
      getPatentList(this.patentParams).then(res => {
        this.patentList = res.data
      })
    },
    getAllPatent () {
      if (this.$store.getters.logged) {
        if (this.patentParams.year !== '') this.$refs[`patent-${this.patentParams.year}`][0].classList.remove('active')
        this.patentParams.limit = 0
        this.patentParams.year = ''
        this.getPatent()
      } else {
        // 打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },
    getRecentPatent () {
      if (this.patentParams.year !== '') this.$refs[`patent-${this.patentParams.year}`][0].classList.remove('active')
      this.patentParams.limit = 10
      this.patentParams.year = ''
      this.getPatent()
    },
    getPatentByYear (year) {
      if (this.$store.getters.logged) {
        if (this.patentParams.year !== '') this.$refs[`patent-${this.patentParams.year}`][0].classList.remove('active')
        this.$refs[`patent-${year}`][0].classList.add('active')
        this.patentParams.limit = 0
        this.patentParams.year = year
        this.getPatent()
      } else {
        // 打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },

    // 其他部分
    checkPaper () {
      this.showPatent = false
      this.showPaper = true
      this.getPaper()
    },
    checkPatent () {
      this.showPaper = false
      this.showPatent = true
      this.getPatent()
    },
    addPaper () {
      if (this.$store.getters.logged) {
        // 添加论文
      } else {
        // 打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },
    addPatent () {
      if (this.$store.getters.logged) {
        // 添加专利
      } else {
        // 打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
a {
  color: inherit;
}

.container {
  position: relative;
  margin-top: 20px;
}

.menu_paper {
  min-height: 100px;
  border: 1px solid #eee;

  .part_title {
    margin-top: 0;
    margin-bottom: 0;
    padding: 10px 15px;
    height: 51px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    background: #f1f3ff;

    .title {
      font-size: 16px;
      font-weight: 700;
      color: #19207d;
    }

    .switch-btn {
      font-size: 16px;
      font-weight: 700;
      color: #19207d;
      background-color: #f1f3ff;
      border: none;
      outline: none;
      opacity: 0.5;
    }

    .select {
      opacity: 1;
    }

    .num {
      font-size: 13px;
      color: #666;
      margin-left: 15px;
      font-weight: 300;
    }
  }
}

.part_content {
  padding: 0 22px;

  .opr_line {
    display: flex;
    justify-content: space-between;
    padding: 12px 0 0;
    align-items: flex-start;
  }

  .pubs_sort {
    .pubs_sort_line {
      display: flex;
      margin-bottom: 5px;
    }

    .label {
      font-size: 14px;
      margin-right: 16px;
      min-width: 30px;
    }

    .sort {
      margin-right: 12px;
      border-radius: 2px;
      color: #666;
      border: 1px solid #666;
      padding: 3px 6px;
      cursor: pointer;
      line-height: 18px;
      font-size: 14px;
    }

    .sort.active {
      border-color: #0078d7;
      background: #0078d7;
      color: #fff;
    }
  }

  .opr_pubs {
    display: flex;
    position: absolute;
    right: 0;

    .add_paper {
      width: 127px;
      height: 48px;
      background: #fff;
      box-shadow: 0 2px 4px 0 rgba(139, 138, 138, .5);
      border-radius: 24px;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      margin-right: 10px;

      .info {
        height: 16px;
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: #000;
        line-height: 15px;

        .icon_add {
          width: 25px;
          color: #1679ff;
          border-radius: 2px;
        }
      }
    }
  }

  .pubs_year {
    padding: 10px 0 0;
    display: flex;
    align-items: flex-start;

    .label {
      font-size: 14px;
      min-width: 46px;
    }

    .years {
      width: calc(100% - 50px);
      max-width: calc(100% - 50px);

      .year {
        margin-right: 12px;
        border-radius: 2px;
        border: 1px solid #666;
        padding: 3px 6px;
        font-size: 12px;
        line-height: 16px;
        color: #666;
        cursor: pointer;
        margin-bottom: 5px;

        &.active {
          border-color: #0078d7;
          background: #0078d7;
          color: #fff;
        }
      }

      .select_year {
        overflow-x: auto;

        .year_bar {
          min-width: 12px;
          max-width: 22px;
          position: relative;
          margin-right: 2px;
          display: flex;
          align-items: flex-end;
          flex-grow: 1;
          background: #f1f1f1;
          cursor: pointer;

          &.active {
            background-color: #dedede;

            .bar {
              background-color: #ffe98e;
            }
          }

          &:hover {
            background-color: #dedede;

            .bar {
              background-color: #ffe98e;
            }
          }

          .bar {
            background: #8ed0ff;
            display: block;
            width: 100%;
            min-height: 1px;
          }

          .bar_label {
            color: #666;
            width: 100%;
            text-align: center;
            transform: rotate(90deg) scale(.8);
            display: block;
            position: absolute;
            top: 13%;
            user-select: none;
          }
        }

        .active {
          background-color: #dedede;
        }

        .select_year_inner {
          display: flex;
          align-items: stretch;
          height: 44px;
          margin-top: 8px;
          box-sizing: content-box;
        }
      }
    }
  }

  .more_paper {
    text-align: center;
    padding: 10px 0;

    .more_btn {
      color: #1679ff;
      font-size: 13px;
      cursor: pointer;
    }
  }
}

.paper_item.end:last-child {
  border-bottom: none !important;
}

.paper_item {
  padding: 20px 0 14px;

  border-bottom: 1px solid #d5d5d5;
  display: flex;
  position: relative;
  border-radius: 2px;

  border-bottom-style: dashed;

  &:last-child {
    margin-bottom: 0;
  }

  .content {
    flex-grow: 1;
    position: relative;
    top: -2px;
    line-height: 18px;
    width: calc(100% - 49px);
  }

  .title_line {
    padding-right: 100px;
    margin-bottom: 0;
    font-size: 18px;
    display: inline;

    .paper-title {
      line-height: 24px;
      margin-bottom: 0;
      vertical-align: middle;
      font-size: 16px;
      font-family: HiraginoSansGB-W6, HiraginoSansGB;
      font-weight: 400;
      color: #000e28;
    }
  }

  .authors {
    font-size: 12px;
    margin-top: 8px;
    display: flex;
    flex-wrap: wrap;
    overflow: hidden;

    .author {
      color: #067c08;
      font-size: 11px;
      font-family: Roboto-Regular, Roboto;
      font-weight: 400;
      line-height: 13px;
      cursor: pointer;
      padding-bottom: 3px;

      .highLight {
        color: #ec6b10;
        font-style: italic;
      }

      &:last-child {
        .mr {
          display: none;
        }
      }
    }

    .mr {
      margin-right: 5px;
    }
  }

  .conf {
    font-size: 13px;
    color: #444;
    line-height: 28px;
  }

  .oprs {
    width: 100%;
    display: flex;
    align-items: center;
    font-size: 12px;
    margin-top: 5px;
  }

  .oprs > :not(.cited):before {
    content: "|";
    margin: 0 10px;
    color: #d5d5d5;
  }
}

.icon_pdf {
  color: #ff5e5c;
  margin-right: 5px;
  vertical-align: middle;
  margin-bottom: 2px;
}

.cited {
  font-size: 12px;
  font-family: HiraginoSansGB-W3, HiraginoSansGB;
  font-weight: 400;
  color: #0b7f0d;
  line-height: 18px;

  span {
    font-size: 12px;
    font-family: HiraginoSansGB-W3, HiraginoSansGB;
    font-weight: 400;
    color: #000;
    line-height: 18px;
  }

  span + span {
    color: #0b7f0d !important;
    margin-left: 8px;
  }
}

.cited2 {
  font-size: 12px;
  font-family: HiraginoSansGB-W3, HiraginoSansGB;
  font-weight: 400;
  color: #0b7f0d;
  line-height: 18px;

  span {
    font-size: 12px;
    font-family: HiraginoSansGB-W3, HiraginoSansGB;
    font-weight: 400;
    color: #000;
    line-height: 18px;
  }
}

.bibtex {
  color: #067c08;
  cursor: pointer;
  font-weight: 700;

  .icon_cited {
    color: #067c08;
    margin-right: 4px;
  }
}

.urlPart .url {
  color: #666;
}
</style>
