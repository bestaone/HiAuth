import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface AppPageDto extends PageDto {
  name?: string;
}

export interface AppLimitDto extends LimitDto {
  name?: string;
}

export interface AppCreateDto {
  name?: string;
  icon?: string;
  remark?: string;
}

export interface AppUpdateDto {
  id: string;
  name?: string;
  icon?: string;
  remark?: string;
}

export interface AppVo {
  id: string;
  name: string;
  icon: string;
  createTime: string;
  remark: string;
}

export const pageAppApi = async (params: AppPageDto) => {
  return requestClient.post<PageVo<AppVo>>(
    '/api/adminSpace/appMgr/page',
    params,
  );
};

export const getAppByIdApi = async (id: string) => {
  return requestClient.post<AppVo>(`/api/adminSpace/appMgr/findById?id=${id}`);
};

// 新增用户
export const createAppApi = async (params: AppCreateDto) => {
  return requestClient.post<AppVo>('/api/adminSpace/appMgr/create', params);
};

export const updateAppApi = async (params: AppUpdateDto) => {
  return requestClient.post<AppVo>('/api/adminSpace/appMgr/update', params);
};

export const deleteAppsApi = async (params: { ids: string[] }) => {
  return requestClient.post('/api/adminSpace/appMgr/delete', params);
};

export const limitAppApi = async (params: AppLimitDto) => {
  return requestClient.post<AppVo[]>(
    '/api/adminSpace/appMgr/limitList',
    params,
  );
};
