import request from '@/utils/request'

export function getBrokerList() {
  return request({
    url: '/rbroker/queryList',
    method: 'get'
  })
}

export function getBrokerConfig(params) {
  return request({
    url: `/rbroker/findConfig?nid=${params.namesrvId}&addr=${params.address}`,
    method: 'get'
  })
}

export function getBrokerState(params) {
  return request({
    url: `/rbroker/findStats?nid=${params.namesrvId}&addr=${params.address}`,
    method: 'get'
  })
}
