import request from '@/utils/request'

export function userInfoList(username) {
  return request({
    url: '/user/selectUserByName',
    method: 'post',
    data: username,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function userInfoById(id) {
  return request({
    url: '/user/selectUserById',
    method: 'post',
    data: id,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function getUserList(params) {
  return request({
    url: '/user/userList',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function idTypeList() {
  return request({
    url: '/user/selectIdTypeList',
    method: 'get'
  })
}

export function deleteUserById(id) {
  return request({
    url: '/user/deleteUserById',
    method: 'post',
    data: id,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function updateUserById(params) {
  return request({
    url: '/user/updateUserById',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function createUser(params) {
  return request({
    url: '/user/createUser',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
