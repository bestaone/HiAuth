import type { AppVo } from '#/api/core/app';
import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface CorpAppPageDto extends PageDto {
  cid?: string;
  keyword?: string;
}

export interface CorpAppLimitDto extends LimitDto {
  cid?: string;
  name?: string;
}

export interface CorpAppCreateDto {
  appIds: string[];
  corpId?: string;
}

export interface CorpAppUpdateDto {
  id: string;
  clientId?: string;
  clientName?: string;
  redirectUris?: string;
  scopes?: string;
  accessTokenTimeToLive?: number;
}

export interface CorpAppDeleteDto {
  appIds: string[];
  corpId: string;
}

export interface CorpAppVo {
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

export const pageCorpAppApi = (params: CorpAppPageDto) => {
  return requestClient.post<PageVo<CorpAppVo>>(
    `/api/corpSpace/corpAppMgr/page`,
    params,
  );
};

export const getCorpAppByIdApi = async (id: string) => {
  return requestClient.post<CorpAppVo>(
    `/api/corpSpace/corpAppMgr/findById?id=${id}`,
  );
};

// 新增用户
export const addCorpAppsApi = async (params: CorpAppCreateDto) => {
  return requestClient.post<CorpAppVo>(`/api/corpSpace/corpAppMgr/add`, params);
};

export const createCorpAppApi = async (params: CorpAppCreateDto) => {
  return requestClient.post<CorpAppVo>(
    `/api/corpSpace/corpAppMgr/create`,
    params,
  );
};

export const updateCorpAppApi = async (params: CorpAppUpdateDto) => {
  return requestClient.post<CorpAppVo>(
    `/api/corpSpace/corpAppMgr/update`,
    params,
  );
};

export const deleteCorpAppsApi = async (params: { ids: string[] }) => {
  return requestClient.post(`/api/corpSpace/corpAppMgr/delete`, params);
};

export const limitHaveAppApi = async (params: CorpAppLimitDto) => {
  return requestClient.post<AppVo[]>(
    `/api/corpSpace/corpAppMgr/limitHaveApp`,
    params,
  );
};

export const limitNotHaveAppApi = async (params: CorpAppLimitDto) => {
  return requestClient.post<AppVo[]>(
    `/api/corpSpace/corpAppMgr/limitNotHaveApp`,
    params,
  );
};
