import { request } from 'umi';
import {Department} from "./data";

export async function findDepTree(
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Department[]>>('/api/department/findDepTree', {
    method: 'POST',
    ...(options || {}),
  });
}

export async function findDepAll(
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Department[]>>('/api/department/limitList', {
    method: 'POST',
    data: { offset: 0, limit: 1000 },
    ...(options || {}),
  });
}

export async function addDep(
  body: {
    pid?: number;
    name: string;
    sort?: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Department>>('/api/department/create', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function modifyDep(
  body: {
    id?: number;
    pid?: number;
    name: string;
    sort?: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<any>>('/api/department/update', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function deleteDepById(
  params: {
    id: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<any>>('/api/department/delete', {
    method: 'POST',
    params,
    ...(options || {}),
  });
}
