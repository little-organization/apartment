import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url: '/apartment/apartmentList',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function patternList() {
  return request({
    url: '/apartment/selectPattern',
    method: 'get'
  })
}

export function faceList() {
  return request({
    url: '/apartment/selectFaceList',
    method: 'get'
  })
}

export function houseTypeList() {
  return request({
    url: '/apartment/selectHouseType',
    method: 'get'
  })
}

export function createApartment(params) {
  return request({
    url: '/apartment/createApartment',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function updateApartment(params) {
  return request({
    url: '/apartment/updateApartment',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function deleteApartment(params) {
  return request({
    url: '/apartment/deleteApartment',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function apartmentListByUserid(id) {
  return request({
    url: '/apartment/getApartmentListByUserid/' + id,
    method: 'get'
  })
}

export function apartmentById(id) {
  return request({
    url: '/apartment/getApartmentById/' + id,
    method: 'get'
  })
}

