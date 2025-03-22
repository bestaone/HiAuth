import type { RouteRecordRaw } from 'vue-router';

import { BasicLayout } from '#/layouts';

const routes: RouteRecordRaw[] = [
  {
    component: BasicLayout,
    name: 'appResourceMgr',
    path: '/common/appResourceMgr',
    meta: {
      title: '应用配置',
      icon: 'ic:baseline-view-in-ar',
      keepAlive: true,
      order: 1000,
      hideInMenu: true,
    },
    children: [
      {
        name: 'appResourceList',
        path: '/common/appResourceMgr/list/:id',
        meta: {
          title: '配置列表',
          isHide: true,
        },
        component: () => import('#/views/common/appResourceMgr/index.vue'),
      },
    ],
  },
];

export default routes;
