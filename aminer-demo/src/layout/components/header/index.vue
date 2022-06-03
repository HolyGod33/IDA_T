<template>
  <div class="header-container">
    <el-header>
      <div class="logo-container">
        <logo/>
      </div>

      <div class="search-container">
        <search/>
      </div>

      <div class="right-menu">
        <right-menu/>
      </div>
    </el-header>
  </div>
</template>

<script>
export default {
  components: {
    Logo: () => import('./Logo'),
    Search: () => import('./Search'),
    RightMenu: () => import('./RightMenu')
  }
}
</script>

<style lang="scss" scoped>
.header-container {
  width: 100%;
  height: 60px;
  background: linear-gradient(144deg, #062a66, #041c4f 15%, #021642 68%, #472048);
}

.el-header {
  display: flex;
  flex: 0 0 auto;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  max-width: 100%;
  padding: 0 15px;
  margin: 0 auto;
  background: transparent;
}

.el-header > div {
  height: 56px;
}

.logo-container {
  display: flex;
  flex: 1 1;
  align-items: center;
}

.search-container {
  display: flex;
  flex: 1 1;
  flex-direction: column;
  justify-content: center;
  max-width: 600px;
}

.right-menu {
  display: flex;
  flex: 1 1;
  flex-direction: row;
  justify-content: flex-end;
}
</style>
