import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MainView from '@/views/MainView.vue'
import Front from '../views/Front.vue'
import test123 from '@/views/test123.vue'
import Login from '@/views/Login.vue'
import User from '@/views/manage/file/User.vue'
import Person from '@/views/manage/file/Person.vue'
import Foods from '@/views/manage/file/Foods.vue'
import FoodInfo from '@/views/manage/file/FoodInfo.vue'
import Register from '@/views/Register.vue'
import Manage from '../views/manage/Manage.vue'
import Order from '@/views/manage/file/Order.vue'
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
    path: 'Foods',
    name: 'Foods',
    component: Foods
  },
  {
    path: 'FoodInfo',
    name: 'FoodInfo',
    component: FoodInfo
  },
  {
    path: 'User',
    name: 'User',
    component: User
  },
  {
    path: 'Order',
    name: 'Order',
    component: Order
  }
    //{path: 'user', name: 'user', meta: {title:'用户管理',path: '系统管理/用户管理',requireAuth: true}, component: () => import('../views/manage/User.vue'),},
  ]

},
{
  path: '/test123',
  name: 'test123',
  component: test123
},
{
  path: '/user',
  name: 'user',
  component: User
},
{
    path: '/login',
    name: 'Login',
    component: Login
},
{
  path: '/Register',
  name: 'Register',
  component: Register
},
{
  path: '/Login',
  name: 'Login',
  component: Login
},
{
  path: '/Person',
  name: 'Person',
  component: Person
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

router.beforeEach((to, from, next) => {
    // 首先判断是否需要登录
    if (to.name === 'Login' || to.name === 'Register') {
        // 如果是登录或注册页面，则直接通过
        next();
    } else {
        // 获取token
        let token = localStorage.getItem('token');
        if (token) {
            // 如果存在token，则继续导航
            next();
        } else {
            // 如果不存在token，则显示错误并导航到登录页面
            Message.error("请先登录");
            next({ name: 'Login' }); // 使用name或path都可以，这里使用name更清晰
            // 注意：这里不需要再调用router.push("/login")，因为next已经处理了
        }
    }

    // 如果目标路由与当前路由相同，理论上不需要做额外处理，因为上面的逻辑已经处理了所有情况
    // 但如果你确实需要在这种情况下做一些操作（虽然不常见），你应该将这部分逻辑移到上面
    // 例如，检查查询参数或hash是否有变化，然后决定是否继续

    // 注意：console.log(to, from, next) 应该放在逻辑处理之前或之后，而不是穿插在中间
    // 这里我将它放在最后，仅用于调试
    console.log(to, from); // 注意：next函数不应该被console.log，因为它是一个函数
});

export default router
