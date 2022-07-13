import request from '@/utils/request'

export function getNameServList() {
  return request({
    url: '/namesrv/all',
    method: 'get'
  })
}
