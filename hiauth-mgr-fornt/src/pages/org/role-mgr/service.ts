import { request } from 'umi';
import {Role} from "@/pages/org/role-mgr/data";

export async function pageRole(
  body: {
    pageNum: number;
    pageSize: number;
    name?: string;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<API.Page<Role>>>('/api/role/page', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function limitRole(
  name?: string,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Role[]>>('/api/role/limitList', {
    method: 'POST',
    data: {name},
    ...(options || {}),
  });
}

export async function getRoleByEmp(
  empId: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Role[]>>('/api/role/findByEmp', {
    method: 'POST',
    params: { empId },
    ...(options || {}),
  });
}

export async function getRoleById(
  id: number,
  options?: Record<string, any>
) {
  return request<Role>('/api/role/findById', {
    method: 'POST',
    params: { 'id': id },
    ...(options || {}),
  });
}

export async function createRole(
  body: {
    name: string;
    code: string;
    remark?: string;
  },
  options?: Record<string, any>
) {
  return request('/api/role/create', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function updateRole(
  body: {
    id: number;
    name?: string;
    code?: string;
    remark?: string;
  },
  options?: Record<string, any>
) {
  return request('/api/role/update', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function deleteRole(
  id: number,
  options?: Record<string, any>
) {
  return request('/api/role/delete', {
    method: 'POST',
    params: { 'id': id },
    ...(options || {}),
  });
}

export async function deleteEmpRole(
  empId: number,
  roleId: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<undefined>>('/api/role/deleteEmpRole', {
    method: 'POST',
    params: { empId, roleId },
    ...(options || {}),
  });
}

export async function addEmpRole(
  empId: number,
  roleId: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<undefined>>('/api/role/addEmpRole', {
    method: 'POST',
    params: { empId, roleId },
    ...(options || {}),
  });
}
