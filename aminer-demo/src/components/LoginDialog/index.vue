<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisible"
      :width="'480px'"
      :close-on-click-modal="false"
      @close="close"
    >
      <div class="content">
        <el-tabs>
          <!-- 账户登录 -->
          <el-tab-pane :label="$t('layout.loginDialog.accountLogin')">
            <el-form ref="accountForm" :model="accountForm" :rules="accountRules">
              <!-- 用户名输入框 -->
              <el-form-item prop="username">
                <el-input
                  :placeholder="$t('layout.loginDialog.accountPlaceholder')"
                  v-model="accountForm.username"
                  @input="handleAccountForm"
                />
              </el-form-item>

              <!-- 密码输入框 -->
              <el-form-item prop="password">
                <el-input
                  :placeholder="$t('layout.loginDialog.passwordPlaceholder')"
                  v-model="accountForm.password"
                  type="password"
                  @input="handleAccountForm"
                  @keyup.enter.native="handleAccountLogin"
                />
              </el-form-item>
            </el-form>

            <!-- 登录按钮 -->
            <el-button :disabled="!accountCanLogin" :class="accountCanLogin? 'login-active' : 'button-not-active'"
                       style="width:100%; margin-top:10px; height:46px; font-weight:700; font-size:18px;"
                       @click.native="handleAccountLogin"
            >
              {{ $t('layout.loginDialog.login') }}
            </el-button>

            <!-- 已阅读并同意... -->
            <el-row class="first-row">
              <el-col>
                <el-checkbox v-model="agree" @change="handleAgreeChange">
                  <div class="check-info">
                    <i18n path="layout.loginDialog.agree" :tag="false">
                      <template #policy>
                        <a href="" target="_blank">{{ $t('layout.loginDialog.policy') }}</a>
                      </template>
                      <template #contact>
                        <a href="" target="_blank">{{ $t('layout.loginDialog.contact') }}</a>
                      </template>
                    </i18n>
                  </div>
                </el-checkbox>

                <div class="agree-error" v-show="showAgreeError">
                  {{ $t('layout.loginDialog.agreeError') }}
                </div>
              </el-col>
            </el-row>

            <el-row :gutter="24">
              <el-col :span="9">
                <el-checkbox v-model="accountForm.keepLogin">{{ $t('layout.loginDialog.keepLogin') }}</el-checkbox>
              </el-col>

              <el-col :span="15" style="display:flex; justify-content:flex-end; font-size:12px;">
                <div class="pwd-group">
                  <a href="">{{ $t('layout.loginDialog.forget') }}</a>
                  <a href="">{{ $t('layout.loginDialog.register') }}</a>
                </div>
              </el-col>
            </el-row>

            <div class="others">
              {{ $t('layout.loginDialog.others') }}
            </div>
          </el-tab-pane>

          <!-- 短信登录 -->
          <el-tab-pane :label="$t('layout.loginDialog.SMSLogin')">
            <el-form ref="SMSForm" :model="SMSForm" :rules="SMSRules">
              <!-- 手机号输入框 -->
              <el-form-item prop="phoneNumber">
                <el-input
                  :placeholder="$t('layout.loginDialog.phonePlaceholder')"
                  v-model="SMSForm.phoneNumber"
                  @input="handleSMSForm"
                  class="phone-inner"
                >
                  <el-select v-model="SMSForm.phoneArea" slot="prepend">
                    <el-option label="中国大陆(+86)" value="+86"/>
                  </el-select>
                </el-input>
              </el-form-item>

              <el-form-item prop="code">
                <el-row :gutter="8">
                  <!-- 验证码填写框 -->
                  <el-col :span="14">
                    <el-input
                      :placeholder="$t('layout.loginDialog.codePlaceholder')"
                      v-model="SMSForm.code"
                      @input="handleSMSForm"
                      @keyup.enter.native="handleAccountLogin"
                    />
                  </el-col>

                  <!-- 发送验证码 -->
                  <el-col :span="10">
                    <el-button :disabled="!hasValidPhone" :class="hasValidPhone? 'send-active' : 'button-not-active'"
                               style="width:100%;"
                               @click.native="handleSMSLogin"
                    >
                      {{ $t('layout.loginDialog.send') }}
                    </el-button>
                  </el-col>
                </el-row>
              </el-form-item>
            </el-form>

            <!-- 登录按钮 -->
            <el-button :disabled="!SMSCanLogin" :class="SMSCanLogin? 'login-active' : 'button-not-active'"
                       style="width:100%; height:46px; margin-top:10px; font-weight:700; font-size:18px;"
                       @click.native="handleSMSLogin"
            >
              {{ $t('layout.loginDialog.login') }}
            </el-button>

            <!-- 已阅读并同意... -->
            <el-row class="first-row">
              <el-col>
                <el-checkbox v-model="agree" @change="handleAgreeChange">
                  <div class="check-info">
                    <i18n path="layout.loginDialog.agree" :tag="false">
                      <template #policy>
                        <a href="" target="_blank">{{ $t('layout.loginDialog.policy') }}</a>
                      </template>
                      <template #contact>
                        <a href="" target="_blank">{{ $t('layout.loginDialog.contact') }}</a>
                      </template>
                    </i18n>
                  </div>
                </el-checkbox>

                <div class="agree-error" v-show="showAgreeError">
                  {{ $t('layout.loginDialog.agreeError') }}
                </div>
              </el-col>
            </el-row>

            <el-row :gutter="24">
              <el-col :span="9">
                <el-checkbox v-model="SMSForm.keepLogin">{{ $t('layout.loginDialog.keepLogin') }}</el-checkbox>
              </el-col>

              <el-col :span="15" style="display:flex; justify-content:flex-end; font-size:12px">
                <div class="pwd-group">
                  <a href="">{{ $t('layout.loginDialog.forget') }}</a>
                  <a href="">{{ $t('layout.loginDialog.register') }}</a>
                </div>
              </el-col>
            </el-row>

            <div class="others">
              {{ $t('layout.loginDialog.others') }}
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { validMobile } from '@/utils/validate'
import { sendMessage } from '@/utils/message'

export default {
  name: 'LoginDialog',
  computed: {
    accountRules () {
      return {
        username: [
          {
            required: true,
            message: this.$t('layout.loginDialog.accountPlaceholder'),
            trigger: 'blur'
          }
        ],
        password: [
          {
            required: true,
            message: this.$t('layout.loginDialog.passwordError'),
            trigger: 'blur'
          }
        ]
      }
    },
    SMSRules () {
      return {
        phoneNumber: [
          {
            required: true,
            message: this.$t('layout.loginDialog.phoneError'),
            trigger: 'blur'
          },
          {
            pattern: /^1[3-9]\d{9}$/,
            message: this.$t('layout.loginDialog.phoneError2'),
            trigger: 'change'
          }
        ],
        code: [
          {
            required: true,
            message: this.$t('layout.loginDialog.codeError'),
            trigger: 'blur'
          },
          {
            min: 6,
            max: 6,
            message: this.$t('layout.loginDialog.codeError2'),
            trigger: 'change'
          }
        ]
      }
    }
  },
  data () {
    return {
      dialogVisible: false,
      accountForm: {
        username: '',
        password: '',
        keepLogin: false
      },
      SMSForm: {
        phoneArea: '+86',
        phoneNumber: '',
        code: '',
        keepLogin: false
      },
      // 账号登录验证
      accountCanLogin: false,
      // 短信登录验证
      hasValidPhone: false,
      SMSCanLogin: false,
      // 是否勾选《用户协议》及是否显示未勾选的提示
      agree: false,
      showAgreeError: false
    }
  },
  watch: {
    '$store.getters.needLogin': {
      handler: function (newValue) {
        if (newValue === true) {
          this.showDialog()
        }
      }
    }
  },
  methods: {
    showDialog () {
      this.dialogVisible = true
    },
    // 账号登录验证
    handleAccountForm () {
      this.$refs.accountForm.validate(valid => {
        this.accountCanLogin = valid
      })
    },
    // 验证码登录验证
    handleSMSForm () {
      this.hasValidPhone = validMobile(this.SMSForm.phoneNumber)
      this.SMSCanLogin = this.hasValidPhone && this.SMSForm.code.length === 6
    },
    // 验证是否勾选《隐私政策》
    handleAgreeChange (val) {
      this.showAgreeError = !val
    },
    // 账号登录处理
    handleAccountLogin () {
      if (!this.agree) {
        this.showAgreeError = true
      } else {
        this.$refs.accountForm.validate(valid => {
          if (valid) {
            this.$store.dispatch('user/login', this.accountForm).then(() => {
              this.resetForm()
            })
          } else {
            return false
          }
        })
      }
    },
    // 短信登录处理
    handleSMSLogin () {
      if (!this.agree) {
        this.showAgreeError = true
      } else {
        sendMessage.error(this.$t('layout.loginDialog.SMSNotOpen'))
      }
    },
    // 重置登录对话内容
    resetForm () {
      Object.assign(this.$data, this.$options.data())
      this.$refs.accountForm.clearValidate()
      this.$refs.SMSForm.clearValidate()
    },
    // 关闭登录对话
    close () {
      this.resetForm()
      this.$store.commit('user/SWITCH_STATUS')
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-dialog {
  margin-top: 14vh !important;
  border-radius: 28px 28px 0 0;
}

/*修改遮罩层的背景颜色还需要在全局样式中将.v-modal设为不可见或透明*/
::v-deep .el-dialog__wrapper {
  background: rgba(0, 14, 40, .35);
}

::v-deep .el-dialog__header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  min-height: 50px;
  padding: 0 22px 0 28px;
  color: #fff;
  font-size: 18px;

  position: relative;
  background: url(https://lfs.aminer.cn//misc/skyline_ui/headerBg.png) #36418c no-repeat;
  background-size: cover;
  border-radius: 28px 28px 0 0;

  &:after {
    position: absolute;
    right: 181px;
    bottom: -16px;
    z-index: 1;
    width: 42px;
    height: 42px;
    background: url(https://fileserver.aminer.cn/sys/aminer/circular.png) no-repeat;
    background-size: cover;
    content: "";
  }
}

::v-deep .el-dialog__headerbtn {
  top: 14px;

  .el-dialog__close {
    color: hsla(0, 0%, 100%, .8);;
    font-size: 24px;
    font-weight: 700;
  }
}

::v-deep .el-dialog__body {
  padding: 17px 35px 34px;
  height: 500px;
}

.content {
  width: 100%;
  height: 100%;
}

::v-deep .el-tabs__nav {
  min-height: 58px;
}

::v-deep .el-tabs__item {
  top: 5px;
  font-size: 15px;
  font-weight: 500;
  color: #bbb;

  &:hover {
    color: #222;
    opacity: .7;
  }

  &.is-active {
    color: #222;
  }
}

::v-deep .el-tabs__active-bar {
  background-color: #1679ff;
}

.el-form {
  padding-top: 12px;
}

::v-deep .el-form-item__error {
  font-size: 14px;
}

::v-deep .el-input-group__prepend {
  width: 150px;
  background-color: #fff;
  color: #222;
  border: 0;
}

::v-deep .phone-inner > .el-input__inner {
  border: 0;
}

.phone-inner {
  border: 1px solid #DCDFE6;
  border-radius: 4px;

  &:focus-within {
    border: 1px solid #409EFF;
  }
}

.first-row {
  margin-top: 25px;
  outline: none;
  -ms-user-select: none;
  user-select: none;
  font-size: 12px;
  font-family: Roboto-Regular, Roboto;
  font-weight: 400;
  line-height: 16px;
  height: 24px;
}

.button-not-active {
  border: 0 !important;
  background-color: #f3f3f4 !important;
  color: #bbb !important;
}

.send-active {
  border: 1px solid #ffe300;
  background-color: #ffe300;
  color: #444;
}

.login-active {
  background-color: #1679ff;
  color: #fff;
}

::v-deep .el-checkbox {
  color: #222;
}

::v-deep .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #222;
}

::v-deep .el-checkbox__label {
  font-size: 12px;
}

.check-info a {
  text-decoration: underline;
  margin: 0 6px;
  color: #1679ff;
}

.pwd-group {
  position: relative;

  a {
    position: relative;
    font-size: 12px;
    font-family: Roboto-Regular, Roboto;
    font-weight: 400;
    line-height: 14px;
    color: #1679ff;
    margin-left: 13px;
  }

  a:nth-of-type(2):after {
    content: "";
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    left: -7px;
    border: none;
    width: 1px;
    height: 8px;
    background: #9b9b9b;
  }
}

.agree-error {
  position: absolute;
  font-size: 14px;
  color: #ff4d4f;
  top: 45px;
  text-align: center;
  width: 100%;
}

.others {
  margin-top: 15px;
  text-align: center;
  color: #9b9b9b;
}
</style>
