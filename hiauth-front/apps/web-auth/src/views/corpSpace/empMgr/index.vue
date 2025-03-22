<script lang="ts" setup>
import type { Key } from 'ant-design-vue/es/_util/type';
import type { TreeDataNode } from 'ant-design-vue/lib/vc-tree-select/interface';

import type { VxeGridListeners, VxeGridProps } from '#/adapter/vxe-table';
import type { EmpVo } from '#/api/core/emp';

import { onMounted, ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';

import { Button, Flex, Input, Popconfirm, Tree } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { YesOrNoUseBool } from '#/api/core/common';
import { depTreeApi } from '#/api/core/dep';
import { deleteEmpsApi, getEmpByIdApi, pageEmpApi } from '#/api/core/emp';
import { ACTION } from '#/common/constants';

import EmpDrawerCom from './emp-drawer.vue';

const { Search } = Input;

const depIds = ref<string[]>([]);
const keyword = ref<string>();

const gridOptions: VxeGridProps<EmpVo> = {
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
    { field: 'no', title: '工号' },
    { field: 'name', title: '姓名' },
    { field: 'email', title: '邮箱' },
    {
      field: 'isCorpAdmin',
      title: '企业管理员',
      slots: { default: 'isCorpAdmin' },
    },
    { field: 'createTime', title: '创建时间', formatter: 'formatDateTime' },
    {
      field: 'action',
      fixed: 'right',
      slots: { default: 'action' },
      title: '操作',
      width: 160,
    },
  ],
  pagerConfig: {},
  proxyConfig: {
    ajax: {
      query: async ({ page }) => {
        return await pageEmpApi({
          pageNum: page.currentPage,
          pageSize: page.pageSize,
          depIds: depIds.value,
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

const gridEvents: VxeGridListeners<EmpVo> = {
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

const [EmpDrawer, empDrawerApi] = useVbenDrawer({
  connectedComponent: EmpDrawerCom,
});

async function reloadGrid() {
  await gridApi.query();
}

async function onSearch(value: string) {
  keyword.value = value;
  await gridApi.query();
}

async function viewDrawer(vo: EmpVo) {
  const emp: EmpVo = await getEmpByIdApi(vo.id);
  empDrawerApi.setState({ title: '查看' });
  empDrawerApi.setData({ action: ACTION.VIEW, emp });
  empDrawerApi.open();
}

async function addDrawer() {
  empDrawerApi.setState({ title: '新增' });
  empDrawerApi.setData({ action: ACTION.ADD, callback: reloadGrid });
  empDrawerApi.open();
}

async function editDrawer(vo: EmpVo) {
  const emp: EmpVo = await getEmpByIdApi(vo.id);
  empDrawerApi.setState({ title: '编辑' });
  empDrawerApi.setData({ action: ACTION.EDIT, emp, callback: reloadGrid });
  empDrawerApi.open();
}

async function delEmp(row: EmpVo) {
  await deleteEmpsApi({ ids: [row.id] });
  await reloadGrid();
}

const treeData = ref<TreeDataNode[]>([]);
const expandedKeys = ref<string[]>([]);

async function expandAll() {
  const allKeys: string[] = [];
  const collectKeys = (nodes: TreeDataNode[]) => {
    nodes.forEach((node) => {
      allKeys.push(node.id);
      if (node.children) {
        collectKeys(node.children);
      }
    });
  };
  collectKeys(treeData.value);
  expandedKeys.value = allKeys;
}

async function onSelect(selectedKeys: Key[]) {
  depIds.value = selectedKeys as string[];
  await gridApi.query();
}

onMounted(async () => {
  treeData.value = await depTreeApi();
  await expandAll();
});
</script>

<template>
  <Page auto-content-height>
    <Flex :vertical="false" class="h-full">
      <div
        class="bg-card rounded-md"
        style="width: 15%; padding-top: 0.3em; margin-right: 0.6em"
      >
        <div class="item w-full">
          <Input placeholder="输入关键字过滤" />
        </div>
        <div v-if="false" class="item w-full">
          <Button class="w-full">全部</Button>
        </div>
        <div class="item w-full">
          <Tree
            :expanded-keys="expandedKeys"
            :field-names="{ title: 'name', key: 'id', children: 'children' }"
            :tree-data="treeData"
            default-expand-all
            @select="(keys) => onSelect(keys)"
          />
        </div>
      </div>
      <div style="width: 85%">
        <Grid>
          <template #toolbar-actions>
            <Search
              :style="{ width: '240px' }"
              allow-clear
              placeholder="姓名、工号、邮箱"
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
          <template #isCorpAdmin="{ row }">
            <div>
              {{
                YesOrNoUseBool[row.isCorpAdmin as keyof typeof YesOrNoUseBool]
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
              @confirm="() => delEmp(row)"
            >
              <Button type="link">删除</Button>
            </Popconfirm>
          </template>
        </Grid>
      </div>
    </Flex>
    <EmpDrawer />
  </Page>
</template>
<style>
.item {
  padding: 0.3em 0.6em 0;
}
</style>
