<script lang="ts" setup>
import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { AppResourceVo } from '#/api/core/appResource';

import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { useUserStore } from '@vben/stores';

import { Button, Input, Popconfirm } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  AppResourceType,
  deleteAppResourcesApi,
  getAppResourceByIdApi,
  pageAppResourceApi,
} from '#/api/core/appResource';
import { ACTION, ROLE_SYS_ADMIN } from '#/common/constants';

import AppResourceDrawerCom from './app-resource-drawer.vue';

const { Search } = Input;

const route = useRoute();
const userStore = useUserStore();

const appIdRef = ref<string>('');
const keyword = ref<string>();
const roles = userStore.userInfo?.roles ?? [];
const isSysAdmin: boolean = roles.some((role) => ROLE_SYS_ADMIN.includes(role));

onMounted(async () => {
  appIdRef.value = route.params.id as string;
});

const gridOptions: VxeGridProps<AppResourceVo> = {
  height: 'auto',
  size: 'medium',
  align: 'left',
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
    { field: 'name', title: '名称', treeNode: true },
    { field: 'code', title: '编号' },
    { field: 'url', title: '页面地址' },
    { field: 'api', title: '接口地址' },
    {
      field: 'type',
      title: '类型',
      slots: { default: 'type' },
      width: 80,
    },
    { field: 'createTime', title: '创建时间', formatter: 'formatDateTime' },
    {
      field: 'action',
      fixed: 'right',
      slots: { default: 'action' },
      title: '操作',
      width: 160,
      visible: isSysAdmin,
    },
  ],
  pagerConfig: {
    pageSize: 200,
    pageSizes: [200, 500],
  },
  treeConfig: {
    parentField: 'pid',
    rowField: 'id',
    transform: true,
  },
  proxyConfig: {
    ajax: {
      query: async ({ page }) => {
        return await pageAppResourceApi({
          pageNum: page.currentPage,
          pageSize: page.pageSize,
          appId: appIdRef.value,
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

const gridEvents: VxeGridListeners<AppResourceVo> = {
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

const [AppResourceDrawer, appResourceDrawerApi] = useVbenDrawer({
  connectedComponent: AppResourceDrawerCom,
});

async function reloadGrid() {
  await gridApi.query();
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
}

async function viewDrawer(vo: AppResourceVo) {
  const appResource: AppResourceVo = await getAppResourceByIdApi(vo.id);
  appResourceDrawerApi.setState({ title: '查看' });
  appResourceDrawerApi.setData({
    appId: appIdRef.value,
    action: ACTION.VIEW,
    appResource,
  });
  appResourceDrawerApi.open();
}

async function addDrawer() {
  appResourceDrawerApi.setState({ title: '新增' });
  appResourceDrawerApi.setData({
    appId: appIdRef.value,
    action: ACTION.ADD,
    callback: reloadGrid,
  });
  appResourceDrawerApi.open();
}

async function editDrawer(vo: AppResourceVo) {
  const appResource: AppResourceVo = await getAppResourceByIdApi(vo.id);
  appResourceDrawerApi.setState({ title: '编辑' });
  appResourceDrawerApi.setData({
    appId: appIdRef.value,
    action: ACTION.EDIT,
    appResource,
    callback: reloadGrid,
  });
  appResourceDrawerApi.open();
}

async function delAppResource(row: AppResourceVo) {
  await deleteAppResourcesApi({ ids: [row.id] });
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
          placeholder="名称、编号"
          @search="onSearch"
        />
      </template>
      <template #toolbar-tools>
        <Button
          v-show="isSysAdmin"
          class="vxe-button type--button size--medium is--circle"
          @click="addDrawer"
        >
          <span class="icon-[ant-design--plus-outlined]"></span>
        </Button>
      </template>
      <template #type="{ row }">
        <div>
          {{ AppResourceType[row.type as keyof typeof AppResourceType] }}
        </div>
      </template>
      <template #action="{ row }">
        <Button type="link" @click="editDrawer(row)">编辑</Button>
        <Popconfirm
          :description="`确定要删除【${row.name}】?`"
          cancel-text="否"
          ok-text="是"
          title="删除"
          @confirm="() => delAppResource(row)"
        >
          <Button type="link">删除</Button>
        </Popconfirm>
      </template>
    </Grid>
    <AppResourceDrawer />
  </Page>
</template>
