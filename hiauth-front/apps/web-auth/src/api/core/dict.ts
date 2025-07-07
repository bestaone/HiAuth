import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface DictPageDto extends PageDto {
  keyword: string | undefined;
  code?: string;
  name?: string;
  isRoot: boolean;
}

export interface DictLimitDto extends LimitDto {
  keyword?: string;
  pcode?: string;
}

export interface DictCreateDto {
  sort?: number;
  code?: string;
  pcode?: string;
  name?: string;
  value?: string;
}

export interface DictUpdateDto {
  id: string;
  sort?: number;
  code?: string;
  pcode?: string;
  name?: string;
  value?: string;
  isEnable?: boolean;
}

export interface DictVo {
  id: string;
  sort: number;
  code: string;
  pCode: string;
  name: string;
  value: string;
  isEnable: boolean;
  createTime: string;
  hasChild: boolean;
}

export async function pageDictApi(params: DictPageDto) {
  return requestClient.post<PageVo<DictVo>>(
    '/api/corpSpace/dictMgr/page',
    params,
  );
}

export async function getDictByIdApi(id: string) {
  return requestClient.post<DictVo>(`/api/corpSpace/dictMgr/findById?id=${id}`);
}

// 新增用户
export async function createDictApi(params: DictCreateDto) {
  return requestClient.post<DictVo>('/api/corpSpace/dictMgr/create', params);
}

export async function updateDictApi(params: DictUpdateDto) {
  return requestClient.post<DictVo>('/api/corpSpace/dictMgr/update', params);
}

export async function deleteDictApi(params: { ids: string[] }) {
  return requestClient.post('/api/corpSpace/dictMgr/delete', params);
}

export async function findDictApi(params: DictLimitDto) {
  return requestClient.post<DictVo[]>(
    '/api/corpSpace/dictMgr/findDict',
    params,
  );
}

export async function findSubDictApi(params: DictLimitDto) {
  return requestClient.post<DictVo[]>(
    '/api/corpSpace/dictMgr/findSubDict',
    params,
  );
}
