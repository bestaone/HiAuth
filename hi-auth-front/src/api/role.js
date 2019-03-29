import request from '@/utils/request'
import { getToken } from '@/utils/auth'

export function queryRole(pageNum, pageSize) {
  const accessToken = getToken()
  return request({
    url: 'api/role',
    method: 'get',
    params: {
      access_token: accessToken,
      pageNum,
      pageSize
    }
  })
}

export function getRoleByUserId(userId) {
  const accessToken = getToken()
  return request({
    url: 'api/role/get_role_by_user_id/' + userId,
    method: 'get',
    params: {
      access_token: accessToken
    }
  })
}

export function removeRoleResource(roleId, resourcesIds) {
  return request({
    url: 'api/role/remove_resources_by_role_id/' + roleId,
    method: 'delete',
    params: {
      access_token: getToken()
    },
    data: { resourcesIds: resourcesIds }
  })
}

export function addRoleResource(roleId, resourcesIds) {
  return request({
    url: 'api/role/add_resources_by_role_id/' + roleId,
    method: 'post',
    params: {
      access_token: getToken()
    },
    data: { resourcesIds: resourcesIds }
  })
}
