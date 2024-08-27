import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MainView from '@/views/MainView.vue'
import Front from '../views/Front.vue'
import test123 from '@/views/test123.vue'
import Login from '@/views/Login.vue'
import User from '@/views/User.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Front',
        component: Front
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
        name: 'login',
        component: Login
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
]

const router = new VueRouter({
    routes
})


export default router