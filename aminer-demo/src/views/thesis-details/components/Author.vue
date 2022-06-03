<template>
  <div class="container">
    <div class="paperAuthors">
      <div class="authorLegend">
        <span>{{ $t('thesisDetails.author') }}</span>
      </div>

      <!-- 作者列表 -->
      <div class="author_content">
        <div class="authors-item" v-for="(item,index) in authorInfo" :key="index">
          <img class="authors-avatar"
               :src="loadAvatar(item.id)"
               alt="">
          <div class="authors-right">
            <div class="authors-title">
              <span class="authors-noIdProfile">
                <router-link :to="{path: `/profile/${item.id}/${item.name}`}">
                  {{ item.name }}
                </router-link>
              </span>
            </div>
          </div>
        </div>
      </div>

      <!-- 评分 -->
      <div class="panel">
        <div class="rating">
          <div class="score">0</div>
          <div>
            <div class="user-rate">
              <span class="rateLabel">{{ $t('thesisDetails.yourRating') }}</span>
              <el-rate/>
            </div>

            <p class="rateLine">
              <span>{{ $t('thesisDetails.noRatings') }}</span>
            </p>
          </div>
        </div>
      </div>

      <!-- 标签 -->
      <div class="panel">
        <div class="panel-head">
          <span>{{ $t('thesisDetails.tags') }}</span>
          <span class="panel-oprs" @click="showTags=!showTags">
            <i class="arrow_icon" :class="showTags? 'arrow_down' : 'arrow_up'"></i>
          </span>
        </div>
        <div class="panel-body">
          <div class="panel-tags-content" v-show="showTags">
            <el-input :placeholder="$t('thesisDetails.addTags')" v-model="tag.input" class="addTags"
                      style="width: 188px"></el-input>
          </div>
        </div>
      </div>

      <!-- 评论 -->
      <div class="panel">
        <div class="panel-head">
          <span>{{ $t('thesisDetails.comments') }}</span>
          <span class="panel-oprs" @click="showComments=!showComments">
            <i class="arrow_icon" :class="showComments? 'arrow_down' : 'arrow_up'"></i></span>
        </div>
        <div class="panel-body">
          <div class="panel-tags-content" v-show="showComments">
            <el-input placeholder="" class="addTags" v-model="comment.input" style="width: 170px"></el-input>
            <div class="commit">
              <button>{{ $t('thesisDetails.submit') }}</button>
            </div>
          </div>
        </div>
      </div>

      <!-- 免责声明 -->
      <!--      <div class="statement_container">-->
      <!--        <span class="statement_title">数据免责声明</span>-->
      <!--        <div class="statement_info">-->
      <!--          页面数据均来自互联网公开来源、合作出版商和通过AI技术自动分析结果，我们不对页面数据的有效性、准确性、正确性、可靠性、完整性和及时性做出任何承诺和保证。若有疑问，可以通过电子邮件方式联系我们：report@aminer.cn-->
      <!--        </div>-->
      <!--      </div>-->
    </div>
  </div>
</template>

<script>
export default {
  props: {
    authorInfo: Array
  },
  data () {
    return {
      showTags: true,
      showComments: true,
      tag: {
        input: ''
      },
      comment: {
        input: ''
      }
    }
  },
  methods: {
    loadAvatar (id) {
      try {
        return require(`@/assets/avatar/${id}.jpg`)
      } catch {
        return '//avatarcdn.aminer.cn/default/default.jpg!80'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: var(--width_right);
}

.authorLegend {
  font-size: 20px;
  font-family: Roboto-Medium, Roboto;
  font-weight: 500;
  color: #666;
  line-height: 24px;
  margin-bottom: 12px;
}

.author_content {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;

  .authors-item {
    display: flex;
    margin: 0 16px 12px 0;
    background: #fff;
    border-radius: 4px;
    border: 1px solid #dadada;
    padding: 12px;

    .authors-avatar {
      width: 40px;
      height: 40px;
      border-radius: 20px;
      margin-right: 6px;
      object-fit: cover;
    }
  }
}

.authors-title {
  font-size: 13px;
}

.authors-noIdProfile {
  opacity: .7;
  color: #1679ff;
  position: relative;
}

.arrow_icon {
  display: inline-block;
  width: 6px;
  height: 6px;
  border: 1px solid #000;
  border-top: none;
  border-left: none;
  margin-left: 5px;
  position: relative;
}

.arrow_down {
  top: -3px;
  transform: rotate(45deg);
}

.arrow_up {
  top: 1px;
  transform: rotate(-135deg);
}

.panel {
  border: 1px solid #eee;
  box-shadow: 2px 2px 4px hsla(0, 0%, 92.5%, .5);
  margin-bottom: 20px;
  margin-right: 16px;

  .panel-head {
    height: 32px;
    padding: 0 10px;
    font-size: 14px;
    color: #020202;
    border-bottom: 1px solid #eee;
    display: flex;
    justify-content: space-between;
    align-items: center;

    i {
      width: 8px;
      height: 8px;
      font-weight: 200;
      border-color: #1679ff;
      border-width: 2px;
      cursor: pointer;
      font-size: 16px;
      margin-left: 8px;
    }

    i:last-child {
      font-size: 12px;
    }

    .panel-oprs {
      display: flex;
      align-items: center;
      cursor: pointer;
    }
  }

  .rating {
    display: flex;
    padding: 8px 10px;

    .rateLine {
      margin-bottom: 0;
    }

    .user-rate {
      display: flex;
      align-items: center;

      .rateLabel {
        margin-right: 5px;
      }
    }

    .score {
      color: #ffae00;
      width: 52px;
      font-size: 30px;
      text-align: center;
      padding-right: 8px;
      margin-right: 8px;
      border-right: 1px solid #eee;
    }
  }
}

.panel-tags-content {
  padding: 12px;

  .addTags {
    margin-bottom: 0;
    margin-top: 8px;
  }

  ::v-deep .el-input__inner {
    height: 32px;
    border-radius: 2px;
  }
}

.commit {
  margin-top: 10px;

  button {
    border: 1px solid #3f81bd;
    background: #f5f5f5;
    width: 80px;
    height: 30px;
    line-height: 30px;
    outline: none;
    cursor: pointer;
  }
}

.statement_container {
  margin: 10px;
  min-height: 200px;

  .statement_title {
    color: #6e6e6e;
  }

  .statement_info {
    margin-top: 10px;
    color: #7c7c7c;
    font-size: 10px !important;
  }
}
</style>
