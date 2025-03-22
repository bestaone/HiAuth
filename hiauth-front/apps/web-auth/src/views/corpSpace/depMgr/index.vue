<script lang="ts" setup>
import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { DepVo } from '#/api/core/dep';

import { ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';

import { Button, Input, Popconfirm } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { EnableStatusUseNum } from '#/api/core/common';
import { deleteDepsApi, getDepByIdApi, pageDepApi } from '#/api/core/dep';
import { ACTION } from '#/common/constants';

import DepDrawerCom from './dep-drawer.vue';

const { Search } = Input;

const keyword = ref<string>();

const gridOptions: VxeGridProps<DepVo> = {
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
    { field: 'no', title: '编号' },
    {
      field: 'status',
      title: '状态',
      slots: { default: 'status' },
      width: 80,
    },
    { field: 'remark', title: '备注' },
    { field: 'createTime', title: '创建时间', formatter: 'formatDateTime' },
    {
      field: 'action',
      fixed: 'right',
      slots: { default: 'action' },
      title: '操作',
      width: 160,
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
    expandAll: true,
  },
  proxyConfig: {
    ajax: {
      query: async ({ page }) => {
        return await pageDepApi({
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

const gridEvents: VxeGridListeners<DepVo> = {
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

const [DepDrawer, depDrawerApi] = useVbenDrawer({
  connectedComponent: DepDrawerCom,
});

async function reloadGrid() {
  await gridApi.query();
  await gridApi.grid?.setAllTreeExpand(true);
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
  await gridApi.grid?.setAllTreeExpand(true);
}

async function viewDrawer(vo: DepVo) {
  const dep: DepVo = await getDepByIdApi(vo.id);
  depDrawerApi.setState({ title: '查看' });
  depDrawerApi.setData({ action: ACTION.VIEW, dep });
  depDrawerApi.open();
}

async function addDrawer() {
  depDrawerApi.setState({ title: '新增' });
  depDrawerApi.setData({ action: ACTION.ADD, callback: reloadGrid });
  depDrawerApi.open();
}

async function editDrawer(vo: DepVo) {
  const dep: DepVo = await getDepByIdApi(vo.id);
  depDrawerApi.setState({ title: '编辑' });
  depDrawerApi.setData({ action: ACTION.EDIT, dep, callback: reloadGrid });
  depDrawerApi.open();
}

async function delDep(row: DepVo) {
  await deleteDepsApi({ ids: [row.id] });
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
          class="vxe-button type--button size--medium is--circle"
          @click="addDrawer"
        >
          <span class="icon-[ant-design--plus-outlined]"></span>
        </Button>
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
        <Popconfirm
          :description="`确定要删除【${row.name}】?`"
          cancel-text="否"
          ok-text="是"
          title="删除"
          @confirm="() => delDep(row)"
        >
          <Button type="link">删除</Button>
        </Popconfirm>
      </template>
    </Grid>
    <DepDrawer />
  </Page>
</template>
