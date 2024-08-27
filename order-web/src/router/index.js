import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MainView from '@/views/MainView.vue'
import Test1 from '@/components/Test1.vue'
import Login from '@/views/Login.vue'
import User from '@/views/User.vue'
import Register from '@/views/Register.vue'
import Manage from '../views/manage/Manage.vue'
import {
  Message
} from 'element-ui';

Vue.use(VueRouter)

const routes = [{
    path: '/a',
    name: 'main',
    component: MainView
  },
  {
    path: '/user',
    name: 'user',
    component: User
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/',
    name: 'manage',
    component: Manage,
    redirect: "/manage/home",
    meta: {
      title: '后台',
      path: '后台',
      requireAuth: true
    },
    children: [{
      path: 'home',
      name: 'home',
      meta: {
        title: '主页',
        path: '主页',
        requireAuth: true
      },
      component: () => import('../views/manage/Home.vue'),
    }, {
      path: 'test1',
      name: 'test1',
      meta: {
        title: 'cs',
        path: 'cs',
        requireAuth: true
      },
      component: () => import('../views/manage/test1.vue'),
    }]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( /* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  routes
})


router.beforeEach((to, from, next) => {
  console.log(to, from, next)
  // 判断是否需要登录
  if (to.name === 'login' || to.name === 'register') {
    next()
  } else {
    // 获取token
    let token = localStorage.getItem('token')
    if (token) {
      next()
    } else {
      Message.error("请先登录1")
      next({
        name: 'login'
      })
      //router.push("/login")
    }
  }
})

export default router
