import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  await getToken() !== 'undefined' && getToken() !== null
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    } else {
      const hasGetUserInfo = store.getters.name
      await hasGetUserInfo !== ''
      if (hasGetUserInfo) {
        next()
      } else {
        // 确定用户是否通过getInfo获取了他的权限角色
        const hasRoles = store.getters.roles && store.getters.roles.length > 0
        if (hasRoles) {
          next()
        } else {
          try {
            // get user info
            // 注意：角色必须是对象数组！例如：['admin']或['developer'，'editor']
            const { roles } = await store.dispatch('user/getInfo')
            // 基于角色的可访问路线图
            const accessRoutes = await store.dispatch('permission/generateRoutes', roles)
            // 动态添加可访问路由
            router.addRoutes(accessRoutes)
            // hack method to ensure that addRoutes is complete
            // 设置replace：true，因此导航不会留下历史记录
            next({ ...to, replace: true })
          } catch (error) {
            // remove token and go to login page to re-login
            await store.dispatch('user/resetToken')
            Message.error(error || 'Has Error')
            next(`/login?redirect=${to.path}`)
            NProgress.done()
          }
        }
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
