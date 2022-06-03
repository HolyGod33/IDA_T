<template>
  <div class="container">
    <div class="title">
      <span>{{ $t('scholarDetails.similarAuthors') }}</span>
    </div>
    <ul class="author_list">
      <li class="" v-for="(item,index) in this.list" :key="index">
        <span>
          <a :href="$store.getters.preUrl+'/profile/'+item.id2+'/'+item.scholar2" target="_blank">
            <div class="personImg">
              <span></span>
              <img class="" :src="loadAvatar(item.id2)" alt="">
            </div>
            <p class="authors-name">{{ item.scholar2 }}</p>
          </a>
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
import { getSimilarScholarByScholarId } from '@/api/scholar-details'

export default {
  data () {
    return {
      list: []
    }
  },
  created () {
    this.getList()
  },
  methods: {
    getList () {
      getSimilarScholarByScholarId({ scholarId: this.$route.params.id }).then(res => {
        this.list = res.data
      })
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
ol, ul {
  list-style: none;
}

.title {
  height: 40px;
  line-height: 40px;
  font-size: 16px;
  font-weight: 600;
  margin-top: 20px;
}

.author_list {
  padding: 15px 0;
  overflow: hidden;
  margin: 0;
  display: flex;
  flex-wrap: wrap;

  li {
    width: 78px;
    margin-right: 10px;
    margin-bottom: 20px;

    a {
      color: #777;
      font-size: 12px;
      display: flex;
      flex-direction: column;
      align-items: center;
      transition: opacity .2s ease-in-out;
    }

    .personImg img {
      width: 60px;
      height: 60px;
      border-radius: 24px;
      object-position: top center;
      object-fit: cover;
      box-shadow: 0 4px 16px 0 hsla(0, 0%, 40.4%, .5);
    }

    p {
      margin: 0;
    }
  }
}
</style>
