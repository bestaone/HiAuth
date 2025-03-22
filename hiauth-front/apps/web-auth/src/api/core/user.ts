import type { UserInfo } from '@vben/types';

import type { LimitDto, PageDto, PageVo } from '#/api/core/common';

import { requestClient } from '#/api/request';

export interface UserPageDto extends PageDto {
  keyword: string;
  name: string;
  username: string;
  phoneNum: string;
  gender: number;
  status: number;
  regtime: string[];
  isSysAdmin: boolean;
}

export interface UserLimitDto extends LimitDto {
  keyword?: string;
}

export interface UserCreateDto {
  name?: string;
  avatar?: string;
  username?: string;
  phoneNum?: string;
  gender?: number;
  status?: number;
  isSysAdmin?: boolean;
}

export interface UserUpdateDto {
  id: string;
  name?: string;
  avatar?: string;
  username?: string;
  phoneNum?: string;
  gender?: number;
  status?: number;
  isSysAdmin?: boolean;
}

export interface UserVo {
  id: string;
  name: string;
  avatar: string;
  username: string;
  phoneNum: string;
  gender: number;
  status: number;
  regtime: string;
  lastLoginTime: string;
  isSysAdmin: boolean;
}

/**
 * @description：用户性别
 */
export const GenderType = { 0: '未知', 1: '男', 2: '女' };
export const GenderTypeOpt = [
  { label: '未知', value: 0 },
  { label: '男', value: 1 },
  { label: '女', value: 2 },
];

/**
 * 获取用户信息
 */
export async function getUserInfoApi() {
  return requestClient.post<UserInfo>('/api/common/userInfo');
}

export async function pageUserApi(params: UserPageDto) {
  return requestClient.post<PageVo<UserVo>>(
    '/api/adminSpace/userMgr/page',
    params,
  );
}

export async function getUserByIdApi(id: string) {
  return requestClient.post<UserVo>(
    `/api/adminSpace/userMgr/findById?id=${id}`,
  );
}

// 新增用户
export async function createUserApi(params: UserCreateDto) {
  return requestClient.post<UserVo>('/api/adminSpace/userMgr/create', params);
}

export async function updateUserApi(params: UserUpdateDto) {
  return requestClient.post<UserVo>('/api/adminSpace/userMgr/update', params);
}

export async function deleteUsersApi(params: { ids: string[] }) {
  return requestClient.post('/api/adminSpace/userMgr/delete', params);
}
