<template>
  <div class="container">
    <ul class="right-info">
      <!-- 语言切换 -->
      <li class="lang-switch">
        <el-tooltip popper-class="popper-switch-lang" effect="dark" :content="$t('layout.rightMenu.switchLanguage')"
                    placement="bottom">
          <a class="lang" @click="switchLanguage">
            <svg-icon v-show="this.language==='zh'" icon-class="lang_to_en" class-name="icon-lang"/>
            <svg-icon v-show="this.language==='en'" icon-class="lang_to_ch" class-name="icon-lang"/>
          </a>
        </el-tooltip>
      </li>

      <!-- 产品列表 -->
      <el-popover popper-class="popper-product" placement="bottom-end" :offset="50" trigger="click">
        <a class="product" slot="reference">
          <svg-icon icon-class="product_list" class-name="icon-product"/>
        </a>

        <div class="product-container">
          <div class="product-title">{{ $t('layout.rightMenu.productList', { productName: 'IDA' }) }}</div>
          <div class="product-content">
            <a class="productItem" href="http://10.12.45.49:8082/ida/index.html#/AllSubject" target="_blank">
              <img class="productIcon" src="https://fileserver.aminer.cn/sys/aminer/product/AMinerIcon_Channel.png"
                   alt="channel">
              <p>{{ $t('layout.rightMenu.channel') }}</p>
            </a>
            <!--            <a class="productItem" href="">-->
            <!--              <img class="productIcon"-->
            <!--                   src="https://fileserver.aminer.cn/sys/aminer/product/AMinerIcon_Academic Ranking.png" alt="ranking">-->
            <!--              <p>{{ $t('layout.rightMenu.rankings') }}</p>-->
            <!--            </a>-->
            <a class="productItem" href="/ida" target="_blank">
              <img class="productIcon" src="https://fileserver.aminer.cn/sys/aminer/product/AMinerIcon_Mustreading.png"
                   alt="must reading">
              <p>{{ $t('layout.rightMenu.thesisRecommends') }}</p>
            </a>
          </div>
        </div>
      </el-popover>

      <!-- 用户信息 -->
      <el-popover popper-class="popper-user" placement="bottom-end" :offset="11" trigger="hover">
        <div v-if="this.$store.getters.logged" class="user-zone" slot="reference">
          <div class="user-avatar">
            <img :src="this.$store.getters.avatar" alt="">
          </div>
        </div>

        <div v-else class="user-zone" slot="reference">
          <svg-icon icon-class="user_info" class-name="icon-user"/>
          <span class="not-logged">{{ $t('layout.rightMenu.notLogged') }}</span>
        </div>

        <div class="drop-content">
          <div class="info-card">
            <!-- 用户信息 -->
            <div v-if="this.$store.getters.logged" class="top">
              <a class="image" href="">
                <img :src="this.$store.getters.avatar" alt=""/>
              </a>
              <div class="info">
                <p class="name">
                  <a href="">{{ this.$store.getters.username }}</a>
                </p>
              </div>
            </div>

            <div v-else class="top not-log">
              <a class="image" href="">
                <svg-icon icon-class="user" class-name="icon-user"/>
              </a>
              <div class="info">
                <a @click="toLogin">
                  <span>{{ $t('layout.rightMenu.logIn') }}</span>
                </a>
              </div>
            </div>

            <div class="part">
              <a target="_self" class="route list_item" href="">{{ $t('layout.rightMenu.academicProfile') }}</a>
              <a target="_self" class="route list_item" href="">{{ $t('layout.rightMenu.userProfile') }}</a>
            </div>
            <div class="part">
              <a target="_self" class="route list_item" href="">{{ $t('layout.rightMenu.myFollowing') }}</a>
              <a target="_self" class="route list_item" href="">{{ $t('layout.rightMenu.paperCollections') }}</a>
            </div>

            <!-- 退出登录 -->
            <div v-show="this.$store.getters.logged" class="part logout">
              <div class="list_item" @click="logout">
                <a><span>{{ $t('layout.rightMenu.logout') }}</span></a>
              </div>
            </div>
          </div>
        </div>
      </el-popover>
    </ul>
  </div>
</template>

<script>
import { setI18nLanguage } from '@/lang'

export default {
  computed: {
    language () {
      return this.$i18n.locale
    }
  },
  methods: {
    switchLanguage () {
      if (this.language === 'zh') {
        setI18nLanguage('en')
      } else {
        setI18nLanguage('zh')
      }
    },
    toLogin () {
      this.$store.commit('user/SWITCH_STATUS')
    },
    logout () {
      this.$store.dispatch('user/logout')
    }
  }
}
</script>

<style lang="scss" scoped>
ol, ul {
  list-style: none;
}

dl, ol, ul {
  margin-top: 0;
  margin-bottom: 1em;
}

p {
  margin-top: 0;
  margin-bottom: 1em;
}

a {
  color: inherit;
}

.container {
  width: 100%;
}

.right-info {
  display: flex;
  flex: 1 1;
  justify-content: flex-end;
  align-items: center;
  order: 3;
  padding-left: 0;
  margin-bottom: 0;
  margin-left: -30px;
  height: 100%;

  li {
    display: flex;
    align-items: center;
  }

  li:first-child {
    position: relative;
  }
}

.lang-switch {
  margin: 0 13px;

  .lang {
    display: flex;
    align-items: center;

    .icon-lang {
      font-size: 24px;
      color: white;
    }
  }
}

.product {
  display: flex;
  align-items: center;
  margin: 0 13px;

  .icon-product {
    color: #d3d3d3;
    font-size: 22px;
    cursor: pointer;
    transition: all .3s;

    &:hover {
      color: #1679ff;
    }
  }
}

.product-container {
  .product-title {
    margin: 0 24px;
    color: #545454;
    font-weight: 500;
    font-size: 16px;
    line-height: 50px;
    border-bottom: 1px solid #d8d8d8;
  }

  .product-content {
    display: flex;
    flex-wrap: wrap;
    padding: 10px 5px;
    font-size: 13px;
    text-align: center;

    .productItem {
      width: 30%;
      margin: 0 4px;
      padding-top: 6px;
      color: #000;

      &:hover {
        background: #f6fafe;
        border-radius: 8px;

        .productIcon {
          transform: translate3d(0, 1px, -2px);
          filter: drop-shadow(0 0 250px rgba(255, 255, 255, .4));
        }
      }
    }

    .productIcon {
      width: 48px;
      height: 48px;
    }
  }
}

.user-zone {
  margin: 9px 20px 10px 13px;

  position: relative;

  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  color: #fff;
  cursor: pointer;

  .user-avatar {
    width: 30px;
    height: 30px;
    overflow: hidden;
    background: rgba(126, 133, 146, .6);
    border-radius: 50%;

    img {
      display: block;
      width: 100%;
    }
  }

  .icon-user {
    font-size: 24px;
    color: white;
  }

  span {
    margin-left: 20px;
  }
}

.drop-content {
  .info-card {
    .top {
      display: flex;
      flex-grow: 1;
      align-items: center;
      justify-content: space-between;
      padding-bottom: 12px;

      .image {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 64px;
        min-width: 64px;
        height: 64px;
        margin-right: 6px;
        overflow: hidden;
        border-radius: 50%;
        box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .25);

        img {
          width: 100%;
        }

        .icon-user {
          color: #6d7682;
          font-size: 36px;
        }
      }

      .info {
        min-width: 141px;

        p {
          margin-bottom: 0;
          line-height: 25px;
        }

        .name {
          color: #000;
          font-size: 18px;
        }

        a {
          color: inherit;
        }
      }
    }

    .top.not-log {
      justify-content: flex-start;

      .image {
        background: #f0f1f4;
        box-shadow: none;
      }

      .info {
        margin-left: 12px;
        color: #000e28;
        font-weight: 700;
        font-size: 15px;
      }
    }

    .part {
      position: relative;
      margin: 0 -22px;
      padding: 0;
      color: #6e6d7a;
      font-size: 15px;
    }

    .part:before {
      position: absolute;
      top: 0;
      left: 22px;
      display: block;
      width: calc(100% - 44px);
      border-top: 1px solid #dbdbde;
      content: "";
    }

    .list_item {
      display: block;
      height: 40px;
      padding: 0 22px;
      color: #6e6d7a;
      line-height: 40px;
      cursor: pointer;
      transition: all .2s;

      &:hover {
        color: #000e28;
        background: #f4f4f4;
      }
    }

    .logout .list_item {
      height: 80px;
      padding: 20px 22px;
    }
  }

  .info-card:after {
    position: absolute;
    z-index: 9999;
    top: -5px;
    right: 20px;
    display: block;
    width: 10px;
    height: 10px;
    background: #fff;
    border-top: 1px solid #ccc;
    border-right: 1px solid #ccc;
    transform: rotate(-45deg);
    content: "";
  }
}
</style>
