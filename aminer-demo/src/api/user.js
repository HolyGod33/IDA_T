import request from '@/utils/request'

// 用户登录
export function login (loginForm) {
  return request({
    url: 'http://10.12.45.49:8081/student/login',
    method: 'post',
    data: {
      studentId: loginForm.username.trim(),
      password: loginForm.password
    }
  })
}

// 注销
// export function logout () {
//   return request({
//     url: '/student/logout',
//     method: 'get'
//   })
// }
