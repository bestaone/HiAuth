import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface CorpPageDto extends PageDto {
  keyword?: string;
}

export interface CorpLimitDto extends LimitDto {
  keyword?: string;
}

export interface CorpCreateDto {
  name?: string;
  status?: number;
}

export interface CorpUpdateDto {
  id: string;
  name?: string;
  status?: number;
}

export interface CorpVo {
  id: string;
  name: string;
  appCount: number;
  depCount: number;
  empCount: number;
}

export const listCorpApi = () => {
  return requestClient.post<CorpVo[]>('/api/adminSpace/corpMgr/listCorp');
};

export const pageCorpApi = (params: CorpPageDto) => {
  return requestClient.post<PageVo<CorpVo>>(
    '/api/adminSpace/corpMgr/page',
    params,
  );
};

export const limitCorpApi = (params: CorpLimitDto) => {
  return requestClient.post<CorpVo[]>('/api/adminSpace/corpMgr/limit', params);
};

export const getCorpByIdApi = async (id: string) => {
  return requestClient.post<CorpVo>(
    `/api/adminSpace/corpMgr/findById?id=${id}`,
  );
};

export const createCorpApi = (params: CorpCreateDto) => {
  return requestClient.post<CorpVo>('/api/adminSpace/corpMgr/create', params);
};

export const updateCorpApi = (params: CorpUpdateDto) => {
  return requestClient.post<CorpVo>('/api/adminSpace/corpMgr/update', params);
};

export const deleteCorpsApi = (params: { ids: string[] }) => {
  return requestClient.post('/api/adminSpace/corpMgr/delete', params);
};

export const intoCorpSpaceApi = (cid: string) => {
  return requestClient.post<string>(
    `/api/adminSpace/corpMgr/intoCorpSpace?cid=${cid}`,
  );
};

export const intoAdminSpaceApi = () => {
  return requestClient.post<string>('/api/adminSpace/corpMgr/intoAdminSpace');
};
