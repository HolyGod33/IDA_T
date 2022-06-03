<template>
  <div class="container">
    <div class="card">
      <!-- 左下大图标 -->
      <div class="watermark">
        <svg-icon icon-class="watermark" class-name="icon_watermark"/>
      </div>

      <!-- 头像框 -->
      <div class="avatar_block">
        <div class="avatar_bottom_line">
          <svg-icon icon-class="avatar_bottom_line" class-name="icon_avatar_bottom_line"/>
        </div>
        <div class="avatar_upload">
          <img :src="loadAvatar(this.scholarDetails.id)"
               class="avatar" alt=""
               title="">
          <div class="mask_video">
            <svg-icon icon-class="camera" class-name="icon_camera"/>
          </div>
        </div>
      </div>

      <!-- 绑定按钮 -->
      <div class="bind">
        <div class="btn_block">
          <div class="certified_btn" @click="bind">
            <svg-icon icon-class="bind" class-name="icon_bind"/>
            <span>{{ $t('scholarDetails.binding') }}</span>
          </div>
        </div>
      </div>

      <!-- 教师信息 -->
      <div class="expert_content">
        <h1>
          <span class="name">
            <!-- 这里不能换行，否则会产生一个空格 -->
            {{ this.scholarDetails.pyName }}<span class="sub">{{ '(' + this.scholarDetails.name + ')' }}</span>
          </span>
          <span class="position">{{ this.scholarDetails.title }}</span>
        </h1>

        <!-- 底部主页栏 -->
        <!--        <div class="expert_bottom_info">-->
        <!--          <p class="link-list">-->
        <!--            <a class="link" href="https://aminer.org/profile/542a54bedabfae646d551262" target="_blank"-->
        <!--               rel="noopener noreferrer">-->
        <!--              <svg-icon icon-class="aminer_single" class-name="icon_bottom"/>-->
        <!--            </a>-->
        <!--            <span class="link">-->
        <!--              <svg-icon icon-class="homepage" class-name="icon_bottom"/>-->
        <!--            </span>-->
        <!--            <span class="link">-->
        <!--              <svg-icon icon-class="facebook" class-name="icon_bottom"/>-->
        <!--            </span>-->
        <!--            <span class="link">-->
        <!--              <svg-icon icon-class="twitter" class-name="icon_bottom"/>-->
        <!--            </span>-->
        <!--            <span class="link">-->
        <!--              <svg-icon icon-class="youtube" class-name="icon_bottom"/>-->
        <!--            </span>-->
        <!--            <span class="link">-->
        <!--              <svg-icon icon-class="graduation" class-name="icon_bottom"/>-->
        <!--            </span>-->
        <!--          </p>-->
        <!--        </div>-->
      </div>
    </div>

    <!-- 关注科研动态 -->
    <div class="research_trends">
      <button class="ant-btn" @click="follow">
        <svg-icon icon-class="follow" class-name="icon_follow"/>
        <span>{{ $t('scholarDetails.follow') }}</span>
      </button>
    </div>
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
    bind () {
      if (this.$store.getters.logged) {
        // 执行绑定操作
        console.log('do bind')
      } else {
        // 未登录时打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },
    follow () {
      if (this.$store.getters.logged) {
        // 执行关注操作
        console.log('do follow')
      } else {
        // 未登录时打开登录对话框
        this.$store.commit('user/SWITCH_STATUS')
      }
    },
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
a {
  color: inherit;
}

.container {
  padding-top: 53px;
}

.card {
  --avatar_size: 124px;
  --avatar_outer: 129px;
  --text_color: #f0f1f4;
  width: 100%;
  height: 279px;
  color: var(--text_color);
  background: #51449e;
  border-radius: 15px;
  box-shadow: 8px 8px 40px 0 #9e9ea7;
  position: relative;
  margin-top: 12px;

  .icon_watermark {
    position: absolute;
    color: #84e7f4;
    font-size: 183px;
    transform: rotate(90deg);
    bottom: -1px;
    left: 2px;
    opacity: .15;
  }

  .avatar_block {
    position: relative;
  }

  .avatar_bottom_line {
    position: absolute;
    display: flex;
    align-items: flex-end;
    z-index: 2;
    width: var(--avatar_outer);
    height: var(--avatar_outer);
    top: -62px;
    left: calc(50% - var(--avatar_outer) / 2);

    .icon_avatar_bottom_line {
      width: 100%;
      height: 100%;
      color: #84e7f4;
    }
  }

  .avatar_upload {
    width: var(--avatar_size);
    height: var(--avatar_size);
    border-radius: 50%;
    position: absolute;
    left: calc(50% - var(--avatar_size) / 2);
    top: -62px;
    overflow: hidden;
    background: #000;
    cursor: pointer;
    z-index: 3;

    img {
      width: 100%;
      z-index: 2;
      position: absolute;
    }

    .mask_video {
      top: 0;
      position: absolute;
      width: 100%;
      height: 100%;
      align-items: center;
      justify-content: center;
      display: none;
    }
  }

  .bind {
    display: flex;
    justify-content: flex-end;
    padding-right: 20px;
    padding-top: 20px;

    .btn_block {
      display: inline;

      .certified_btn {
        outline: none;
        cursor: pointer;
        transition: all .2s;
        font-size: 18px;
        color: #fff;
        opacity: .6;

        margin-left: 10px;
        height: 26px;
        display: inline-flex;
        align-items: center;
        justify-content: center;

        span {
          font-size: 16px;
          line-height: normal;
        }
      }
    }

    .icon_bind {
      width: 32px;
      height: 16px;
    }
  }

  .expert_content {
    padding-top: calc(var(--avatar_size) / 2 + 30px);
    z-index: 2;
    position: absolute;
    width: 100%;

    h1 {
      font-size: 1em;
      margin-block-start: 1.33em;
      margin-block-end: 1.33em;

      margin: 0;
      font-family: "Open Sans", Arial, Helvetica, Sans-Serif, serif;
      font-weight: 300;

      text-align: center;
      color: var(--text_color);
    }

    .name {
      font-size: 1.333333em;
      font-family: Roboto-Black, Roboto;
      font-weight: 900;
      color: var(--text_color);
      text-transform: capitalize;

      .sub {
        margin-left: 15px;
      }
    }

    .position {
      font-size: .72222222em;
      font-weight: 400;
      font-family: PingFangSC-Regular, PingFang SC;
      color: var(--text_color);
      margin-left: 15px;
    }

    .expert_bottom_info {
      margin-top: 45px;
      display: flex;
      justify-content: center;

      a.link {
        border: 1px solid #fff;
        background: none;
      }

      span.link {
        border: 1px solid #fff;
        opacity: .4;
        background: none;
      }
    }
  }
}

.link-list {
  padding: 0;
  margin: 0;
  display: flex;

  .link {
    width: 38px;
    height: 38px;
    border-radius: 50%;
    margin-right: 15px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}

.icon_bottom {
  color: #fff;
  font-size: 22px;
}

.ant-btn {
  font-size: 14px;
}

.research_trends {
  position: relative;
  width: 100%;
  height: 32px;
  margin: 22px 0 33px;
  overflow: hidden;
  background: #51449e;
  border-radius: 4px;
  opacity: .8;

  white-space: nowrap;

  &:hover {
    opacity: .7;
  }

  button {
    margin-left: 0;
    color: #fff;
    background: transparent;
    border: none;

    border-radius: 2px 0 0 2px;

    position: relative;
    width: calc(100% - 31px);
    height: 100%;
  }
}

.icon_follow {
  margin-right: 5px;
  font-size: 14px;
}
</style>
