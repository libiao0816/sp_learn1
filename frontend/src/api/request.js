import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'

// 统一 axios 实例
// baseURL 为 /api，由 vite.config.js 的 proxy 转发到后端 8080 端口
const request = axios.create({
  baseURL: '/api',
  timeout: 10000
})

// 认证失效：清除本地 token 并跳转登录页
function handleAuthFailed(msg) {
  localStorage.removeItem('token')
  ElMessage.error(msg || '登录已失效，请重新登录')
  // 已在登录页就不再重复跳转
  if (router.currentRoute.value.path !== '/login') {
    router.push('/login')
  }
}

// 请求拦截器
request.interceptors.request.use(
  (config) => {
    // 登录后写入 token，统一放到 Authorization 头；未登录则不带头，由后端返回 401
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = token
    }
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
      // 401：未登录 / token 失效，跳转登录页
      if (res.code === 401) {
        handleAuthFailed()
        return Promise.reject(new Error(res.msg || '认证失败'))
      }
      ElMessage.error(res.msg || '请求失败')
      return Promise.reject(new Error(res.msg || '请求失败'))
    }
    return res
  },
  (error) => {
    // 后端以 HTTP 401 返回时的兜底处理
    if (error.response && error.response.status === 401) {
      handleAuthFailed()
    } else {
      ElMessage.error(error.message || '网络异常')
    }
    return Promise.reject(error)
  }
)

export default request