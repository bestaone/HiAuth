import type { PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface AppResourcePageDto extends PageDto {
  appId: string;
  pid?: string;
  keyword?: string;
  code?: string;
  url?: string;
  api?: string;
  name?: string;
  type?: number;
}

export interface AppResourceCreateDto {
  appId: string;
  pid?: string;
  code?: string;
  url?: string;
  api?: string;
  name?: string;
  type?: number;
  remark?: string;
  extend?: string;
}

export interface AppResourceUpdateDto {
  id: string;
  pid?: string;
  code?: string;
  url?: string;
  api?: string;
  name?: string;
  type?: number;
  remark?: string;
  extend?: string;
}

export interface FindAppResourceIdsByRoleAndAppDto {
  appId: string;
  roleId: string;
}

export interface AppResourceVo {
  id: string;
  appId: string;
  pid: string;
  code: string;
  url: string;
  api: string;
  name: string;
  type: number;
  remark: string;
  extend: string;
}

export const AppResourceType = { 0: 'xx', 1: '菜单', 2: '页面', 3: '功能' };
export const AppResourceTypeOpt = [
  { label: 'xx', value: 0 },
  { label: '菜单', value: 1 },
  { label: '页面', value: 2 },
  { label: '功能', value: 3 },
];

export const pageAppResourceApi = (params: AppResourcePageDto) => {
  return requestClient.post<PageVo<AppResourceVo>>(
    `/api/common/appResourceMgr/page`,
    params,
  );
};

export const getAppResourceByIdApi = async (id: string) => {
  return requestClient.post<AppResourceVo>(
    `/api/common/appResourceMgr/findById?id=${id}`,
  );
};

export const createAppResourceApi = (params: AppResourceCreateDto) => {
  return requestClient.post<AppResourceVo>(
    `/api/common/appResourceMgr/create`,
    params,
  );
};

export const updateAppResourceApi = (params: AppResourceUpdateDto) => {
  return requestClient.post<AppResourceVo>(
    `/api/common/appResourceMgr/update`,
    params,
  );
};

export const deleteAppResourcesApi = (params: { ids: string[] }) => {
  return requestClient.post(`/api/common/appResourceMgr/delete`, params);
};

export const appResourceTreeApi = (appId: string) => {
  return requestClient.post(
    `/api/common/appResourceMgr/tree?appId=${appId}`,
    {},
  );
};

export const findAppResourceIdsByRoleAndAppApi = (
  params: FindAppResourceIdsByRoleAndAppDto,
) => {
  return requestClient.post<string[]>(
    `/api/common/appResourceMgr/findAppResourceIdsByRoleAndApp`,
    params,
  );
};
