import {request} from 'umi';
import type {AppListItem} from "./data";

/** 此处后端没有提供注释 GET /api/notices */
export async function pageApp(
  body: {
    pageNum?: number;
    pageSize?: number;
    clientId?: string;
    clientName?: string;
  },
  options?: Record<string, any>
) {
  // current 无用参数，传到后台会被框架当做查询参数，报错，这里移除
  Reflect.deleteProperty(body, 'current');
  return request<API.BasicApiResponse<API.Page<AppListItem>>>('/api/registeredClient/page', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function getAppById(
  id: number,
  options?: Record<string, any>
) {
  return request<AppListItem>('/api/registeredClient/findById', {
    method: 'POST',
    params: { 'id': id },
    ...(options || {}),
  });
}

export async function createApp(
  body: {
    clientName?: string;
    clientAuthenticationMethods?: string;
    redirectUris?: string;
    scopes?: string;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<AppListItem>>('/api/registeredClient/add', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function updateApp(
  body: {
    id: string;
    clientName?: string;
    clientAuthenticationMethods?: string;
    redirectUris?: string;
    scopes?: string;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<AppListItem>>('/api/registeredClient/update', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function deleteApp(
  id: string,
  options?: Record<string, any>
) {
  return request('/api/registeredClient/delete', {
    method: 'POST',
    params: { 'id': id },
    ...(options || {}),
  });
}
