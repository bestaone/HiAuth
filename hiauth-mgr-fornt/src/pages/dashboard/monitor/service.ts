import { request } from 'umi';
import type { TagType } from './data';

export async function queryTags(): Promise<{ data: { list: TagType[] } }> {
  return request('https://proapi.azurewebsites.net/api/tags');
}
