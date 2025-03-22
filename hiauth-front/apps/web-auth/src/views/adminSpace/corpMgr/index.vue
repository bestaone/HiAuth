<script lang="ts" setup>
import type { CorpVo } from '#/api/core/corp';

import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

import { useVbenDrawer } from '@vben/common-ui';
import { resetAllStores, useAccessStore } from '@vben/stores';

import { Button, Card, List, Popconfirm } from 'ant-design-vue';

import {
  deleteCorpsApi,
  getCorpByIdApi,
  intoCorpSpaceApi,
  listCorpApi,
} from '#/api/core/corp';
import { ACTION } from '#/common/constants';
import { useAuthStore } from '#/store';

import CorpDrawerCom from './corp-drawer.vue';

const router = useRouter();
const accessStore = useAccessStore();
const authStore = useAuthStore();

const corps = ref<CorpVo[]>();

onMounted(async () => {
  await reloadGrid();
});

const [CorpDrawer, corpDrawerApi] = useVbenDrawer({
  connectedComponent: CorpDrawerCom,
});

async function mgrCorp(corp: CorpVo) {
  await intoCorpSpaceApi(corp.id);
  const { accessToken } = accessStore;
  await resetAllStores();
  await authStore.changeSpace(accessToken ?? '', async () => {
    await router.replace(`/?r=${Date.now()}`);
  });
}

async function reloadGrid() {
  corps.value = await listCorpApi();
}

async function addDrawer() {
  corpDrawerApi.setState({ title: '新增' });
  corpDrawerApi.setData({ action: ACTION.ADD, callback: reloadGrid });
  corpDrawerApi.open();
}

async function editDrawer(vo: CorpVo) {
  const corp: CorpVo = await getCorpByIdApi(vo.id);
  corpDrawerApi.setState({ title: '编辑' });
  corpDrawerApi.setData({ action: ACTION.EDIT, corp, callback: reloadGrid });
  corpDrawerApi.open();
}

async function del(row: CorpVo) {
  await deleteCorpsApi({ ids: [row.id] });
  await reloadGrid();
}
</script>

<template>
  <div style="padding: 20px">
    <List :data-source="corps" :grid="{ gutter: 16 }">
      <template #renderItem="{ item }">
        <List.Item row-key="id" style="padding: 0">
          <Card
            :key="item.id"
            :title="item.name"
            hoverable
            style="width: 260px"
          >
            <template #cover>
              <img
                alt="example"
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
              />
            </template>
            <p>企业状态：{{ item.status ? '启用' : '禁用' }}</p>
            <p>部门数量：{{ item.depCount }}个</p>
            <p>用户数量：{{ item.depCount }}个</p>
            <template #actions>
              <Button type="text" @click="mgrCorp(item)">
                <span class="icon-[ant-design--file-search-outlined]"></span>
              </Button>
              <Button type="text" @click="editDrawer(item)">
                <span class="icon-[ant-design--edit-twotone]"></span>
              </Button>
              <Popconfirm
                :description="`确定要删除【${item.name}】?`"
                cancel-text="否"
                ok-text="是"
                title="删除"
                @confirm="() => del(item)"
              >
                <Button type="text">
                  <span class="icon-[ant-design--delete-outlined]"></span>
                </Button>
              </Popconfirm>
            </template>
          </Card>
        </List.Item>
      </template>
    </List>
    <Button @click="addDrawer">创建企业</Button>
    <CorpDrawer />
  </div>
</template>
