<script lang="ts" setup>
import type { Key } from 'ant-design-vue/es/_util/type';
import type { CheckInfo } from 'ant-design-vue/es/vc-tree/props';
import type { TreeDataNode } from 'ant-design-vue/lib/vc-tree-select/interface';

import type { AppVo } from '#/api/core/app';

import { ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { Tree, TreeSelect } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { limitHaveAppApi } from '#/api/core/appClient';
import {
  appResourceTreeApi,
  findAppResourceIdsByRoleAndAppApi,
} from '#/api/core/appResource';
import { authRoleApi } from '#/api/core/role';

defineOptions({ name: 'AuthFormModel' });

const apps = ref<AppVo[]>([]);
const roleIdRef = ref<string>('');
const appIdRef = ref<string>('');
const checkedKeysRef = ref<string[]>([]);
const arTreeData = ref<TreeDataNode[]>([]);
let gridCallback = () => {};

const handleAppSelectOnChange = async (appId: string) => {
  appIdRef.value = appId;
  arTreeData.value = await appResourceTreeApi(appId);
  checkedKeysRef.value = await findAppResourceIdsByRoleAndAppApi({
    appId,
    roleId: roleIdRef.value,
  });
};

const handleTreeOnCheck = async (
  checkedKeys: Key[] | { checked: Key[]; halfChecked: Key[] },
  info: CheckInfo,
) => {
  const { checked, node } = info;
  const keys = checkedKeys as { checked: string[]; halfChecked: string[] };
  checkedKeysRef.value = [...keys.checked, ...keys.halfChecked];
  if (checked) {
    node.children?.forEach((child) => {
      checkedKeysRef.value.push(child.id);
    });
  }
};

const resetForm = async () => {
  apps.value = [];
  roleIdRef.value = '';
  appIdRef.value = '';
  checkedKeysRef.value = [];
  arTreeData.value = [];
};

const [Form, formApi] = useVbenForm({
  handleSubmit: onFormSubmit,
  showDefaultActions: false,
});

const [Modal, modalApi] = useVbenModal({
  onCancel() {
    modalApi.close();
  },
  onConfirm: async () => {
    await formApi.validateAndSubmitForm();
  },
  onOpenChange: async (isOpen: boolean) => {
    if (isOpen) {
      const { roleId, callback } = modalApi.getData<Record<string, any>>();
      await resetForm();
      roleIdRef.value = roleId;
      gridCallback = callback;
      const param = { offset: 0, limit: 100 };
      apps.value = await limitHaveAppApi(param);
    }
  },
  fullscreenButton: false,
  title: '应用授权',
});
async function onFormSubmit() {
  await authRoleApi({
    roleId: roleIdRef.value,
    appId: appIdRef.value,
    appResourceIds: checkedKeysRef.value,
  });
  await modalApi.close();
  await gridCallback();
}
</script>
<template>
  <Modal class="h-[700px] w-[450px]">
    <Form>
      <input v-model="roleIdRef" name="roleId" type="hidden" />
      <TreeSelect
        :field-names="{ label: 'name', value: 'id' }"
        :tree-data="apps"
        class="w-full"
        name="appId"
        @change="handleAppSelectOnChange"
      />
      <Tree
        :checked-keys="checkedKeysRef"
        :field-names="{ title: 'name', key: 'id', children: 'children' }"
        :tree-data="arTreeData"
        check-strictly
        checkable
        class="app-resource-tree"
        name="appResourceIds"
        @check="handleTreeOnCheck"
      />
    </Form>
  </Modal>
</template>
<style>
.app-resource-tree {
  height: 530px;
  margin-top: 10px;
  overflow: auto;
  border: solid 1px #e5e7eb;
}
</style>
