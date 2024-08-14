<template>
  <div id="app">
    <div id="title">
      <h1>点餐系统</h1>
    </div>
    <nav>
      <router-link to="/">主页</router-link> |
      <router-link to="/about">订单详情</router-link> |
      <router-link to="/admin" v-if="false">后台管理系统 |</router-link><!--管理员-->
      <router-link to="/login">关于我的</router-link>
    </nav>
    <div id="main"><router-view /></div>

  </div>
</template>

<style>
  #title {
    background: #3498db;
    padding: 10px;
    text-align: left;
    color: #fff;
  }

  #main {
    
  }

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    
    color: #2c3e50;
  }

  nav {
    padding: 30px;
  }

  nav a {
    font-weight: bold;
    color: #2c3e50;
  }

  nav a.router-link-exact-active {
    color: #42b983;
  }

</style>
