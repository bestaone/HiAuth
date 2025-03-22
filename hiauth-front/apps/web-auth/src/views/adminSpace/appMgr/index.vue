<script lang="ts" setup>
import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { AppVo } from '#/api/core/app';

import { ref } from 'vue';
import { useRouter } from 'vue-router';

import { Page, useVbenDrawer } from '@vben/common-ui';

import { Button, Input, Popconfirm } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteAppsApi, getAppByIdApi, pageAppApi } from '#/api/core/app';
import { ACTION } from '#/common/constants';

import AppDrawerCom from './app-drawer.vue';

const { Search } = Input;
const router = useRouter();

const apiUrl = import.meta.env.VITE_GLOB_API_URL;
const keyword = ref<string>();

const gridOptions: VxeGridProps<AppVo> = {
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
    { field: 'icon', title: '图标', slots: { default: 'icon' }, width: 50 },
    { field: 'name', title: '名称' },
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
        return await pageAppApi({
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

const gridEvents: VxeGridListeners<AppVo> = {
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

const [AppDrawer, appDrawerApi] = useVbenDrawer({
  connectedComponent: AppDrawerCom,
});

async function reloadGrid() {
  await gridApi.query();
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
}

async function viewDrawer(vo: AppVo) {
  const app: AppVo = await getAppByIdApi(vo.id);
  appDrawerApi.setState({ title: '查看' });
  appDrawerApi.setData({ action: ACTION.VIEW, app });
  appDrawerApi.open();
}

async function addDrawer() {
  appDrawerApi.setState({ title: '新增' });
  appDrawerApi.setData({ action: ACTION.ADD, callback: reloadGrid });
  appDrawerApi.open();
}

async function editDrawer(vo: AppVo) {
  const app: AppVo = await getAppByIdApi(vo.id);
  appDrawerApi.setState({ title: '编辑' });
  appDrawerApi.setData({ action: ACTION.EDIT, app, callback: reloadGrid });
  appDrawerApi.open();
}

async function toConfig(vo: AppVo) {
  await router.push(`/common/appResourceMgr/list/${vo.id}`);
}

async function delApp(row: AppVo) {
  await deleteAppsApi({ ids: [row.id] });
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
      <template #icon="{ row }">
        <img
          :src="apiUrl + row.icon"
          alt="图标"
          style="width: 28px; height: 28px"
        />
      </template>
      <template #action="{ row }">
        <Button type="link" @click="editDrawer(row)">编辑</Button>
        <Button type="link" @click="toConfig(row)">配置</Button>
        <Popconfirm
          :description="`确定要删除【${row.name}】?`"
          cancel-text="否"
          ok-text="是"
          title="删除"
          @confirm="() => delApp(row)"
        >
          <Button type="link">删除</Button>
        </Popconfirm>
      </template>
    </Grid>
    <AppDrawer />
  </Page>
</template>
