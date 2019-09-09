import request from '@/utils/request'

export function querySmsTemplate(params) {
  return request({
    url: '/message/querySmsTemplate',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function querySmsSign(params) {
  return request({
    url: '/message/querySmsSign',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function querySmsSendMsgDetail(params) {
  return request({
    url: '/message/queryMsgDetails',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function selectTemplateCodeAndNameList() {
  return request({
    url: '/message/selectTemplateCodeAndNameList',
    method: 'get'
  })
}

export function delSmsSign(signName) {
  return request({
    url: '/message/deleteSmsSign',
    method: 'post',
    data: signName,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function insertSmsSign(signName) {
  return request({
    url: '/message/insertSmsSignFromAli',
    method: 'post',
    data: signName,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function insertSmsTemplate(templateCode) {
  return request({
    url: '/message/insertTemplateSignFromAli',
    method: 'post',
    data: templateCode,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function delSmsTemplate(templateCode) {
  return request({
    url: '/message/deleteSmsTemplate',
    method: 'post',
    data: templateCode,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function updateSmsTemplate(params) {
  return request({
    url: '/message/updateSmsTemplateFromAli',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function sendMsg(params) {
  return request({
    url: '/message/sendMsg',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

// 发送验证码
export function getCode(params) {
  return request({
    url: '/message/getCode',
    method: 'post',
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

