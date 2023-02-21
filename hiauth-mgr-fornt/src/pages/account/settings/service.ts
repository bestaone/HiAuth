import { request } from 'umi';
import type { CurrentUser, GeographicItemType } from './data';
import {EmployEdit} from "@/pages/org/emp-mgr/data";

// export async function queryCurrent(): Promise<{ data: CurrentUser }> {
//   return request('/api/accountSettingCurrentUser');
// }

export async function queryCurrent(
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<CurrentUser>>('/api/employ/currentEmpDetail', {
    method: 'POST',
    ...(options || {}),
  });
}

// export async function queryProvince(): Promise<{ data: GeographicItemType[] }> {
//   return request('/api/geographic/province');
// }

export async function queryProvince(): Promise<{ data: GeographicItemType[] }> {
  return request('https://proapi.azurewebsites.net/api/geographic/province');
}

export async function queryCity(province: string): Promise<{ data: GeographicItemType[] }> {
  return request(`https://proapi.azurewebsites.net/api/geographic/city/${province}`);
}

export async function query() {
  return request('/api/users');
}

export async function modifyCurrentEmp(
  employ: EmployEdit,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<undefined>>('/api/employ/modifyCurrentEmp', {
    method: 'POST',
    data: employ,
    ...(options || {}),
  });
}
