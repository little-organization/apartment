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

export function delImage(fileName) {
  return request({
    url: '/file/delImage/' + fileName,
    method: 'get'
  })
}

export function getImages(id) {
  return request({
    url: '/file/getImages/' + id,
    method: 'get'
  })
}

export function getHostAddress() {
  return request({
    url: '/file/getHostAddress',
    method: 'get'
  })
}

export function image(image) {
  return request({
    url: '/file/image',
    data: image,
    method: 'post'
  })
}

export function addRepairAndFile(repair) {
  return request({
    url: '/repairs/insertRepairAndFile',
    method: 'post',
    data: repair,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

