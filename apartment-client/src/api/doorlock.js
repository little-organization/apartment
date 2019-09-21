import request from '@/utils/request'

export function selectDoorLockList(params) {
  return request({
    url: '/doorlock/selectDoorLockList',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function selectDoorLockDetails(params) {
  return request({
    url: '/doorlock/selectDoorLockDetails',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function selectDoorLockPwdInfo(params) {
  return request({
    url: '/doorlock/selectDoorLockPwdInfo',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function changePassword(params) {
  return request({
    url: '/doorlock/changePassword',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

