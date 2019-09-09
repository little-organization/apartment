import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data,
    transformRequest: [function(data) {
      let ret = ''
      for (const it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/userInfo',
    method: 'post',
    params: { token },
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export function checkUsername(username) {
  return request({
    url: '/checkUsername',
    method: 'post',
    params: { username },
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export function checkPassword(params) {
  return request({
    url: '/ums-admin/checkPassword',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function checkUsernameHave(params) {
  return request({
    url: '/ums-admin/checkUsername',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function changePassword(params) {
  return request({
    url: '/ums-admin/changePassword',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function changeUsername(params) {
  return request({
    url: '/ums-admin/changeUsername',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function changePhone(params) {
  return request({
    url: '/ums-admin/changePhone',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function changeStatus(params) {
  return request({
    url: '/ums-admin/changeStatus',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export function fetchList(params) {
  return request({
    url: '/ums-admin/getUserList',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function delAdminInfoByID(id) {
  return request({
    url: '/ums-admin/delAdminInfo/' + id,
    method: 'get'
  })
}

export function insertAdminInfo(params) {
  return request({
    url: '/ums-admin/insertAdminInfo',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
