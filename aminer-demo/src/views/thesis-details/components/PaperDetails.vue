<template>
  <div class="container">
    <!-- 大标题 -->
    <div class="titleline">
      <h1 class="titleInner">
        <span class="text">
          {{ articleInfo.title }}
        </span>
      </h1>
    </div>

    <!-- 小标题 -->
    <p class="confInfo">{{
        articleInfo.journalAttr
      }}<span>{{
          articleInfo.journalAttr !== null ? ',  (' + articleInfo.year + ')' : '(' + articleInfo.year + ')'
        }}</span></p>

    <!-- 引用 & 浏览 -->
    <div class="tagLine">
      <div class="misctags">
        <span class="cited">
          <span>{{ $t('thesisDetails.cited') }}</span>
          <strong>{{ articleInfo.citeCount }}</strong>
        </span>
        <span class="split">|</span>
        <span class="views">
          <span>
            <span>{{ $t('thesisDetails.views') }}</span>
            <span>{{ articleInfo.views || '0' }}</span>
          </span>
        </span>
      </div>
    </div>

    <!-- 按钮组 -->
    <div class="btn-group">
      <div class="download" v-show="articleInfo.showPDF">
        <div class="pdf unifiedStyle">
          <svg-icon icon-class="pdf2" class-name="icon_pdf2"/>
          <span>{{ $t('thesisDetails.fullText') }}</span>
        </div>
      </div>
      <div class="origin unifiedStyle">
        <a href="" target="_blank">
          <svg-icon icon-class="global" class-name="icon_global"/>
          <span>{{ $t('thesisDetails.viewViaPublisher') }}</span>
        </a>
      </div>
      <div class="other-links unifiedStyle">
        <svg-icon icon-class="origin" class-name="icon_origin"/>
        <span>{{ $t('thesisDetails.otherLinks') }}</span>
        <svg-icon icon-class="shouqi" class-name="icon_shouqi" style="margin-right: 0"/>
      </div>
      <div class="bibtexBlock unifiedStyle2">
        <span class="bibtex">
          <svg-icon icon-class="cited" class-name="icon_cited"/>
          <span>{{ $t('thesisDetails.bibtex') }}</span>
        </span>
      </div>
      <div class="mark unifiedStyle2">
        <svg-icon icon-class="add" class-name="icon_add"/>
        <span>{{ $t('thesisDetails.mark') }}</span>
      </div>
    </div>

    <!-- 关键词 -->
    <div class="keywords">
      <div class="keywords-title">
        <span>{{ $t('thesisDetails.keywords') }}</span>
      </div>
      <div class="keywords-list">
        <div class="keywords-item" v-for="(item,index) in articleInfo.keyWord" :key="index">
          <!-- todo 暂时不跳回搜索页面,有需要的话外面套一层a标签即可 -->
          <a :href="'/ida/all/findallbyanywords?words='+item">{{ item }}</a>
<!--          {{ item }}-->
        </div>
      </div>
    </div>

    <!-- 摘要 -->
    <div class="abstract">
      <div class="abstract-title"><span>{{ $t('thesisDetails.abstract') }}</span></div>
      <div class="abstract-contents">
        {{ articleInfo.articleAbstract }}
      </div>
    </div>

    <!-- 代码 & 数据 -->
    <div class="code-data">
      <div class="code-data-title">
        <span class="code-data-label">{{ $t('thesisDetails.code') }}</span>
        <span style="font-weight: normal">：</span>
        <svg-icon icon-class="edit" class-name="icon-edit"/>
      </div>
      <div class="code-data-title">
        <span class="code-data-label">{{ $t('thesisDetails.data') }}</span>
        <span style="font-weight: normal">：</span>
        <svg-icon icon-class="edit" class-name="icon-edit"/>
      </div>
    </div>

    <!-- pdf阅读器 & 上传pdf -->
    <div class="tab-container">
      <el-tabs type="border-card">
        <!-- PDF阅读器 -->
        <el-tab-pane :label="$t('thesisDetails.fullText')">
          <PDFJSViewer v-if="articleInfo.showPDF" :path="`${path}`" :fileName="`${articleInfo.name}`"/>
          <el-upload v-else
                     class="upload-demo"
                     drag
                     action="https://jsonplaceholder.typicode.com/posts/"
                     multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">{{ $t('thesisDetails.uploadPDF') }}</div>
          </el-upload>
        </el-tab-pane>

        <!-- 上传PPT -->
        <el-tab-pane :label="$t('thesisDetails.ppt')">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">{{ $t('thesisDetails.uploadPPT') }}</div>
          </el-upload>
        </el-tab-pane>
      </el-tabs>

      <!--      <div class="upload-button">-->
      <!--        <span class="button-container">-->
      <!--          <button class="ant-btn">-->
      <!--            <svg-icon icon-class="cloud" class-name="icon-cloud"/>-->
      <!--            <span>{{ $t('thesisDetails.updateFullText') }}</span>-->
      <!--          </button>-->
      <!--        </span>-->
      <!--      </div>-->
    </div>
  </div>
</template>

<script>
export default {
  props: {
    articleInfo: Object
  },
  components: {
    PDFJSViewer: () => import('@/components/PDFJSViewer')
  },
  data () {
    return {
      // name: 'pdf/show.pdf', // 这里是需要预览的 PDF 文件名
      path: '/ida/static/lib/web/viewer.html'
    }
  }
}
</script>

<style lang="scss" scoped>
h1, h2, h3, h4 {
  margin: 0;
  font-family: "Open Sans", Arial, Helvetica, Sans-Serif, serif;
  font-weight: 300;
  color: rgba(0, 0, 0, .85);
}

p {
  margin-top: 0;
  margin-bottom: 1em;
}

a {
  color: inherit;
}

.container {
  flex-grow: 1;
  width: var(--width_content);
  margin-left: var(--gap);
  margin-right: var(--gap);
  overflow: hidden;

  .titleline {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 12px;

    .titleInner {
      line-height: 25px;
      display: flex;
      flex-wrap: wrap;

      .text {
        margin-bottom: 12px;
        font-size: 24px;
        font-weight: 700;
        font-family: "Times New Roman";
      }
    }
  }

  .confInfo {
    font-size: 14px;
    color: #6b6b6b;
    margin-bottom: 12px;
  }

  .tagLine {
    display: flex;
    align-items: center;
    margin-bottom: 8px;
    flex-wrap: wrap;
  }
}

.misctags {
  margin-right: 12px;
  font-size: 13px;

  .cited {
    color: #999;
    font-size: 13px;
  }

  .views {
    color: #1679ff;
    font-size: 13px;
    font-weight: bolder;

    span {
      color: #555;
    }

    span > span {
      margin-right: 8px;
      font-size: 12px;
      font-family: HiraginoSansGB-W3, HiraginoSansGB;
      font-weight: 400;
      color: #000;
      line-height: 18px;
    }

    span > span:nth-child(2) {
      font-size: 12px;
      font-family: HiraginoSansGB-W3, HiraginoSansGB;
      font-weight: 400;
      color: #0b7f0d;
      line-height: 18px;
    }
  }

  .split {
    margin: 0 10px;
    color: #d5d5d5;
  }
}

.btn-group {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
  margin-top: 20px;
  flex-wrap: wrap;

  .unifiedStyle {
    color: #fff;
    height: 30px;
    display: flex;
    font-size: 14px;
    justify-content: center;
    align-items: center;
    border-radius: 4px;
    padding-left: 10px;
    padding-right: 10px;
    font-weight: 700;
    margin-right: 10px;
    margin-bottom: 10px;

    svg {
      margin-right: 3px;
    }
  }

  .download {
    margin-right: 10px;

    .pdf {
      background-color: #fa6402;
      width: 100%;
      font-family: SourceHanSerifSC-Bold, SourceHanSerifSC;
      position: relative;

      span {
        padding-right: 10px;
      }

      .icon_pdf2 {
        width: 29px;
        height: 26px;
      }
    }
  }

  .origin {
    background-color: #1a73e8;
    cursor: pointer;
  }

  .other-links {
    background-color: #1a73e8;
    cursor: pointer;

    span {
      margin-right: 5px;
    }
  }

  .unifiedStyle2 {
    height: 30px;
    display: flex;
    font-size: 14px;
    justify-content: center;
    align-items: center;
    border-radius: 4px;
    padding-left: 10px;
    padding-right: 10px;
    font-weight: 700;
    margin-right: 10px;
    margin-bottom: 10px;

    svg {
      margin-right: 3px;
    }
  }

  .bibtexBlock {
    border: 1px solid #5fb108;

    .bibtex {
      color: #067c08;
      cursor: pointer;
      font-weight: 700;
    }
  }

  .mark {
    padding: 0 10px;
    background: #fff;
    box-shadow: 0 4px 13px 0 #e8edf4;
    border: 1px solid #5aaa06 !important;
    height: 30px !important;
    border-radius: 4px !important;
    margin-left: 10px;

    .icon_add {
      width: 12px;
      height: 11px;
      color: #5aaa06;
      box-shadow: 0 4px 13px 0 #e8edf4;
      margin-bottom: 6px !important;
    }

    span {
      opacity: 1 !important;
      padding-left: 0;
      color: initial;

      margin-left: 4px;
      font-size: 13px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      line-height: 18px;
      text-shadow: 0 4px 13px #e8edf4;
    }
  }
}

.keywords {
  margin-bottom: 12px;
  padding-right: 10px;

  .keywords-title {
    margin-bottom: 8px;

    span {
      font-weight: bolder;
      font-size: 20px;
      white-space: nowrap;
    }
  }

  .keywords-list {
    display: flex;
    flex-wrap: wrap;

    .keywords-item {
      border: 1px solid #abc;
      border-radius: 3px;
      padding: .1em .3em;
      margin-right: .5em;
      margin-bottom: .5em;
      display: inline-block;
      color: #002050;
      //user-select: none;
    }
  }
}

.abstract {
  margin-bottom: 12px;

  .abstract-title {
    margin-bottom: 6px;
    font-size: 20px;
    font-weight: bolder;
  }

  .abstract-contents {
    color: #555;
  }
}

.code-data {
  margin-bottom: 12px;

  .code-data-title {
    margin-bottom: 6px;
    font-size: 16px;
    font-weight: bolder;
  }

  .icon-edit {
    font-size: 13px;
  }
}

.tab-container {
  position: relative;
}

.upload-button {
  padding: 4px 0;
  position: absolute;
  top: 0;
  right: 0;

  .button-container {
    margin-right: 12px;
  }

  .ant-btn {
    height: 32px;
    padding: 0 10px;
    font-size: 12px;
    background: #f5f5f5;
    border-radius: 0;

    .icon-cloud {
      font-size: 14px;
      margin-right: 5px;
    }
  }
}

::v-deep .el-tabs--border-card {
  background: #FFF;
  border: 1px solid #eee;
  box-shadow: 0 2px 4px hsla(0, 0%, 92.5%, .5);
}

::v-deep .el-tabs--border-card > .el-tabs__header {
  background-color: #FFF;
  border: 0;
  border-bottom: 1px solid #d5d5d5;
  margin: 0 0 0 14px;
}

::v-deep .el-tabs--border-card > .el-tabs__header .el-tabs__item {
  margin: 5px 32px 0 0;
}

::v-deep .el-tabs--border-card > .el-tabs__header .el-tabs__item.is-active {
  color: #1679ff;
  background-color: #FFF;
  border-right-color: #FFF;
  border-left-color: #FFF;
  border-bottom-color: #1679ff;
  border-bottom: 2px solid;
}

::v-deep .el-upload {
  width: 100%;
}

::v-deep .el-upload-dragger {
  width: 100%;
}
</style>
