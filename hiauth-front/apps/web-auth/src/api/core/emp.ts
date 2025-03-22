import type { UserLimitDto, UserVo } from '#/api';
import type { PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface EmpPageDto extends PageDto {
  cid?: string;
  depIds?: string[];
  keyword?: string;
  name?: string;
}

export interface EmpCreateDto {
  cid?: string;
  userId?: string;
  no?: string;
  name?: string;
  email?: string;
  isCorpAdmin?: boolean;
  depIds?: string[];
  roleIds?: string[];
}

export interface EmpUpdateDto {
  id: string;
  userId?: string;
  no?: string;
  name?: string;
  email?: string;
  isCorpAdmin?: boolean;
  depIds?: string[];
  roleIds?: string[];
}

export interface EmpVo {
  id: string;
  cid: string;
  userId: string;
  no: string;
  name: string;
  email: string;
  createTime: string;
  isCorpAdmin: boolean;
  depIds: string[];
}

export const pageEmpApi = (params: EmpPageDto) => {
  return requestClient.post<PageVo<EmpVo>>(
    '/api/corpSpace/empMgr/page',
    params,
  );
};

export const getEmpByIdApi = async (id: string) => {
  return requestClient.post<EmpVo>(`/api/corpSpace/empMgr/findById?id=${id}`);
};

// 新增用户
export const createEmpApi = (params: EmpCreateDto) => {
  return requestClient.post<EmpVo>('/api/corpSpace/empMgr/create', params);
};

export const updateEmpApi = (params: EmpUpdateDto) => {
  return requestClient.post<EmpVo>('/api/corpSpace/empMgr/update', params);
};

export const deleteEmpsApi = (params: { ids: string[] }) => {
  return requestClient.post('/api/corpSpace/empMgr/delete', params);
};

export const findUserApi = (params: UserLimitDto) => {
  return requestClient.post<UserVo[]>('/api/corpSpace/empMgr/findUser', params);
};
