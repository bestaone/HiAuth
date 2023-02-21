import request from '@/utils/request'
import { getToken } from '@/utils/auth'

export function queryUser(params) {
  return request({
    url: 'api/user',
    method: 'get',
    params: {
      pageNum: params.pageNum,
      pageSize: params.pageSize,
      access_token: getToken()
    }
  })
}

export function getUserInfo(id) {
  return request({
    url: 'api/user/' + id,
    method: 'get',
    params: {
      access_token: getToken()
    }
  })
}

export function removeUser(id) {
  return request({
    url: 'api/user/' + id,
    method: 'delete',
    params: {
      access_token: getToken()
    }
  })
}

export function savaUser(id, user) {
  if (id == null) {
    return createUser(user)
  } else {
    return updateUser(id, user)
  }
}

export function createUser(user) {
  return request({
    url: 'api/user',
    method: 'post',
    params: {
      access_token: getToken()
    },
    data: user
  })
}

export function updateUser(id, user) {
  return request({
    url: 'api/user/' + id,
    method: 'put',
    params: {
      access_token: getToken()
    },
    data: user
  })
}
