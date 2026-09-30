import request from './request'

// 登录：data 为 { username, password }，axios 会以 JSON 请求体发送
export function login(data) {
  return request.post('/user/login', data)
}

// 注册：data 为 { username, password }，axios 会以 JSON 请求体发送
export function register(data) {
  return request.post('/user/register', data)
}