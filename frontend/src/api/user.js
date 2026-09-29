import request from './request'

// 登录（后端目前是空实现，仅返回成功）
export function login() {
  return request.post('/user/login')
}

// 注册（后端目前是空实现，仅返回成功）
export function register() {
  return request.post('/user/register')
}