import axios from 'axios'
import { ElMessage } from 'element-plus'

// 统一 axios 实例
// baseURL 为 /api，由 vite.config.js 的 proxy 转发到后端 8080 端口
const request = axios.create({
  baseURL: '/api',
  timeout: 10000
})

// 请求拦截器
request.interceptors.request.use(
  (config) => {
    // 后端 LoginInterceptor 会拦截所有请求并校验 Authorization 头是否非空
    // 这里统一带上 token，登录后写入真实 token，未登录时用占位值先通过拦截器
    const token = localStorage.getItem('token') || 'dev-token'
    config.headers['Authorization'] = token
    return config
  },
  (error) => Promise.reject(error)
)

// 响应拦截器
request.interceptors.response.use(
  (response) => {
    const res = response.data
    // 后端统一返回 { code, msg, data }，code 为 200 表示成功
    if (res && res.code !== undefined) {
      if (res.code === 200) {
        return res
      }
      ElMessage.error(res.msg || '请求失败')
      return Promise.reject(new Error(res.msg || '请求失败'))
    }
    return res
  },
  (error) => {
    // 401：拦截器返回的认证失败（注意后端用的是对象 toString，不是标准 JSON）
    if (error.response && error.response.status === 401) {
      ElMessage.error('认证失败，请先登录')
    } else {
      ElMessage.error(error.message || '网络异常')
    }
    return Promise.reject(error)
  }
)

export default request