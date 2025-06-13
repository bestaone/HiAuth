<script lang="ts" setup>
import type { VbenFormProps } from '#/adapter/form';
import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { DictVo } from '#/api';

import { onMounted, ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';

import { Button, Input, Popconfirm } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteDictApi,
  findSubDictApi,
  getDictByIdApi,
  pageDictApi,
} from '#/api';
import { EnableStatusUseBool } from '#/api/core/common';
import { ACTION } from '#/common/constants';

import DictDrawerCom from './dict-drawer.vue';

const { Search } = Input;

const simpleSearch = ref<boolean>(true);
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
      label: '名称：',
    },
    {
      component: 'Input',
      componentProps: {
        allowClear: true,
      },
      fieldName: 'code',
      label: '编码：',
    },
  ],
  // 控制表单是否显示折叠按钮
  showCollapseButton: false,
  // 按下回车时是否提交表单
  submitOnEnter: false,
};

const gridOptions: VxeGridProps<DictVo> = {
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
  treeConfig: {
    parentField: 'pCode',
    rowField: 'code',
    transform: true,
    lazy: true,
    hasChildField: 'hasChild',
    loadMethod: async ({ row }) => {
      return findSubDictApi({ offset: 0, limit: 200, pcode: row.code });
    },
  },
  columns: [
    { title: '序号', type: 'seq', width: 50 },
    { field: 'name', title: '名称', align: 'left', treeNode: true },
    { field: 'code', title: '编码', align: 'left' },
    { field: 'value', title: '值', align: 'left' },
    { field: 'createTime', title: '创建时间', formatter: 'formatDateTime' },
    {
      field: 'isEnable',
      title: '状态',
      slots: { default: 'isEnable' },
      width: 80,
    },
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
        const { code, name } = formValues;
        return await pageDictApi({
          pageNum: page.currentPage,
          pageSize: page.pageSize,
          isRoot: !code && !name && !keyword.value,
          keyword: keyword.value,
          code,
          name,
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

const gridEvents: VxeGridListeners<DictVo> = {
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

const [DictDrawer, dictDrawerApi] = useVbenDrawer({
  connectedComponent: DictDrawerCom,
});

async function reloadGrid() {
  await gridApi.query();
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
}

async function viewDrawer(vo: DictVo) {
  const dict: DictVo = await getDictByIdApi(vo.id);
  dictDrawerApi.setState({ title: '查看' });
  dictDrawerApi.setData({ action: ACTION.VIEW, dict });
  dictDrawerApi.open();
}

async function createDrawer() {
  await addDrawer(null);
}

async function addDrawer(row: DictVo | null) {
  dictDrawerApi.setState({ title: '新增' });
  dictDrawerApi.setData({
    action: ACTION.ADD,
    dict: { pcode: row?.code },
    callback: reloadGrid,
  });
  dictDrawerApi.open();
}

async function search() {
  simpleSearch.value = !simpleSearch.value;
  gridApi.toggleSearchForm(!simpleSearch.value);
}

async function editDrawer(vo: DictVo) {
  const dict: DictVo = await getDictByIdApi(vo.id);
  dictDrawerApi.setState({ title: '编辑' });
  dictDrawerApi.setData({ action: ACTION.EDIT, dict, callback: reloadGrid });
  dictDrawerApi.open();
}

async function delDict(row: DictVo) {
  await deleteDictApi({ ids: [row.id] });
  await reloadGrid();
}

onMounted(async () => {
  gridApi.toggleSearchForm(!simpleSearch.value);
});
</script>

<template>
  <Page auto-content-height>
    <Grid>
      <template #toolbar-actions>
        <Search
          :style="{ width: '240px' }"
          v-if="simpleSearch"
          allow-clear
          placeholder="名称、编码"
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
          @click="createDrawer"
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
        <Button type="link" @click="addDrawer(row)">添加</Button>
        <Button type="link" @click="editDrawer(row)">编辑</Button>
        <Popconfirm
          :description="`确定要删除【${row.name}】?`"
          cancel-text="否"
          ok-text="是"
          title="删除"
          @confirm="() => delDict(row)"
        >
          <Button type="link">删除</Button>
        </Popconfirm>
      </template>
    </Grid>
    <DictDrawer />
  </Page>
</template>
