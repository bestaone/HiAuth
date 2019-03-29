import request from '@/utils/request'
import { getToken } from '@/utils/auth'

export function queryApp(params) {
  return request({
    url: 'api/app',
    method: 'get',
    params: {
      pageNum: params.pageNum,
      pageSize: params.pageSize,
      access_token: getToken()
    }
  })
}

export function getAppInfo(id) {
  return request({
    url: 'api/app/' + id,
    method: 'get',
    params: {
      access_token: getToken()
    }
  })
}

export function removeApp(id) {
  return request({
    url: 'api/app/' + id,
    method: 'delete',
    params: {
      access_token: getToken()
    }
  })
}

export function savaApp(id, app) {
  if (id == null) {
    return createApp(app)
  } else {
    return updateApp(id, app)
  }
}

export function createApp(app) {
  return request({
    url: 'api/app',
    method: 'post',
    params: {
      access_token: getToken()
    },
    data: app
  })
}

export function updateApp(id, app) {
  return request({
    url: 'api/app/' + id,
    method: 'put',
    params: {
      access_token: getToken()
    },
    data: app
  })
}
