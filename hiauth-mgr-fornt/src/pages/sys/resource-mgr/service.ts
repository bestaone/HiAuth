import {request} from 'umi';
import {Resource, ResourceGroup, Permission, ResourceInfo} from "@/pages/sys/resource-mgr/data";

export async function limitResourceGroup(
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<ResourceGroup[]>>('/api/resourceGroup/limitList', {
    method: 'POST',
    data: { offset: 0, limit: 100 },
    ...(options || {}),
  });
}

export async function limitResourceByGroupId(
  groupId: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<Resource[]>>('/api/resource/limitList', {
    method: 'POST',
    data: { 'groupId': groupId, offset: 0, limit: 100 },
    ...(options || {}),
  });
}

export async function findResourceAndPermissionByRoleAndGroup(
  roleId: number,
  groupId: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<number[]>>('/api/resource/findResourceAndPermissionByRoleAndGroup', {
    method: 'POST',
    params: { 'roleId': roleId, 'groupId': groupId },
    ...(options || {}),
  });
}

export async function limitListByRoleIdAndGroupId(
  groupId: number,
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<ResourceInfo[]>>('/api/resource/limitListByRoleIdAndGroupId', {
    method: 'POST',
    data: {
      groupId,
      pageNum: 1,
      pageSize: 100
    },
    ...(options || {}),
  });
}

export async function pagePermissionByResourceId(
  body: {
    resourceId: number
    pageNum: number;
    pageSize: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<API.Page<Permission>>>('/api/permission/pageByResourceId', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

// export async function updateResourceAndPermission(
//   body: {
//     roleId?: number
//     opType: number;
//     resourceIds?: number[];
//     permissionIds?: number[];
//   },
//   options?: Record<string, any>
// ) {
//   return request<API.BasicApiResponse<API.Page<Permission>>>('/api/resource/updateResourceAndPermission', {
//     method: 'POST',
//     data: body,
//     ...(options || {}),
//   });
// }

export async function updateRoleResource(
  params: {
    opType: number;
    roleId?: number
    resourceId: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<API.Page<Permission>>>('/api/resource/updateRoleResource', {
    method: 'POST',
    params,
    ...(options || {}),
  });
}

export async function updateRolePermission(
  params: {
    opType: number;
    roleId?: number
    permissionId: number;
  },
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<API.Page<Permission>>>('/api/permission/updateRolePermission', {
    method: 'POST',
    params,
    ...(options || {}),
  });
}
