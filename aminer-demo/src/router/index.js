import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'

/* Layout */
import Layout from '@/layout'

/* Router Modules */

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/',
    redirect: '/profile/439337/张元鸣'
  },

  {
    path: '/pub',
    redirect: '/pub/421204'
  },

  {
    path: '/profile/:id',
    component: Layout,
    children: [
      {
        path: ':name',
        component: () => import('@/views/scholar-details')
      }
    ]
  },

  {
    path: '/pub/:id',
    component: Layout,
    children: [
      {
        path: '',
        component: () => import('@/views/thesis-details')
      }
    ]
  }
]

const createRouter = () => new Router({
  mode: 'history', // require service support
  base: '/ida/aps', // 挂在url的 /ida/aps 下
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

router.beforeEach(async (to, from, next) => {
  if (store.getters.logged) await store.dispatch('user/getLoginInfo')
  next()
})

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter () {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
