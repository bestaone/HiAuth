import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface DepPageDto extends PageDto {
  cid?: string;
  keyword?: string;
  no?: string;
  name?: string;
  status?: number;
}

export interface DepLimitDto extends LimitDto {
  cid?: string;
  keyword?: string;
}

export interface DepCreateDto {
  cid?: string;
  sort?: number;
  no?: string;
  name?: string;
  status?: number;
  remark?: string;
}

export interface DepUpdateDto {
  id: string;
  sort?: number;
  no?: string;
  name?: string;
  status?: number;
  remark?: string;
}

export interface DepVo {
  id: string;
  cid: string;
  sort: number;
  no: string;
  name: string;
  createTime: string;
  status: number;
  remark: string;
}

export const pageDepApi = (params: DepPageDto) => {
  return requestClient.post<PageVo<DepVo>>(
    '/api/corpSpace/depMgr/page',
    params,
  );
};

export const limitDepApi = (params: DepLimitDto) => {
  return requestClient.post<DepVo[]>('/api/corpSpace/depMgr/limit', params);
};

export const getDepByIdApi = async (id: string) => {
  return requestClient.post<DepVo>(`/api/corpSpace/depMgr/findById?id=${id}`);
};

export const createDepApi = (params: DepCreateDto) => {
  return requestClient.post<DepVo>('/api/corpSpace/depMgr/create', params);
};

export const updateDepApi = (params: DepUpdateDto) => {
  return requestClient.post<DepVo>('/api/corpSpace/depMgr/update', params);
};

export const deleteDepsApi = (params: { ids: string[] }) => {
  return requestClient.post('/api/corpSpace/depMgr/delete', params);
};

export const depTreeApi = () => {
  return requestClient.post('/api/corpSpace/depMgr/depTree', {});
};
