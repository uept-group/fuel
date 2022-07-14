import request from '@/utils/request'

export function getNameServList() {
  return request({
    url: '/namesrv/all',
    method: 'get'
  })
}

export function addNameServ(data) {
  return request({
    url: '/namesrv/insert',
    method: 'post',
    data
  })
}
export function modNameServ(data) {
  return request({
    url: '/namesrv/update',
    method: 'post',
    data
  })
}
export function delNameServ(data) {
  return request({
    url: '/namesrv/delete',
    method: 'post',
    data
  })
}
