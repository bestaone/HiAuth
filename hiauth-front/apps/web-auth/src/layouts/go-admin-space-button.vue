<script setup lang="ts">
import { useRouter } from 'vue-router';

import { resetAllStores, useAccessStore } from '@vben/stores';

import { Button } from 'ant-design-vue';

import { intoAdminSpaceApi } from '#/api/core/corp';
import { useAuthStore } from '#/store';

const router = useRouter();
const accessStore = useAccessStore();
const authStore = useAuthStore();

async function onClick() {
  await intoAdminSpaceApi();
  const { accessToken } = accessStore;
  resetAllStores();
  await authStore.changeSpace(accessToken ?? '', async () => {
    await router.replace(`/?r=${Date.now()}`);
  });
}
</script>

<template>
  <Button
    class="mr-1 inline-flex w-8 items-center justify-center px-1"
    type="text"
    @click="onClick()"
  >
    <span class="icon-[ant-design--swap-outlined]"></span>
  </Button>
</template>
