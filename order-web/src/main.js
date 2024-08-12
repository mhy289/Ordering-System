import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 引入 element-ui
import ElementUI from 'element-ui'
// import '../node_modules/element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/index.css'

// @ 代表 src根目录
import '@/assets/css/style.css'

// 引入插件
Vue.use(ElementUI)

// 引入 axios
import axios from 'axios'

// 设置请求的基础路径
axios.defaults.baseURL = "http://localhost:81"
    // 定义响应拦截器
axios.interceptors.response.use(function(resp) {
    let data = resp.data
    return data
})

Vue.prototype.$http = axios

// this.$http

Vue.config.productionTip = false


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
