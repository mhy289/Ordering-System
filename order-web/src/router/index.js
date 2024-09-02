import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MainView from '@/views/MainView.vue'
import Front from '../views/Front.vue'
import test123 from '@/views/test123.vue'
import Login from '@/views/Login.vue'
import User from '@/views/User.vue'
import FoodInfo from '@/views/manage/file/FoodInfo.vue'
import Register from '@/views/Register.vue'
import Manage from '../views/manage/Manage.vue'
import {
  Message
} from 'element-ui';

Vue.use(VueRouter)

const routes = [{
  path: '/',
  name: 'Front',
  component: Front
},

{
  path: '/manage',
  name: 'manage',
  component: () =>
    import('../views/manage/Manage.vue'),
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
    component: () =>
      import('../views/manage/Home.vue'),

  },
  {
    path: 'FoodInfo',
    name: 'FoodInfo',
    component: FoodInfo
  },
    //{path: 'user', name: 'user', meta: {title:'用户管理',path: '系统管理/用户管理',requireAuth: true}, component: () => import('../views/manage/User.vue'),},
  ]

},
{
  path: '/test123',
  name: 'test123',
  component: test123
},
{
  path: '/login',
  name: 'Login',
  component: Login
},
{
  path: '/user',
  name: 'user',
  component: User
},
{
  path: '/Register',
  name: 'Register',
  component: Register
},
{
  path: '/about',
  name: 'about',
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import( /* webpackChunkName: "about" */ '../views/AboutView.vue')
}
]

const router = new VueRouter({
  routes
})

export default router