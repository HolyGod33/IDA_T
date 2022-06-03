<template>
  <!-- 论文详情页 -->
  <div class="page-container">
    <article class="article">
      <!-- 左侧栏 -->
      <ai-help/>

      <!-- 中间栏 -->
      <paper-details :article-info="articleInfo"/>

      <!-- 右侧栏 -->
      <author :author-info="authorInfo"/>
    </article>
  </div>
</template>

<script>
import { getArticleByArticleId } from '@/api/thesis-details'
import { articleIndex } from '@/constant'

export default {
  components: {
    AiHelp: () => import('./components/AIHelp'),
    PaperDetails: () => import('./components/PaperDetails'),
    Author: () => import('./components/Author')
  },
  created () {
    this.getArticle()
  },
  data () {
    return {
      articleInfo: {},
      authorInfo: null
    }
  },
  methods: {
    getArticle () {
      getArticleByArticleId({ articleId: this.$route.params.id }).then(res => {
        const copy = JSON.parse(JSON.stringify(res.data))
        delete copy.scholarList
        if (copy.keyWord !== '') copy.keyWord = copy.keyWord.split(/[；]|[;]|[，]|,/)
        document.title = copy.title
        // 为了演示PDF
        copy.showPDF = articleIndex.indexOf(copy.title) !== -1
        copy.name = 'pdf/' + copy.title + '.pdf' // 这里是需要预览的 PDF 文件名

        this.articleInfo = copy
        this.authorInfo = res.data.scholarList
      })
    }
  }
}
</script>

<style scoped>
.page-container {
  flex: auto;

  display: flex;
  width: 100%;
  min-height: calc(100vh - 120px);

  padding-bottom: 100px;
}

.article {
  width: 100%;
  color: #333;
  max-width: 2000px;
  margin: 0 auto;
  position: relative;
  --width_left: 20%;
  --width_content: 50%;
  --width_right: 20%;
  --gap: 5%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  padding: 30px 15px 0;
  overflow: hidden;
}
</style>
