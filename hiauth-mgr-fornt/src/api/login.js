import request from '@/utils/request'

export function token(username, password) {
  const data = {
    username: username,
    password
  }
  return request({
    url: 'oauth/token?username=' + username + '&password=' + password + '&grant_type=password&client_id=umc_client_id&client_secret=123456',
    method: 'post',
    data
  })
}

export function getUserProfile(token) {
  return request({
    url: 'api/user/profile',
    method: 'get',
    params: { access_token: token }
  })
}

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/login/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

