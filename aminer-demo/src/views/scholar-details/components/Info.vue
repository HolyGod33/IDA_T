<template>
  <div class="container">
    <!-- 浏览量 -->
    <!--    <div class="views_refresh">-->
    <!--      <div class="views">-->
    <!--        <span class="label">{{ $t('scholarDetails.views') }}</span><span class="num">107</span>-->
    <!--      </div>-->
    <!--    </div>-->

    <!-- 个人信息 -->
    <div class="info_container">
      <section class="resume_card">
        <div class="card_title">
          <span class="title_text">{{ $t('scholarDetails.information') }}</span>
        </div>
        <div class="card_content">
          <div class="expert_info_content">
            <p class="info_line" v-show="this.scholarDetails.position">
              <svg-icon icon-class="position" class-name="icon_left"/>
              <span class="baseInfo">{{ this.scholarDetails.position }}</span>
            </p>

            <p class="info_line" v-show="this.scholarDetails.affiliate">
              <svg-icon icon-class="affiliation" class-name="icon_left"/>
              <span class="baseInfo">{{ this.scholarDetails.affiliate }}
              </span>
            </p>

            <p class="info_line" v-show="this.scholarDetails.org">
              <svg-icon icon-class="affiliation" class-name="icon_left"/>
              <span class="baseInfo">{{ this.scholarDetails.org }}</span>
            </p>

            <div v-if="this.$store.getters.logged">
              <p class="info_line" v-show="this.scholarDetails.email">
                <svg-icon icon-class="email" class-name="icon_left"/>
                <span class="baseInfo">{{ this.scholarDetails.email }}</span>
              </p>

              <p class="info_line" v-show="this.scholarDetails.homepage">
                <svg-icon icon-class="global" class-name="icon_left"/>
                <a class="homepage baseInfo" target="_blank" rel="noopener noreferrer"
                   :href="this.scholarDetails.homepage">{{ this.scholarDetails.homepage }}</a>
              </p>

              <p class="info_line" v-show="this.scholarDetails.address">
                <svg-icon icon-class="address" class-name="icon_left"/>
                <span class="baseInfo">{{ this.scholarDetails.address }}</span>
              </p>
            </div>

            <div v-else class="should_login">
              <p class="tip" @click="toLogin"><span>{{ $t('scholarDetails.viewMore') }}</span></p>
              <div class="icon_group">
                <div class="phone should_login_item" style="background-color: #CB1F27">
                  <svg-icon icon-class="phone" class-name="icon_phone"/>
                </div>
                <div class="mail should_login_item" style="background-color: #8BC165">
                  <svg-icon icon-class="email" class-name="icon_email"/>
                </div>
                <div class="location should_login_item" style="background-color: #1F76CB">
                  <svg-icon icon-class="address" class-name="icon_address"/>
                </div>
                <div class="homepage should_login_item" style="background-color: #FA6400">
                  <svg-icon icon-class="homepage" class-name="icon_homepage"/>
                </div>
                <div class="award should_login_item" style="background-color: #684AD4">
                  <svg-icon icon-class="rank" class-name="icon_rank"/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>

    <!-- 工作经历 -->
    <section class="resume_card">
      <div class="card_title">
        <span class="title_text">{{ $t('scholarDetails.experience') }}</span>
      </div>
      <div class="card_content">
        <div v-if="this.$store.getters.logged" class="resume">
          {{ this.scholarDetails.experience }}
        </div>

        <span v-else class="resume need-login" @click="toLogin">
          <span>{{ $t('scholarDetails.viewMore') }}</span>
        </span>
      </div>
    </section>

    <!-- 教育背景 -->
    <section class="resume_card">
      <div class="card_title">
        <span class="title_text">{{ $t('scholarDetails.education') }}</span>
      </div>
      <div class="card_content">
        <div v-if="this.$store.getters.logged" class="resume">
          {{ this.scholarDetails.education }}
        </div>

        <span v-else class="resume need-login" @click="toLogin">
          <span>{{ $t('scholarDetails.viewMore') }}</span>
        </span>
      </div>
    </section>

    <!-- 个人简介 -->
    <section class="resume_card">
      <div class="card_title">
        <span class="title_text">{{ $t('scholarDetails.bio') }}</span>
      </div>
      <div class="card_content">
        <div class="resume resume_bio">
          {{ this.scholarDetails.bio }}
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  props: {
    scholarDetails: {
      type: Object,
      default: function () {
        return {}
      }
    }
  },
  methods: {
    toLogin () {
      this.$store.commit('user/SWITCH_STATUS')
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  margin-top: -10px;
  border-left: 1px dashed #ccc;
}

.views_refresh {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-top: 20px;
}

.views {
  padding-left: 15px;
  color: #000e28;
  font-weight: 600;
  font-size: .77777778em;
}

.info_container {
  padding-top: 16px;
}

.resume_card {
  padding: 8px 12px;
  position: relative;

  .card_title {
    font-size: 16px;
    color: #373737;
    font-weight: 700;
    margin-bottom: 12px;
    display: flex;
    justify-content: space-between;
    border-bottom: 2px solid #428bca;

    .title_text {
      height: 28px;
      color: #1679ff;
      position: relative;
      display: flex;
      align-items: center;

      &:before {
        content: "";
        margin-left: -14px;
        width: 4px;
        height: 4px;
        border-radius: 50%;
        background: #1679ff;
        position: absolute;
      }
    }
  }

  .resume {
    max-height: 130px;
  }

  .resume_bio {
    text-indent: 2em;
  }

  .card_content::-webkit-scrollbar {
    width: 4px;
    height: 40px;
  }

  .card_content::-webkit-scrollbar-button {
    background-color: #7c2929;
  }

  .card_content::-webkit-scrollbar-thumb {
    background-color: #d8d8d8;
  }
}

.card_content {
  overflow-y: auto;
  overflow-x: hidden;

  .need-login {
    display: block;
    margin-bottom: 0;
    padding: 5px 20px;
    font-size: 16px !important;
    color: #333;
    cursor: pointer;
    background-color: #f5f7f7;

    span {
      font-weight: 400;
      color: #5f6368;
    }
  }
}

.expert_info_content {
  color: #353535;

  .info_line {
    position: relative;

    margin-top: 0;
    margin-bottom: 6px;
    font-size: 14px;
    line-height: 20px;
    min-height: 15px;
    display: flex;

    .icon_left {
      margin-right: 8px;
      width: 14px;
      min-width: 14px;
      height: 20px;
      color: #555;
    }

    .baseInfo {
      word-wrap: break-word;
      word-break: normal;
      width: 95%;
      font-size: 13px;
    }

    .textarea {
      background: transparent;
      color: #353535;
      resize: none;
      border: none;
      padding: 0;
      cursor: auto;

      min-height: auto;
      font-size: 14px;
      line-height: 1.5;
      height: 21px;
      max-height: 9.0072e+15px;
    }

    .homepage {
      color: #353535;
      text-decoration: underline;
    }
  }
}

.should_login {
  margin-top: 40px;
  width: 80%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  background-color: #fff8f8;
  border: 1px dashed #ffe4e4;

  .tip {
    color: #32620b;
    display: flex;
    justify-content: center;
    border-bottom: 1px solid #ffb2b2;
    padding-bottom: 10px;
    font-weight: 700;
    cursor: pointer;
  }

  .icon_group {
    display: flex;
    justify-content: space-between;
  }

  .should_login_item {
    width: 48px;
    height: 48px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    opacity: .9;
    cursor: pointer;
  }

  svg {
    color: #fff;
    width: 20px;
    height: 20px;
  }
}
</style>
