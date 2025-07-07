<script lang="ts" setup>
import type { VbenFormProps } from '#/adapter/form';
import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { UserVo } from '#/api';

import { onMounted, ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';

import { Button, Input, message, Popconfirm } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteUsersApi, GenderType, getUserByIdApi, pageUserApi } from '#/api';
import {
  EnableStatusUseNum,
  YesOrNoUseBool,
  YesOrNoUseNumOpt,
} from '#/api/core/common';
import { ACTION } from '#/common/constants';

import UserDrawerCom from './user-drawer.vue';

const { Search } = Input;

const apiUrl = import.meta.env.VITE_GLOB_API_URL;
const simpleSearch = ref<boolean>(false);
const keyword = ref<string>();

const formOptions: VbenFormProps = {
  // 默认展开
  collapsed: true,
  schema: [
    {
      component: 'Input',
      componentProps: {
        allowClear: true,
      },
      fieldName: 'name',
      label: '姓名：',
    },
    {
      component: 'Input',
      componentProps: {
        allowClear: true,
      },
      fieldName: 'username',
      label: '用户名：',
    },
    {
      component: 'Input',
      componentProps: {
        allowClear: true,
      },
      fieldName: 'phoneNum',
      label: '手机号：',
    },
    {
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: YesOrNoUseNumOpt,
        placeholder: '请选择',
      },
      fieldName: 'isSysAdmin',
      label: '管理员：',
    },
    {
      component: 'RangePicker',
      fieldName: 'regtime',
      label: '注册时间：',
    },
  ],
  // 控制表单是否显示折叠按钮
  showCollapseButton: true,
  // 按下回车时是否提交表单
  submitOnEnter: false,
};

const gridOptions: VxeGridProps<UserVo> = {
  size: 'medium',
  border: true,
  height: 'auto',
  keepSource: true,
  checkboxConfig: {
    highlight: true,
    labelField: 'name',
  },
  rowConfig: {
    isCurrent: true,
    isHover: true,
  },
  columns: [
    { title: '序号', type: 'seq', width: 50 },
    { field: 'avatar', title: '头像', slots: { default: 'avatar' }, width: 50 },
    { field: 'name', title: '姓名' },
    { field: 'username', title: '账号' },
    { field: 'phoneNum', title: '手机号码' },
    { field: 'gender', title: '性别', slots: { default: 'gender' }, width: 80 },
    {
      field: 'isSysAdmin',
      title: '管理员',
      slots: { default: 'isSysAdmin' },
      width: 80,
    },
    { field: 'status', title: '状态', slots: { default: 'status' }, width: 80 },
    { field: 'regtime', title: '注册时间', formatter: 'formatDateTime' },
    { field: 'lastLoginTime', title: '最近登录', formatter: 'formatDateTime' },
    {
      field: 'action',
      fixed: 'right',
      slots: { default: 'action' },
      title: '操作',
      width: 260,
    },
  ],
  pagerConfig: {},
  proxyConfig: {
    ajax: {
      query: async ({ page }, formValues) => {
        return await pageUserApi({
          pageNum: page.currentPage,
          pageSize: page.pageSize,
          keyword: keyword.value,
          ...formValues,
        });
      },
    },
    response: {
      result: 'records',
      total: 'totalCount',
    },
  },
  toolbarConfig: {
    custom: true,
    refresh: true,
    zoom: true,
  },
};

const gridEvents: VxeGridListeners<UserVo> = {
  cellDblclick: (e) => {
    if (e.column.field !== 'action') {
      viewDrawer(e.row);
      e.$event.stopPropagation();
    }
  },
};

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions,
  gridOptions,
  gridEvents,
});

const [UserDrawer, userDrawerApi] = useVbenDrawer({
  connectedComponent: UserDrawerCom,
});

async function reloadGrid() {
  await gridApi.query();
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
}

async function viewDrawer(vo: UserVo) {
  const user: UserVo = await getUserByIdApi(vo.id);
  userDrawerApi.setState({ title: '查看' });
  userDrawerApi.setData({ action: ACTION.VIEW, user });
  userDrawerApi.open();
}

async function addDrawer() {
  userDrawerApi.setState({ title: '新增' });
  userDrawerApi.setData({ action: ACTION.ADD, callback: reloadGrid });
  userDrawerApi.open();
}

async function search() {
  simpleSearch.value = !simpleSearch.value;
  gridApi.toggleSearchForm(!simpleSearch.value);
}

async function editDrawer(vo: UserVo) {
  const user: UserVo = await getUserByIdApi(vo.id);
  userDrawerApi.setState({ title: '编辑' });
  userDrawerApi.setData({ action: ACTION.EDIT, user, callback: reloadGrid });
  userDrawerApi.open();
}

async function resetPwd(row: UserVo) {
  message.success({
    content: `form values: ${JSON.stringify(row)}`,
  });
}

async function delUser(row: UserVo) {
  await deleteUsersApi({ ids: [row.id] });
  await reloadGrid();
}

onMounted(async () => {});
</script>

<template>
  <Page auto-content-height>
    <Grid>
      <template #toolbar-actions>
        <Search
          :style="{ width: '240px' }"
          v-if="simpleSearch"
          allow-clear
          placeholder="名称、编号"
          @search="onSearch"
        />
      </template>
      <template #toolbar-tools>
        <Button
          class="vxe-button type--button size--medium is--circle"
          @click="search"
        >
          <span class="icon-[ant-design--search-outlined]"></span>
        </Button>
        <Button
          class="vxe-button type--button size--medium is--circle"
          @click="addDrawer"
        >
          <span class="icon-[ant-design--plus-outlined]"></span>
        </Button>
      </template>
      <template #avatar="{ row }">
        <img
          :src="apiUrl + row.avatar"
          alt=""
          style="width: 28px; height: 28px"
        />
      </template>
      <template #gender="{ row }">
        <div>{{ GenderType[row.gender as keyof typeof GenderType] }}</div>
      </template>
      <template #isSysAdmin="{ row }">
        <div>
          {{
            YesOrNoUseBool[
              row.isSysAdmin?.toString() as keyof typeof YesOrNoUseBool
            ]
          }}
        </div>
      </template>
      <template #status="{ row }">
        <div>
          {{
            EnableStatusUseNum[row.status as keyof typeof EnableStatusUseNum]
          }}
        </div>
      </template>
      <template #action="{ row }">
        <Button type="link" @click="editDrawer(row)">编辑</Button>
        <Button type="link" @click="resetPwd(row)">重置密码</Button>
        <Popconfirm
          :description="`确定要删除【${row.name}】?`"
          cancel-text="否"
          ok-text="是"
          title="删除"
          @confirm="() => delUser(row)"
        >
          <Button type="link">删除</Button>
        </Popconfirm>
      </template>
    </Grid>
    <UserDrawer />
  </Page>
</template>
