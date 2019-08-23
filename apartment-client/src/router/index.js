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
 * a base page that does not have permission requirements
 * all roles can be accessed
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
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/doorlock',
    component: Layout,
    redirect: '/doorlock',
    children: [{
      path: 'doorlock',
      name: '门锁管理',
      component: () => import('@/views/doorlock/index'),
      meta: { title: '门锁管理', icon: 'lock' }
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
      meta: { title: '公寓管理', icon: 'home' }
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
      meta: { title: '租户管理', icon: 'peoples' }
    }]
  },

  {
    path: '/reportrepair',
    component: Layout,
    redirect: '/reportrepair',
    children: [{
      path: 'reportrepair',
      name: '报修管理',
      component: () => import('@/views/reportrepair/index'),
      meta: { title: '报修管理', icon: 'form' }
    }]
  },

  {
    path: '/check',
    component: Layout,
    redirect: '/check',
    children: [{
      path: 'check',
      name: '视频查看',
      component: () => import('@/views/components-demo/videoplayer'),
      meta: { title: '视频查看', icon: 'form' }
    }]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: '示例',
    meta: { title: '示例', icon: 'example' },
    hidden: true,
    children: [
      {
        path: 'table',
        name: '表格',
        component: () => import('@/views/table/index'),
        hidden: true,
        meta: { title: '表格', icon: 'table' }
      },
      {
        path: 'tree',
        name: '树',
        component: () => import('@/views/tree/index'),
        hidden: true,
        meta: { title: '树', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form表单',
        component: () => import('@/views/form/index'),
        hidden: true,
        meta: { title: 'Form表单', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: '菜单示例',
    hidden: true,
    meta: {
      title: '菜单示例',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        hidden: true,
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            hidden: true,
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            hidden: true,
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                hidden: true,
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                hidden: true,
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            hidden: true,
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        hidden: true,
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: '开发文档外部链接', icon: 'link' },
        hidden: true
      }
    ]
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
