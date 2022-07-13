import request from '@/utils/request'

export function getBrokerList() {
  return request({
    url: '/rbroker/queryList',
    method: 'get'
  })
}
