<template>
  <div class="app-wrapper">
    <my-header/>
    <app-main/>
    <!-- 应要求删除footer，如有需要可以修改并加回来 -->
    <!--    <my-footer/>-->

    <!-- transition—name设置为空值表示不使用动画效果，如果想要淡入淡出效果可设置为fade -->
    <back-to-top :custom-style="myBackToTopStyle" :visibility-height="50" :back-position="0" transition-name=""/>
    <!-- 登录对话框 -->
    <login-dialog/>
  </div>
</template>

<script>
export default {
  name: 'Layout',
  components: {
    MyHeader: () => import('./components/header'),
    AppMain: () => import('./components/AppMain'),
    // MyFooter: () => import('./components/footer'),
    BackToTop: () => import('@/components/BackToTop'),
    LoginDialog: () => import('@/components/LoginDialog')
  },
  data () {
    return {
      // customizable button style, show/hide critical point, return position
      myBackToTopStyle: {
        right: '2px',
        bottom: '2px',
        width: '40px',
        height: '37px',
        'border-radius': '0px'
        // 'line-height': '17px' // 请保持与高度一致以垂直居中 Please keep consistent with height to center vertically
        // 'background': 'rgb(102, 102, 102)'// 按钮的背景颜色 The background color of the button
      }
    }
  }
}
</script>

<style scoped>
.app-wrapper {
  position: relative;
  padding-right: 0;

  display: flex;
  flex: auto;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  box-sizing: content-box;

  width: 100vw;
  min-height: 100%;
  line-height: 1.6;
  background: #fff;
}
</style>
