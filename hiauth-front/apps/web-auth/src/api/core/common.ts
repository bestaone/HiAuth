import { requestClient } from '#/api/request';

export interface R<T = any> {
  code: string;
  msg: string;
  data: T;
}

export interface PageVo<T> {
  records: T[];
  pageNum: number;
  pageSize: number;
  totalCount: number;
}

export interface PageDto {
  pageNum: number;
  pageSize: number;
}

export interface LimitDto {
  offset: number;
  limit: number;
}

export interface FileUrlVo {
  fileUrl: string;
}

export interface MetadataVo {
  usernamePlaceholder: string;
  passwordPlaceholder: string;
  encryptType: string;
  publicKey: string;
}

export const YesOrNoUseNum = { 0: '否', 1: '是' };
export const YesOrNoUseNumOpt = [
  { label: '否', value: 0 },
  { label: '是', value: 1 },
];

export const YesOrNoUseBool = { false: '否', true: '是' };
export const YesOrNoUseBoolOpt = [
  { label: '否', value: false },
  { label: '是', value: true },
];

export const EnableStatusUseNum = { 0: '禁用', 1: '启用' };
export const EnableStatusUseNumOpt = [
  { label: '禁用', value: 0 },
  { label: '启用', value: 1 },
];

export const EnableStatusUseBool = { false: '禁用', true: '启用' };
export const EnableStatusUseBoolOpt = [
  { label: '禁用', value: false },
  { label: '启用', value: true },
];

export async function getMetadataApi() {
  return requestClient.post<MetadataVo>('/unpapi/metadata', {});
}

export async function uploadImgApi(file: File) {
  return await requestClient.upload('/api/common/file/upload/img', { file });
}
