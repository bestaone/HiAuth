import Layout from '@/views/layout/Layout'

const appRouter = {
  path: '/app',
  component: Layout,
  redirect: 'noredirect',
  name: 'AppManager',
  meta: {
    title: 'appManager',
    icon: 'table',
    roles: ['ROLE_ADMIN']
  },
  children: [
    {
      path: 'app-list',
      component: () => import('@/views/app/appList'),
      name: 'AppList',
      meta: { title: 'appList' }
    }
  ]
}
export default appRouter
