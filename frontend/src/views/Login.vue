<template>
  <div class="login-wrap">
    <el-card class="login-card">
      <template #header>
        <div class="card-title">登录 / 注册</div>
      </template>

      <el-alert
        type="info"
        :closable="false"
        show-icon
        title="后端 /user/login 与 /user/register 目前是空实现，仅返回成功，这里只演示前端调用流程。"
        style="margin-bottom: 16px"
      />

      <el-form :model="form" label-width="70px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password" placeholder="请输入密码" />
        </el-form-item>
      </el-form>

      <div class="btn-group">
        <el-button type="primary" :loading="loading" @click="handleLogin">登录</el-button>
        <el-button :loading="loading" @click="handleRegister">注册</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { login, register } from '../api/user'

const router = useRouter()
const loading = ref(false)

const form = reactive({
  username: '',
  password: ''
})

// 登录：调用后端接口，成功后写入 token（后端暂未真正下发 token，这里先写占位值）
async function handleLogin() {
  if (!form.username || !form.password) {
    ElMessage.warning('请输入用户名和密码')
    return
  }
  loading.value = true
  try {
    await login()
    // 后端 LoginInterceptor 只校验 Authorization 头是否非空，
    // 后续接入真实 JWT 后，这里换成接口返回的 token 即可
    localStorage.setItem('token', `token-${form.username}`)
    ElMessage.success('登录成功')
    router.push('/product')
  } finally {
    loading.value = false
  }
}

async function handleRegister() {
  if (!form.username || !form.password) {
    ElMessage.warning('请输入用户名和密码')
    return
  }
  loading.value = true
  try {
    await register()
    ElMessage.success('注册接口调用成功（后端暂为空实现）')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-wrap {
  display: flex;
  justify-content: center;
  padding-top: 60px;
}

.login-card {
  width: 460px;
}

.card-title {
  font-size: 16px;
  font-weight: bold;
}

.btn-group {
  display: flex;
  justify-content: center;
  gap: 12px;
  margin-top: 8px;
}
</style>