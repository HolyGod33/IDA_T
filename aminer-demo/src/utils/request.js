import axios from 'axios'
import store from '@/store'
import { sendMessage } from '@/utils/message'
import { TokenKey } from '@/constant'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // headers: { 'content-type': 'application/x-www-form-urlencoded' },
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    if (process.env.NODE_ENV === 'development') {
      // console.log(config)
    }

    // do something before request is sent
    if (store.getters.token) {
      // 为每个请求添加token
      config.headers[TokenKey] = store.getters.token
    }
    return config
  },
  error => {
    // do something with request error
    console.log('request error: ' + error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    // 开发环境中在控制台打印response信息
    if (process.env.NODE_ENV === 'development') {
      // console.log(response)
    }

    const res = response.data

    // if the custom code is not 200, it is judged as an error.
    if (res.result_code !== 0) {
      sendMessage({
        message: res.result_msg || 'code error',
        type: 'error',
        duration: 3 * 1000
      })
      return Promise.reject(new Error(res.result_msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('response error: ' + error) // for debug
    // 500: Token expired
    sendMessage({
      message: error.message || error,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
