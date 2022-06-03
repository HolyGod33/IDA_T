import request from '@/utils/request'

// 获取教师信息
export function getScholarByScholarId (params) {
  return request({
    url: '/scholar/findScholarByScholarId',
    method: 'get',
    params
  })
}

// 获取论文
export function getArticleList (params) {
  return request({
    url: '/article/findArticlesByScholarId',
    method: 'get',
    params
  })
}

// 获取发表论文的年份和每年发表的数量
export function getPublishArticleCountByScholarId (params) {
  return request({
    url: '/article/findPublishArticleCountByScholarId',
    method: 'get',
    params
  })
}

// 获取专利
export function getPatentList (params) {
  return request({
    url: '/patent/findPatentsByScholarId',
    method: 'get',
    params
  })
}

// 获取发表专利的年份和每年发表的数量
export function getPublishPatentCountByScholarId (params) {
  return request({
    url: '/patent/findPublishPatentCountByScholarId',
    method: 'get',
    params
  })
}

// 查询研究兴趣
export function getInterestByScholarId (params) {
  return request({
    url: '/scholar/findInterestByScholarId',
    method: 'get',
    params
  })
}

// 查询网络关系
export function getRelationByScholarId (params) {
  return request({
    url: '/scholar/findRelationByScholarId',
    method: 'get',
    params
  })
}

// 查询Co-Author
export function getCoAuthorByScholarId (params) {
  return request({
    url: '/scholar/findCoAuthorByScholarId',
    method: 'get',
    params
  })
}

// 查询作者统计
export function getStatisticsByScholarId (params) {
  return request({
    url: '/scholar/findStatisticsByScholarId',
    method: 'get',
    params
  })
}

// 查询相似作者
export function getSimilarScholarByScholarId (params) {
  return request({
    url: '/scholar/findSimilarScholarByScholarId',
    method: 'get',
    params
  })
}
