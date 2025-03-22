import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface RolePageDto extends PageDto {
  cid?: string;
  name?: string;
}

export interface RoleLimitDto extends LimitDto {
  keyword?: string;
}

export interface RoleCreateDto {
  cid?: string;
  name?: string;
  remark?: string;
  isEnable?: boolean;
}

export interface RoleUpdateDto {
  id: string;
  name?: string;
  remark?: string;
  isEnable?: boolean;
}

export interface RoleAuthDto {
  appId: string;
  roleId: string | undefined;
  appResourceIds: string[];
}

export interface RoleVo {
  id: string;
  cid: string;
  name: string;
  createTime: string;
  remark: string;
  isEnable: boolean;
}

export const pageRoleApi = (params: RolePageDto) => {
  return requestClient.post<PageVo<RoleVo>>(
    '/api/corpSpace/roleMgr/page',
    params,
  );
};

export const limitRoleApi = (params: RoleLimitDto) => {
  return requestClient.post<RoleVo[]>('/api/corpSpace/roleMgr/limit', params);
};

export const getRoleByIdApi = async (id: string) => {
  return requestClient.post<RoleVo>(`/api/corpSpace/roleMgr/findById?id=${id}`);
};

// 新增用户
export const createRoleApi = (params: RoleCreateDto) => {
  return requestClient.post<RoleVo>('/api/corpSpace/roleMgr/create', params);
};

export const updateRoleApi = (params: RoleUpdateDto) => {
  return requestClient.post<RoleVo>('/api/corpSpace/roleMgr/update', params);
};

export const deleteRolesApi = (params: { ids: string[] }) => {
  return requestClient.post('/api/corpSpace/roleMgr/delete', params);
};

export const authRoleApi = (params: RoleAuthDto) => {
  return requestClient.post<boolean>('/api/corpSpace/roleMgr/auth', params);
};
