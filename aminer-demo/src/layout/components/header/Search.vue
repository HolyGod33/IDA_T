<template>
  <div class="container">
    <el-form :inline="true" class="ant-form">
      <span class="search ant-input-group">
        <el-input v-show="isFuzzy" :placeholder="$t('layout.search.placeholder')" v-model="form.keyword"
                  @keyup.enter.native="search"/>

        <el-input v-show="!isFuzzy" :placeholder="$t('layout.search.term')" v-model="form.keyword"/>
        <el-input class="input-group" v-show="!isFuzzy" :placeholder="$t('layout.search.name')" v-model="form.name"/>
        <el-input class="input-group" v-show="!isFuzzy" :placeholder="$t('layout.search.organization')"
                  v-model="form.organization"/>

        <el-button class="ant-btn ant-btn-primary btn-search" @click="search">
          <svg-icon icon-class="search" class-name="icon-search"/>
        </el-button>

        <el-button class="ant-btn ant-btn-primary btn-turn" @click="switchQueryCondition">
          <svg-icon icon-class="turn" class-name="icon-turn"/>
        </el-button>
      </span>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      isFuzzy: true,
      form: {
        keyword: '',
        name: '',
        organization: ''
      }
    }
  },
  methods: {
    search () {
      if (this.form.keyword !== '') {
        const baseUrl = '/ida/all/findallbyanywords?words='
        window.open(baseUrl + this.form.keyword, '_blank')
      }
    },
    switchQueryCondition () {
      this.isFuzzy = !this.isFuzzy
      if (this.isFuzzy) {
        this.form.name = ''
        this.form.organization = ''
      }
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-input__inner {
  height: 36px;
  border-radius: 2px 0 0 2px;
  font-size: 16px;
}

::v-deep .el-input__inner:focus {
  outline: none;
  border-color: #0050b3;
  border-right: 1px solid #1890ff;
}

.input-group {
  margin-left: 5px;

  ::v-deep .el-input__inner {
    border: 0;
    border-radius: 0;
    font-size: 14px;
  }
}

.el-form {
  max-width: 688px;
  width: 100%;
}

.search {
  zoom: 1;
  display: flex !important;
  position: relative;
  min-height: 36px;
  transition: box-shadow .2s cubic-bezier(.4, 0, .2, 1);
  border-radius: 2px;
}

.ant-btn {
  flex-shrink: 0;
  flex-grow: 0;
}

.ant-btn > i, .ant-btn > span {
  display: inline-block;
  transition: margin-left .3s cubic-bezier(.645, .045, .355, 1);
  pointer-events: none;
}

.ant-btn-primary {
  background: #1679ff !important;
  border-color: #1679ff !important;
}

.btn-search {
  display: inline-block;
  float: none;
  vertical-align: top;
  border-radius: 0;
  margin-right: -1px;

  height: 36px;
  width: auto;
  font-weight: 700;
  font-size: 14px;
  border-right: 1px solid #1890ff;

  .icon-search {
    color: white;
  }
}

.btn-turn {
  display: inline-block;
  float: none;
  vertical-align: top;

  height: 36px;
  padding-right: 12px;
  padding-left: 12px;
  margin-left: 5px;
  border-left: 1px solid rgba(0, 0, 0, .43);
  border-right: 1px solid #1890ff;
  font-size: 16px;
  font-weight: 700;
  border-radius: 0 2px 2px 0;

  .icon-turn {
    color: white;
  }
}
</style>
