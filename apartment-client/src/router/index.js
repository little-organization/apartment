import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * 没有权限要求的基页可以访问所有角色
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/repair',
    component: () => import('@/views/reportrepair/repair'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  }
]

/**
 * asyncRoutes
 * 需要根据用户角色动态加载的路由
 */
export const asyncRoutes = [
  {
    path: '/doorlock',
    component: Layout,
    redirect: '/doorlock',
    children: [{
      path: 'doorlock',
      name: '门锁管理',
      component: () => import('@/views/doorlock/index'),
      meta: { title: '门锁管理', icon: 'lock', roles: ['超级管理员', '系统管理员'] }
    }]
  },

  {
    path: '/apartment',
    component: Layout,
    redirect: '/apartment',
    children: [{
      path: 'apartment',
      name: '公寓管理',
      component: () => import('@/views/apartment/index'),
      meta: { title: '公寓管理', icon: 'home', roles: ['超级管理员', '系统管理员'] }
    }]
  },

  {
    path: '/leaseholder',
    component: Layout,
    redirect: '/leaseholder',
    children: [{
      path: 'leaseholder',
      name: '租户管理',
      component: () => import('@/views/leaseholder/index'),
      meta: { title: '租户管理', icon: 'peoples', roles: ['超级管理员', '系统管理员'] }
    }]
  },

  // {
  //   path: '/aftersales',
  //   component: Layout,
  //   redirect: '/aftersales',
  //   children: [{
  //     path: 'aftersales',
  //     name: '维修人员',
  //     component: () => import('@/views/aftersales/index'),
  //     meta: { title: '维修人员', icon: 'people', roles: ['超级管理员', '系统管理员'] }
  //   }]
  // },

  // {
  //   path: '/check',
  //   component: Layout,
  //   redirect: '/check',
  //   children: [{
  //     path: 'check',
  //     name: '视频查看',
  //     component: () => import('@/views/components-demo/videoplayer'),
  //     meta: { title: '视频查看', icon: 'form', roles: ['超级管理员'] }
  //   }]
  // },

  {
    path: '/reportrepair',
    component: Layout,
    redirect: '/reportrepair',
    meta: { title: '报修管理', icon: 'clipboard' },
    children: [{
      path: 'reportrepair',
      name: '报修管理',
      component: () => import('@/views/reportrepair/index'),
      meta: { title: '报修管理', icon: 'education', roles: ['超级管理员', '系统管理员', '售后人员'] }
    },
    {
      path: 'repair',
      name: '报修单',
      component: () => import('@/views/reportrepair/repair'),
      meta: { title: '报修单', icon: 'form', roles: ['超级管理员', '租户'] }
    },
    {
      path: 'myrepair',
      name: '我的报修',
      component: () => import('@/views/aftersales/myrepair'),
      meta: { title: '我的报修', icon: 'icon', roles: ['售后人员', '租户'] }
    }]
  },

  {
    path: '/messageTemplate',
    component: Layout,
    redirect: '/messageTemplate',
    children: [{
      path: 'messageTemplate',
      name: '短信模板',
      component: () => import('@/views/message/index'),
      meta: { title: '短信模板', icon: 'message', roles: ['超级管理员'] }
    }]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
