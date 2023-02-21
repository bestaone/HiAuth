import {request} from 'umi';
import type {CurrentUser, ListItemDataType} from './data.d';

// export async function queryCurrent(): Promise<{ data: CurrentUser }> {
//   return request('/api/currentUserDetail');
// }

export async function queryCurrent(
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<CurrentUser>>('/api/employ/currentEmpDetail', {
    method: 'POST',
    ...(options || {}),
  });
}

export async function queryFakeList(params: {
  count: number;
}): Promise<{ data: { list: ListItemDataType[] } }> {
  return request('/api/fake_list_Detail', {
    params,
  });
}
