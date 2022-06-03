<template>
  <section class="app-main">
    <keep-alive>
      <router-view :key="key"/>
    </keep-alive>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    key () {
      return this.$route.path
    }
  }
}
</script>

<style scoped>
.app-main {
  position: relative;
  display: flex;
  flex: auto;
  width: 100%;
  min-height: calc(100vh - 120px);
}
</style>
