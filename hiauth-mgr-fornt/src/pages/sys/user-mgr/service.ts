import {request} from 'umi';
import type {User, UserListItem} from "./data";

/** 此处后端没有提供注释 GET /api/notices */
export async function pageUser(
  body: {
    pageNum?: number;
    pageSize?: number;
    username?: string;
    phoneNum?: string;
  },
  options?: Record<string, any>
) {
  // current 无用参数，传到后台会被框架当做查询参数，报错，这里移除
  Reflect.deleteProperty(body, 'current');
  return request<API.BasicApiResponse<API.Page<UserListItem>>>('/api/user/page', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function getUserById(
  id: number,
  options?: Record<string, any>
) {
  return request<UserListItem>('/api/user/findById', {
    method: 'POST',
    params: { 'id': id },
    ...(options || {}),
  });
}

export async function createUser(
  body: {
    username?: string;
    phoneNum?: string;
    password?: string
  },
  options?: Record<string, any>
) {
  return request('/api/user/create', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function updateUser(
  body: {
    id: number;
    username?: string;
    phoneNum?: string;
    password?: string
  },
  options?: Record<string, any>
) {
  return request('/api/user/update', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function deleteUser(
  id: number,
  options?: Record<string, any>
) {
  return request('/api/user/delete', {
    method: 'POST',
    params: { 'id': id },
    ...(options || {}),
  });
}

export async function limitUnbindUser(
  username?: string,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<User[]>>('/api/user/limitUnbind', {
    method: 'POST',
    params: { username },
    ...(options || {}),
  });
}
