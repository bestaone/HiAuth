<script lang="ts" setup>
import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { RoleVo } from '#/api/core/role';

import { ref } from 'vue';

import { Page, useVbenDrawer, useVbenModal } from '@vben/common-ui';

import { Button, Input, Popconfirm } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { EnableStatusUseBool } from '#/api/core/common';
import { deleteRolesApi, getRoleByIdApi, pageRoleApi } from '#/api/core/role';
import { ACTION } from '#/common/constants';

import AuthModalCom from './auth-modal.vue';
import RoleDrawerCom from './role-drawer.vue';
// import CorpAppAddModalCom from "#/views/corpSpace/corpAppMgr/corp-app-add-modal.vue";

const { Search } = Input;

const keyword = ref<string>();

const gridOptions: VxeGridProps<RoleVo> = {
  height: 'auto',
  size: 'medium',
  border: true,
  keepSource: true,
  checkboxConfig: {
    highlight: true,
  },
  rowConfig: {
    isCurrent: true,
    isHover: true,
  },
  columns: [
    { title: '序号', type: 'seq', width: 50 },
    { field: 'name', title: '名称' },
    {
      field: 'isEnable',
      title: '状态',
      slots: { default: 'isEnable' },
      width: 80,
    },
    { field: 'remark', title: '备注' },
    { field: 'createTime', title: '创建时间', formatter: 'formatDateTime' },
    {
      field: 'action',
      fixed: 'right',
      slots: { default: 'action' },
      title: '操作',
      width: 220,
    },
  ],
  pagerConfig: {},
  proxyConfig: {
    ajax: {
      query: async ({ page }) => {
        return await pageRoleApi({
          pageNum: page.currentPage,
          pageSize: page.pageSize,
          name: keyword.value,
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

const gridEvents: VxeGridListeners<RoleVo> = {
  cellDblclick: (e) => {
    if (e.column.field !== 'action') {
      viewDrawer(e.row);
      e.$event.stopPropagation();
    }
  },
};

const [Grid, gridApi] = useVbenVxeGrid({
  gridOptions,
  gridEvents,
});

const [RoleDrawer, roleDrawerApi] = useVbenDrawer({
  connectedComponent: RoleDrawerCom,
});

const [AuthModal, authModalApi] = useVbenModal({
  connectedComponent: AuthModalCom,
});

async function reloadGrid() {
  await gridApi.query();
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
}

async function viewDrawer(vo: RoleVo) {
  const role: RoleVo = await getRoleByIdApi(vo.id);
  roleDrawerApi.setState({ title: '查看' });
  roleDrawerApi.setData({ action: ACTION.VIEW, role });
  roleDrawerApi.open();
}

async function addDrawer() {
  roleDrawerApi.setState({ title: '新增' });
  roleDrawerApi.setData({ action: ACTION.ADD, role: {}, callback: reloadGrid });
  roleDrawerApi.open();
}

async function editDrawer(vo: RoleVo) {
  const role: RoleVo = await getRoleByIdApi(vo.id);
  roleDrawerApi.setState({ title: '编辑' });
  roleDrawerApi.setData({ action: ACTION.EDIT, role, callback: reloadGrid });
  roleDrawerApi.open();
}

async function auth(vo: RoleVo) {
  authModalApi.setData({ roleId: vo.id, callback: reloadGrid });
  authModalApi.open();
}

async function delRole(row: RoleVo) {
  await deleteRolesApi({ ids: [row.id] });
  await reloadGrid();
}
</script>

<template>
  <Page auto-content-height>
    <Grid>
      <template #toolbar-actions>
        <Search
          :style="{ width: '240px' }"
          allow-clear
          placeholder="名称"
          @search="onSearch"
        />
      </template>
      <template #toolbar-tools>
        <Button
          class="vxe-button type--button size--medium is--circle"
          @click="addDrawer"
        >
          <span class="icon-[ant-design--plus-outlined]"></span>
        </Button>
      </template>
      <template #isEnable="{ row }">
        <div>
          {{
            EnableStatusUseBool[
              row.isEnable as keyof typeof EnableStatusUseBool
            ]
          }}
        </div>
      </template>
      <template #action="{ row }">
        <Button type="link" @click="editDrawer(row)">编辑</Button>
        <Button type="link" @click="auth(row)">授权</Button>
        <Popconfirm
          :description="`确定要删除【${row.name}】?`"
          cancel-text="否"
          ok-text="是"
          title="删除"
          @confirm="() => delRole(row)"
        >
          <Button type="link">删除</Button>
        </Popconfirm>
      </template>
    </Grid>
    <RoleDrawer />
    <AuthModal />
  </Page>
</template>
