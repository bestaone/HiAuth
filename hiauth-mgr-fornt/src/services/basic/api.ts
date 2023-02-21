// @ts-ignore
/* eslint-disable */
import {request} from 'umi';

/** 登录接口 POST /api/login/account */
export async function login(body: API.BasicLoginParams, options?: { [key: string]: any }) {
  return request<API.BasicApiResponse<API.BasicLoginResult>>('/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

export async function outLogin(options?: { [key: string]: any }) {
  return request<API.BasicApiResponse<any>>('/api/logout', {
    method: 'GET',
    ...(options || {}),
  });
}

/** 获取当前的用户 GET /api/currentUser */
export async function currentUser(options?: { [key: string]: any }) {
  return request<API.BasicApiResponse<API.CurrentUserInfo>>('/api/user/getCurrentUserInfo', {
    method: 'GET',
    ...(options || {}),
    // headers: {
    //   'Authorization':`Bearer ${localStorage.getItem('accessToken')}`
    // },
  });
}

export async function getNotices(options?: { [key: string]: any }) {
  return request<API.BasicApiResponse<API.Page<API.NoticeIconItem>>>('/api/notice/page', {
    data: { pageNum: 1, pageSize: 100 },
    method: 'POST',
    ...(options || {}),
  });
}

export async function getCurrentResources(
  groupKey: string,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Set<string>>>('/api/user/findCurrentResources', {
    method: 'POST',
    params: {groupKey},
    ...(options || {}),
  });
}

export async function getCurrentPermissions(
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Set<string>>>('/api/user/findCurrentPermissions', {
    method: 'POST',
    ...(options || {}),
  });
}
