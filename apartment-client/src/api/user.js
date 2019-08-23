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

export function logout() {
  return request({
    url: '/logout',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
