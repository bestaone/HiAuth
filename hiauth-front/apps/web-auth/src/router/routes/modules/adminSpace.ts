import type { RouteRecordRaw } from 'vue-router';

import { ROLE_SYS_ADMIN } from '#/common/constants';
import { BasicLayout } from '#/layouts';

const routes: RouteRecordRaw[] = [
  {
    component: BasicLayout,
    name: 'corpMgr',
    path: '/adminSpace/corpMgr',
    meta: {
      title: '租户管理',
      icon: 'ant-design:solution-outlined',
      keepAlive: true,
      order: 1000,
      authority: [ROLE_SYS_ADMIN],
    },
    children: [
      {
        name: 'corpList',
        path: '/adminSpace/corpMgr/list',
        meta: {
          title: '租户列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/adminSpace/corpMgr/index.vue'),
      },
    ],
  },
  {
    component: BasicLayout,
    name: 'userMgr',
    path: '/adminSpace/userMgr',
    meta: {
      title: '用户管理',
      icon: 'ant-design:user-outlined',
      keepAlive: true,
      order: 1000,
      authority: [ROLE_SYS_ADMIN],
    },
    children: [
      {
        name: 'userList',
        path: '/adminSpace/userMgr/list',
        meta: {
          title: '用户列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/adminSpace/userMgr/index.vue'),
      },
    ],
  },
  {
    component: BasicLayout,
    name: 'appMgr',
    path: '/adminSpace/appMgr',
    meta: {
      title: '应用管理',
      icon: 'ant-design:appstore-twotone',
      keepAlive: true,
      order: 1000,
      authority: [ROLE_SYS_ADMIN],
    },
    children: [
      {
        name: 'appList',
        path: '/adminSpace/appMgr/list',
        meta: {
          title: '应用列表',
          icon: 'ant-design:bars-outlined',
        },
        component: () => import('#/views/common/appMgr/index.vue'),
      },
    ],
  },
];

export default routes;
