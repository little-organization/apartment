import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url: '/repairs/repairsList',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function statusList() {
  return request({
    url: '/repairs/selectStatusList',
    method: 'get'
  })
}

export function updateRepair(params) {
  return request({
    url: '/repairs/updateRepair',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function deleteApartment(id) {
  return request({
    url: '/apartment/deleteApartment',
    method: 'post',
    data: id,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function apartmentListByUserid(id) {
  return request({
    url: '/apartment/getApartmentListByUserid',
    method: 'post',
    data: id,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

