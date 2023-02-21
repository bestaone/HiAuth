// https://umijs.org/config/
import { defineConfig } from 'umi';
import { join } from 'path';
import defaultSettings from './defaultSettings';
import proxy from './proxy';
const { REACT_APP_ENV } = process.env;
export default defineConfig({
  hash: true,
  antd: {},
  dva: {
    hmr: true,
  },
  layout: {
    // https://umijs.org/zh-CN/plugins/plugin-layout
    locale: true,
    siderWidth: 208,
    splitMenus: false,
    ...defaultSettings,
  },
  // https://umijs.org/zh-CN/plugins/plugin-locale
  locale: {
    // default zh-CN
    default: 'zh-CN',
    antd: true,
    // default true, when it is true, will use `navigator.language` overwrite default
    baseNavigator: true,
  },
  dynamicImport: {
    loading: '@ant-design/pro-layout/es/PageLoading',
  },
  targets: {
    ie: 11,
  },
  // umi routes: https://umijs.org/docs/routing
  routes: [
    {
      path: '/user',
      layout: false,
      routes: [
        {
          path: '/user/login',
          layout: false,
          name: 'login',
          component: './user/Login',
        },
        {
          path: '/user',
          redirect: '/user/login',
        },
        {
          name: 'register.result',
          icon: 'smile',
          path: '/user/register-result',
          component: './user/register-result',
        },
        {
          name: 'register',
          icon: 'smile',
          path: '/user/register',
          component: './user/register',
        },
        {
          component: '404',
        },
      ],
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      icon: 'dashboard',
      routes: [
        {
          name: 'analysis',
          icon: 'smile',
          path: '/dashboard/analysis',
          component: './dashboard/analysis',
        },
        {
          name: 'monitor',
          icon: 'smile',
          path: '/dashboard/monitor',
          component: './dashboard/monitor',
        },
        {
          name: 'workplace',
          icon: 'smile',
          path: '/dashboard/workplace',
          component: './dashboard/workplace',
        },
      ],
    },
    {
      path: '/biz',
      icon: 'form',
      name: 'biz',
      resourceKey: 'bizMgr@menuGroup',
      access: 'resourceRouteFilter',
      routes: [
        {
          name: 'task-mgr',
          icon: 'smile',
          path: '/biz/task-mgr',
          component: './biz/task-mgr',
          resourceKey: 'taskMgr@menuGroup',
          access: 'resourceRouteFilter',
        },
      ],
    },
    {
      path: '/org',
      icon: 'cluster',
      name: 'org',
      resourceKey: 'orgMgr@menuGroup',
      access: 'resourceRouteFilter',
      routes: [
        {
          name: 'dep-mgr',
          icon: 'smile',
          path: '/org/dep-mgr',
          component: './org/dep-mgr',
          resourceKey: 'depMgr@menuGroup',
          access: 'resourceRouteFilter',
        },
        {
          name: 'emp-mgr',
          icon: 'smile',
          path: '/org/emp-mgr',
          component: './org/emp-mgr',
          resourceKey: 'empMgr@menuGroup',
          access: 'resourceRouteFilter',
        },
        {
          name: 'role-mgr',
          icon: 'smile',
          path: '/org/role-mgr',
          component: './org/role-mgr',
          resourceKey: 'roleMgr@menuGroup',
          access: 'resourceRouteFilter',
        },
      ],
    },
    {
      path: '/sys',
      icon: 'setting',
      name: 'sys',
      routes: [
        {
          name: 'app-mgr',
          icon: 'smile',
          path: '/sys/app-mgr',
          component: './sys/app-mgr',
        },
        {
          name: 'user-mgr',
          icon: 'smile',
          path: '/sys/user-mgr',
          component: './sys/user-mgr',
        },
      ],
    },
    {
      name: 'exception',
      icon: 'warning',
      path: '/exception',
      resourceKey: 'exceptionMgr@menuGroup',
      access: 'resourceRouteFilter',
      routes: [
        {
          name: '403',
          icon: 'smile',
          path: '/exception/403',
          component: './exception/403',
          resourceKey: '403@menuGroup',
          access: 'resourceRouteFilter',
        },
        {
          name: '404',
          icon: 'smile',
          path: '/exception/404',
          component: './exception/404',
          resourceKey: '404@menuGroup',
          access: 'resourceRouteFilter',
        },
        {
          name: '500',
          icon: 'smile',
          path: '/exception/500',
          component: './exception/500',
          resourceKey: '500@menuGroup',
          access: 'resourceRouteFilter',
        },
      ],
    },
    {
      name: 'account',
      icon: 'user',
      path: '/account',
      routes: [
        {
          name: 'center',
          icon: 'smile',
          path: '/account/center',
          component: './account/center',
        },
        {
          name: 'settings',
          icon: 'smile',
          path: '/account/settings',
          component: './account/settings',
        },
      ],
    },
    {
      path: '/',
      redirect: '/dashboard/analysis',
    },
    {
      component: '404',
    },
  ],
  // Theme for antd: https://ant.design/docs/react/customize-theme-cn
  theme: {
    'primary-color': defaultSettings.primaryColor,
  },
  // esbuild is father build tools
  // https://umijs.org/plugins/plugin-esbuild
  esbuild: {},
  title: false,
  ignoreMomentLocale: true,
  proxy: proxy[REACT_APP_ENV || 'dev'],
  manifest: {
    basePath: '/',
  },
  // Fast Refresh 热更新
  fastRefresh: {},
  openAPI: [
    {
      requestLibPath: "import { request } from 'umi'",
      // 或者使用在线的版本
      // schemaPath: "https://gw.alipayobjects.com/os/antfincdn/M%24jrzTTYJN/oneapi.json"
      schemaPath: join(__dirname, 'oneapi.json'),
      mock: false,
    },
    {
      requestLibPath: "import { request } from 'umi'",
      schemaPath: 'https://gw.alipayobjects.com/os/antfincdn/CA1dOm%2631B/openapi.json',
      projectName: 'swagger',
    },
  ],
  nodeModulesTransform: {
    type: 'none',
  },
  mfsu: {},
  webpack5: {},
  exportStatic: {},
});
