import request from './request'

// 登录：data 为 { username, password }，axios 会以 JSON 请求体发送
export function login(data) {
  return request.post('/user/login', data)
}

// 注册：data 为 { username, password }，axios 会以 JSON 请求体发送
export function register(data) {
  return request.post('/user/register', data)
}

// 用户详情：后端从 Authorization 头解析当前登录用户，无需传参
export function getUserDetail() {
  return request.post('/user/detail')
}

// 退出登录：后端会把当前 token 加入黑名单使其失效
// token 由 request 拦截器统一放入 Authorization 头，无需额外传参
export function logout() {
  return request.post('/user/logout')
}