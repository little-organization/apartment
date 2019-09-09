import request from '@/utils/request'

export function repairInfoList(repairname) {
  return request({
    url: '/repair/selectRepairByName',
    method: 'post',
    data: repairname,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function repairInfoById(id) {
  return request({
    url: '/repair/selectRepairById',
    method: 'post',
    data: id,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function getRepairList(params) {
  return request({
    url: '/repair/repairList',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function idTypeList() {
  return request({
    url: '/repair/selectIdTypeList',
    method: 'get'
  })
}

export function deleteRepairById(params) {
  return request({
    url: '/repair/deleteRepairById',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function updateRepairById(params) {
  return request({
    url: '/repair/updateRepairById',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function createRepair(params) {
  return request({
    url: '/repair/createRepair',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
