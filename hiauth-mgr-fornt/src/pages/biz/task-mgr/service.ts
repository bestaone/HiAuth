import {request} from 'umi';
import {Task} from "@/pages/biz/task-mgr/data";

export async function pageTask(
  body: {
    pageNum: number;
    pageSize: number;
    name?: string;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<API.Page<Task>>>('/api/task/page_x', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function createTask(
  body: {
    title: string;
    status: string;
  },
  options?: Record<string, any>
) {
  return request('/api/task/create', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function updateTask(
  body: {
    id: number;
    title?: string;
    status?: string;
  },
  options?: Record<string, any>
) {
  return request('/api/task/update', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

export async function deleteTask(
  id: number,
  options?: Record<string, any>
) {
  return request('/api/task/delete', {
    method: 'POST',
    params: { 'id': id },
    ...(options || {}),
  });
}
