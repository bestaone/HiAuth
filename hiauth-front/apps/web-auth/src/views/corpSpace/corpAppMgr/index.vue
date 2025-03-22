<script lang="ts" setup>
import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { CorpAppVo } from '#/api/core/corpApp';

import { ref } from 'vue';
import { useRouter } from 'vue-router';

import { Page, useVbenDrawer, useVbenModal } from '@vben/common-ui';

import { Button, Input, Popconfirm } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteCorpAppsApi,
  getCorpAppByIdApi,
  pageCorpAppApi,
} from '#/api/core/corpApp';
import { ACTION } from '#/common/constants';

import CorpAppAddModalCom from './corp-app-add-modal.vue';
import CorpAppDrawerCom from './corp-app-drawer.vue';

const { Search } = Input;
const router = useRouter();

const apiUrl = import.meta.env.VITE_GLOB_API_URL;
const keyword = ref<string>();

const gridOptions: VxeGridProps<CorpAppVo> = {
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
    { field: 'clientId', title: 'clientId' },
    { field: 'scopes', title: 'scopes' },
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
        return await pageCorpAppApi({
          pageNum: page.currentPage,
          pageSize: page.pageSize,
          keyword: keyword.value,
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

const gridEvents: VxeGridListeners<CorpAppVo> = {
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

const [CorpAppDrawer, corpAppDrawerApi] = useVbenDrawer({
  connectedComponent: CorpAppDrawerCom,
});

const [CorpAppAddModal, corpAppAddModalApi] = useVbenModal({
  // 连接抽离的组件
  connectedComponent: CorpAppAddModalCom,
});

async function reloadGrid() {
  await gridApi.query();
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
}

async function viewDrawer(vo: CorpAppVo) {
  const corpApp: CorpAppVo = await getCorpAppByIdApi(vo.id);
  corpAppDrawerApi.setState({ title: '查看' });
  corpAppDrawerApi.setData({ action: ACTION.VIEW, corpApp });
  corpAppDrawerApi.open();
}

async function addDrawer() {
  corpAppAddModalApi.setData({ callback: reloadGrid });
  corpAppAddModalApi.open();
}

async function editDrawer(vo: CorpAppVo) {
  const corpApp: CorpAppVo = await getCorpAppByIdApi(vo.id);
  corpAppDrawerApi.setState({ title: '编辑' });
  corpAppDrawerApi.setData({
    action: ACTION.EDIT,
    corpApp,
    callback: reloadGrid,
  });
  corpAppDrawerApi.open();
}

async function toConfig(vo: CorpAppVo) {
  await router.push(`/common/appResourceMgr/list/${vo.appId}`);
}

async function delCorpApp(row: CorpAppVo) {
  await deleteCorpAppsApi({ ids: [row.id] });
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
          @confirm="() => delCorpApp(row)"
        >
          <Button type="link">删除</Button>
        </Popconfirm>
      </template>
    </Grid>
    <CorpAppDrawer />
    <CorpAppAddModal />
  </Page>
</template>
