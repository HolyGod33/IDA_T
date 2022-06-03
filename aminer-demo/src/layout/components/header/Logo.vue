<template>
  <div class="container">
    <!-- 图标1 -->
    <a class="logo-ckcest" href="/ida">
      <img :src="logo_IDA" style="width:100%;" alt=""/>
    </a>

    <!-- 中间分隔线 -->
    <!--    <span class="split"/>-->

    <!-- 图标2 -->
    <div class="aminer-container" style="margin-left: 5px">
      <img :src="title_IDA" style="width:100%;" alt=""/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      logo_IDA: require('@/assets/images/logo_IDA.png'),
      title_IDA: require('@/assets/images/title_IDA.png')
    }
  }
}
</script>

<style scoped>
.container {
  position: relative;
  margin-bottom: 0;
  display: flex;
  align-items: center;
  order: 1;
  height: 100%;
}

a {
  display: flex;
  align-items: center;
  height: 100%;
}

.logo-ckcest {
  position: relative;
  display: flex;
  order: 1;
  width: 55px;
}

.split {
  position: relative;
  top: 2px;
  margin: 0 10px;
  display: flex;
  vertical-align: middle;
  order: 2;
  line-height: 50px;
  height: 16px;
  border-left: 1px solid #888;
}

.aminer-container {
  position: relative;
  top: 0;
  display: flex;
  align-items: center;
  order: 3;
  width: 150px;
}

.icon-aminer {
  font-size: 72px;
}
</style>
