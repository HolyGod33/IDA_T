import elementZhLocale from 'element-ui/lib/locale/lang/zh-CN'

export default {
  zh: {
    ...elementZhLocale,
    layout: {
      search: {
        placeholder: '请输入姓名或者搜索词',
        term: '关键词',
        name: '姓名',
        organization: '机构'
      },
      rightMenu: {
        switchLanguage: 'Switch to English',
        productList: '{productName} 功能列表',
        channel: '学科',
        rankings: '排名',
        thesisRecommends: '论文推荐',
        notLogged: '未登录',
        logIn: '立即登录',
        academicProfile: '学术主页',
        userProfile: '个人账号',
        myFollowing: '我的关注',
        paperCollections: '论文收藏',
        logout: '退出登录'
      },
      footer: {
        protocol: '用户协议',
        policy: '隐私政策',
        manual: '使用手册',
        contact: '联系我们',
        introduction: '关于我们',
        joinUs: '加入我们',
        zhipu: '智谱.AI',
        contactUs: {
          title: '联系我们',
          contents: '在提交您的问题之前，可以访问我们的{FAQ}，也许里面能找到有用的信息。',
          FAQ: '常见问题',
          emailContents: '联系邮箱：{email}',
          email: 'Report@aminer.cn'
        }
      },
      loginDialog: {
        SMSLogin: '短信登录',
        accountLogin: '账户登录',
        phonePlaceholder: '请输入手机号码',
        phoneError: '请输入手机号码',
        phoneError2: '请输入正确的手机号',
        codePlaceholder: '请输入验证码',
        codeError: '验证码不能为空',
        codeError2: '请输入正确的验证码',
        accountPlaceholder: '请输入账号',
        passwordPlaceholder: '请输入密码',
        passwordError: '请输入密码',
        send: '发送验证码',
        login: '登 录',
        agree: '已阅读并同意{policy}和{contact}',
        agreeError: '未勾选《用户协议》和《隐私条款》！',
        policy: '用户协议',
        contact: '隐私政策',
        keepLogin: '保持登录状态',
        forget: '找回密码',
        register: '注册',
        others: '其他登录方式',
        SMSNotOpen: '短信登录暂未开放'
      }
    },
    scholarDetails: {
      binding: '立即绑定',
      professor: '教授',
      follow: '关注TA的科研动态',
      views: '浏览量：',
      information: '个人信息',
      viewMore: '点击登录，查看更多内容',
      experience: '工作经历',
      education: '教育背景',
      bio: '个人简介',
      researchInterests: '研究兴趣',
      checkPapers: '查看论文',
      checkPatents: '查看专利',
      papers: {
        title: '论文',
        total: '共 {total} 篇',
        addPaper: '添加论文',
        sort: '排序',
        byYear: '按年份排序',
        byCitation: '按引用量排序',
        year: '年份',
        all: '全部',
        recent: '最近(10)',
        top: '前(10)',
        cited: '引用',
        bibtex: '引用',
        viewAll: '查看全部'
      },
      patents: {
        title: '专利',
        total: '共 {total} 项',
        addPatent: '添加专利',
        sort: '排序',
        byYear: '按年份排序',
        year: '年份',
        all: '全部',
        recent: '最近(10)',
        top: '前(10)',
        firstInventor: '第一作者',
        viewAll: '查看全部'
      },
      egoNetwork: '网络关系',
      authorStatistics: '作者统计',
      similarAuthors: '相似作者'
    },
    thesisDetails: {
      AIHelp: 'AI帮你理解科学',
      AIGenerate: {
        title: 'AI 生成解读视频',
        contents: 'AI抽取解析论文重点内容自动生成视频',
        goGenerating: '生成解读视频'
      },
      AITraceability: {
        title: 'AI 溯源',
        contents: 'AI解析本论文相关学术脉络',
        generateMRT: '前往 溯源树'
      },
      cited: '引用：',
      views: '浏览',
      fullText: '下载 PDF 全文',
      viewViaPublisher: '原文链接',
      otherLinks: '其它链接',
      bibtex: '引用',
      mark: '收藏',
      keywords: '关键词',
      abstract: '摘要',
      code: '代码',
      data: '数据',
      ppt: 'PPT (上传PPT)',
      uploadPPT: '上传PPT',
      updateFullText: '更改下载 PDF 全文',
      uploadPDF: '上传PDF',
      author: '作者',
      yourRating: '您的评分 :',
      noRatings: '暂无评分',
      tags: '标签',
      addTags: '添加标签',
      comments: '评论',
      submit: '提交'
    }
  }
}
