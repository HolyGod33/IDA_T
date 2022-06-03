module.exports = {
  // 在Cookie中存放token
  TokenKey: 'ida_token',

  // 在Cookie中存放加密的userId
  UserKey: 'studentId',

  preUrl: '/ida/aps',

  // 可展示pdf的论文目录，这里这样做纯粹是为了答辩时演示，正确的操作是使用文件服务器来查看pdf文件
  articleIndex: [
    'CMfgIA  a cloud manufacturing application mode for industry alliance',
    '基于MapReduce的Bagging决策树优化算法'
  ]
}
