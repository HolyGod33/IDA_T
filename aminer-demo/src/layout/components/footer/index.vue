<template>
  <div class="foot-container">
    <el-footer>
      <div class="bottom">
        <ul class="links">
          <li>
            <p>
              © 2005-2022 AMiner
              <span><img src="https://originalfileserver.aminer.cn/sys/aminer/gongan.png" alt="gongan">京公网安备11010802035176号</span>
              <a class="record" target="_blank" href="http://beian.miit.gov.cn/">京ICP备20011824号-11</a>
            </p>
          </li>
          <li><a target="_blank" href="/"><span>{{ $t('layout.footer.protocol') }}</span></a></li>
          <li><a target="_blank" href="/"><span>{{ $t('layout.footer.policy') }}</span></a></li>
          <li><a target="_blank" href="/"><span>{{ $t('layout.footer.manual') }}</span></a></li>
          <li><a target="_blank" href="/"><span>{{ $t('layout.footer.contact') }}</span></a></li>
          <li><a target="_blank" href="/"><span>{{ $t('layout.footer.introduction') }}</span></a></li>
          <li><a target="_blank" href="/"><span>{{ $t('layout.footer.joinUs') }}</span></a></li>
          <li><a href="http://zhipu.ai/index.html" target="_blank"
                 rel="noopener noreferrer"><span>{{ $t('layout.footer.zhipu') }}</span></a></li>
        </ul>

        <ul class="supports">
          <li>
            <div class="mail_layer" :style="showMail ? 'display:block' : 'display:none'">
              <!-- 联系我们-标题 -->
              <b class="title">{{ $t('layout.footer.contactUs.title') }}</b>

              <!-- 联系我们-内容 -->
              <p class="content">
                <i18n path="layout.footer.contactUs.contents" :tag="false">
                  <template #FAQ>
                    <a href="/">{{ $t('layout.footer.contactUs.FAQ') }}</a>
                  </template>
                </i18n>
              </p>

              <!-- 联系我们-联系邮箱 -->
              <i18n path="layout.footer.contactUs.emailContents" :tag="false">
                <template #email>
                  <a href="mailto:Report@aminer.cn">{{ $t('layout.footer.contactUs.email') }}</a>
                </template>
              </i18n>
            </div>

            <a aria-label="weibo" target="_blank" rel="noopener noreferrer" class="bgMail"
               @click="showMail = !showMail">
              <svg-icon icon-class="email"/>
            </a>
          </li>

          <li>
            <a href="//weibo.com/arnetminer" aria-label="weibo" target="_blank" rel="noopener noreferrer"
               class="bgWeibo"
               style="background-image:url(https://originalfileserver.aminer.cn/sys/aminer/layout/v1/home_sprites.png)">
            </a>
          </li>
          <li @mouseenter="hideQR = false"
              @mouseleave="hideQR = true">
            <span class="bgWechat"
                  style="background-image:url(https://originalfileserver.aminer.cn/sys/aminer/layout/v1/home_sprites.png)">
            </span>
            <div class="RQcode" :class="{ RQcordHide: hideQR }">
              <img src="https://originalfileserver.aminer.cn/sys/aminer/AMiner科技.jpg" alt="RQcode">
            </div>
          </li>
        </ul>
      </div>
    </el-footer>
  </div>
</template>

<script>
export default {
  data () {
    return {
      hideQR: true,
      showMail: false
    }
  }
}
</script>

<style lang="scss" scoped>
dl, ol, ul {
  margin-top: 0;
  margin-bottom: 1em;
}

ol, ul {
  list-style: none;
}

p {
  margin-top: 0;
  margin-bottom: 1em;
}

a {
  color: inherit;
}

.foot-container {
  position: fixed;
  z-index: 999;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 40px;
}

.el-footer {
  padding: 0 32px;
  flex: 0 0 auto;
  max-width: 100%;
  font-size: 14px;
  background-color: #fafafa;
}

.bottom {
  padding-top: 6px;
  padding-bottom: 2px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .links {
    margin: 0;

    li {
      float: left;
      padding: 0 5px;
      margin: 3px 0;
      font-size: 13px;
      color: #999;

      p {
        margin-bottom: 0;
      }
    }

    p {
      margin-right: 15px;
    }

    a, span {
      color: #999;
    }

    .record {
      margin-left: 8px;
    }
  }

  .supports {
    padding: 0;
    margin-bottom: 0;
    display: flex;
    justify-content: flex-start;

    li {
      position: relative;
      padding: 0;
      margin-left: 11px;
      width: 32px;
      height: 32px;

      display: flex;
      justify-content: center;
      align-items: center;

      color: grey;
      background: #fafafa;
      border: 1px solid #e7e7e7;
      border-radius: 2px;

      .mail_layer {
        position: absolute;
        z-index: 5;
        right: 0;
        bottom: 30px;
        padding: 10px;
        width: 260px;

        font-size: 12px;
        color: #707070;
        background: #fff;
        border: 1px solid #ededed;

        .title {
          margin-bottom: 5px;
          font-size: 14px;
          color: #3a3a3a;
        }

        .content {
          margin: 5px 0;
        }

        a {
          display: inline;
          color: #1e90ed;
          transform: scale(1);
        }
      }

      img {
        width: 30px;
      }

      .bgMail {
        padding-left: 6px;
        font-size: 20px;
      }

      a, span {
        display: block;
        width: 30px;
        height: 30px;
        background-size: 105px auto;
        transform: scale(.6);
      }

      .bgWeibo {
        background-position: -38px -3px;
      }

      .bgWechat {
        background-position: -73px -3px;
      }

      .RQcode {
        position: absolute;
        top: -160px;
        left: -120px;
        width: 160px;
        background: #fff;

        img {
          width: 100%;
        }
      }

      .RQcordHide {
        display: none;
      }
    }
  }
}
</style>
