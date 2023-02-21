import Layout from '@/views/layout/Layout'

const userRouter = {
  path: '/user_manager',
  component: Layout,
  redirect: 'noredirect',
  name: 'UserManager',
  meta: {
    title: 'userManager',
    icon: 'table'
  },
  children: [
    {
      path: 'user-list',
      component: () => import('@/views/user/userList'),
      name: 'UserList',
      meta: { title: 'userList' }
    },
    {
      path: 'role-list',
      component: () => import('@/views/user/roleList'),
      name: 'RoleList',
      meta: { title: 'roleList' }
    }
  ]
}
export default userRouter
