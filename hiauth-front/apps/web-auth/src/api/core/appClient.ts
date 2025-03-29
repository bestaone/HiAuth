import type { AppVo } from '#/api/core/app';
import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface AppClientPageDto extends PageDto {
  cid?: string;
  keyword?: string;
}

export interface AppClientLimitDto extends LimitDto {
  cid?: string;
  name?: string;
}

export interface AppClientCreateDto {
  appIds: string[];
  corpId?: string;
}

export interface AppClientUpdateDto {
  id: string;
  clientId?: string;
  clientSecret?: string;
  clientName?: string;
  redirectUris?: string;
  scopes?: string;
  accessTokenTimeToLive?: number;
}

export interface AppClientDeleteDto {
  appIds: string[];
  corpId: string;
}

export interface AppClientVo {
  id: string;
  cid: string;
  appId: string;
  name: string;
  icon: string;
  createTime: string;
  remark: string;
  clientId: string;
  clientSecret: string;
  clientName: string;
  redirectUris: string[];
  scopes: string;
  accessTokenTimeToLive: number;
}

export const pageAppClientApi = (params: AppClientPageDto) => {
  return requestClient.post<PageVo<AppClientVo>>(
    `/api/corpSpace/appClientMgr/page`,
    params,
  );
};

export const getAppClientByIdApi = async (id: string) => {
  return requestClient.post<AppClientVo>(
    `/api/corpSpace/appClientMgr/findById?id=${id}`,
  );
};

// 新增用户
export const addAppClientsApi = async (params: AppClientCreateDto) => {
  return requestClient.post<AppClientVo>(
    `/api/corpSpace/appClientMgr/add`,
    params,
  );
};

export const createAppClientApi = async (params: AppClientCreateDto) => {
  return requestClient.post<AppClientVo>(
    `/api/corpSpace/appClientMgr/create`,
    params,
  );
};

export const updateAppClientApi = async (params: AppClientUpdateDto) => {
  return requestClient.post<AppClientVo>(
    `/api/corpSpace/appClientMgr/update`,
    params,
  );
};

export const deleteAppClientsApi = async (params: { ids: string[] }) => {
  return requestClient.post(`/api/corpSpace/appClientMgr/delete`, params);
};

export const limitHaveAppApi = async (params: AppClientLimitDto) => {
  return requestClient.post<AppVo[]>(
    `/api/corpSpace/appClientMgr/limitHaveApp`,
    params,
  );
};

export const limitNotHaveAppApi = async (params: AppClientLimitDto) => {
  return requestClient.post<AppVo[]>(
    `/api/corpSpace/appClientMgr/limitNotHaveApp`,
    params,
  );
};
