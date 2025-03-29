import type { RouteRecordRaw } from 'vue-router';

import { ROLE_CORP_ADMIN } from '#/common/constants';
import { BasicLayout } from '#/layouts';

const routes: RouteRecordRaw[] = [
  {
    component: BasicLayout,
    name: 'depMgr',
    path: '/corpSpace/depMgr',
    meta: {
      title: '部门管理',
      icon: 'ant-design:bank-twotone',
      keepAlive: true,
      order: 1000,
      authority: [ROLE_CORP_ADMIN],
    },
    children: [
      {
        name: 'depList',
        path: '/corpSpace/depMgr/list',
        meta: {
          title: '部门列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/corpSpace/depMgr/index.vue'),
      },
    ],
  },
  {
    component: BasicLayout,
    name: 'empMgr',
    path: '/corpSpace/empMgr',
    meta: {
      title: '员工管理',
      icon: 'mdi:account-hard-hat-outline',
      keepAlive: true,
      order: 1000,
      authority: [ROLE_CORP_ADMIN],
    },
    children: [
      {
        name: 'empList',
        path: '/corpSpace/empMgr/list',
        meta: {
          title: '员工列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/corpSpace/empMgr/index.vue'),
      },
    ],
  },
  {
    component: BasicLayout,
    name: 'roleMgr',
    path: '/corpSpace/roleMgr',
    meta: {
      title: '角色管理',
      icon: 'ant-design:team-outlined',
      keepAlive: true,
      order: 1000,
      authority: [ROLE_CORP_ADMIN],
    },
    children: [
      {
        name: 'corpList',
        path: '/corpSpace/roleMgr/list',
        meta: {
          title: '角色列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/corpSpace/roleMgr/index.vue'),
      },
    ],
  },
  {
    component: BasicLayout,
    name: 'corpAppMgr',
    path: '/corpSpace/corpAppMgr',
    meta: {
      title: '应用管理',
      icon: 'ant-design:appstore-twotone',
      keepAlive: true,
      order: 1000,
      authority: [ROLE_CORP_ADMIN],
    },
    children: [
      {
        name: 'corpAppList',
        path: '/corpSpace/corpAppMgr/list',
        meta: {
          title: '应用列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/common/appMgr/index.vue'),
      },
      {
        name: 'appClientList',
        path: '/corpSpace/appClientMgr/list',
        meta: {
          title: '客户端列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/corpSpace/appClientMgr/index.vue'),
      },
    ],
  },
];

export default routes;
