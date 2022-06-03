import request from '@/utils/request'

// 获取论文信息
export function getArticleByArticleId (params) {
  return request({
    url: '/article/findArticleByArticleId',
    method: 'get',
    params
  })
}
