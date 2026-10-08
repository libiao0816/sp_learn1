<template>
  <el-container class="layout">
    <el-header class="header">
      <div class="logo">lb-learn1 商城</div>
      <el-menu
        :default-active="activeMenu"
        mode="horizontal"
        router
        class="menu"
        background-color="transparent"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-menu-item index="/product">商品列表</el-menu-item>
        <el-menu-item index="/cart">购物车</el-menu-item>
        <el-menu-item index="/order">订单列表</el-menu-item>
        <el-menu-item index="/user">个人中心</el-menu-item>
        <el-menu-item index="/login">登录 / 注册</el-menu-item>
      </el-menu>
    </el-header>

    <el-main class="main">
      <router-view />
    </el-main>
  </el-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

// 让顶部菜单高亮跟随当前路由
const activeMenu = computed(() => route.path)
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html,
body,
#app {
  height: 100%;
}

.layout {
  min-height: 100%;
}

.header {
  display: flex;
  align-items: center;
  background-color: #409eff;
  padding: 0 24px;
}

.logo {
  color: #fff;
  font-size: 18px;
  font-weight: bold;
  margin-right: 40px;
  white-space: nowrap;
}

.menu {
  flex: 1;
  border-bottom: none !important;
}

.main {
  background-color: #f5f7fa;
  padding: 20px;
}
</style>