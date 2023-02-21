import {request} from 'umi';
import type {Employ} from "./data";
import {EmployEdit} from "./data";

/** 此处后端没有提供注释 GET /api/notices */
export async function pageEmp(
  body: {
    name?: string;
    email?: string;
    status?: number;
    depId?: number;
    pageNum: number;
    pageSize: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<API.Page<Employ>>>('/api/employ/pageByParam', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function getDetailById(
  id: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Employ>>('/api/employ/findDetailById', {
    method: 'POST',
    params: {id},
    ...(options || {}),
  });
}

export async function addEmpWithDep(
  body: {
    name: string;
    email?: string;
    depIds?: number[];
    primaryDepId?: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Employ>>('/api/employ/createWithDep', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function modifyEmpWithDep(
  body: {
    id?: number;
    name: string;
    email?: string;
    depIds?: number[];
    primaryDepId?: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<any>>('/api/employ/updateWithDep', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function deleteEmpById(
  params: {
    id: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<any>>('/api/employ/delete', {
    method: 'POST',
    params,
    ...(options || {}),
  });
}


export async function modifyEmp(
  emp: EmployEdit,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<undefined>>('/api/employ/update', {
    method: 'POST',
    data: emp,
    ...(options || {}),
  });
}

export async function empUnbindUser(
  empId: number,
  userId: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<undefined>>('/api/employ/unbindUser', {
    method: 'POST',
    params: {empId, userId},
    ...(options || {}),
  });
}
