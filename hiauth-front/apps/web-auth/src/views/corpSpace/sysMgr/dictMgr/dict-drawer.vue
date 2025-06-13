<script lang="ts" setup>
import { h, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useDebounceFn } from '@vueuse/core';
import { Spin } from 'ant-design-vue';

import { useVbenForm, z } from '#/adapter/form';
import { createDictApi, findDictApi, updateDictApi } from '#/api/core/dict';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'DictDrawer' });

const dictKeyword = ref('');
const drawerAction = ref<ACTION>(ACTION.VIEW);
let gridCallback = () => {};
const fetching = ref(false);
const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
  },
  handleSubmit: onSubmit,
  schema: [
    {
      component: 'Input',
      componentProps: {
        type: 'hidden',
      },
      fieldName: 'id',
    },
    {
      component: 'ApiSelect',
      // 对应组件的参数
      componentProps: () => {
        return {
          api: findDictApi,
          labelField: 'name',
          valueField: 'code',
          allowClear: true,
          // 禁止本地过滤
          filterOption: false,
          // 如果正在获取数据，使用插槽显示一个loading
          notFoundContent: fetching.value ? undefined : null,
          // 搜索词变化时记录下来， 使用useDebounceFn防抖。
          onSearch: useDebounceFn((value: string) => {
            dictKeyword.value = value;
          }, 300),
          // 远程搜索参数。当搜索词变化时，params也会更新
          params: { keyword: dictKeyword.value },
          showSearch: true,
        };
      },
      // defaultValue: { value: 'product_sell_unit'},
      fieldName: 'pcode',
      label: '父节点：',
      renderComponentContent: () => {
        return {
          notFoundContent: fetching.value ? h(Spin) : undefined,
        };
      },
    },

    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'name',
      label: '名称：',
      rules: z
        .string()
        .min(1, { message: '长度应在1至20个字符内' })
        .max(20, { message: '长度应在1至20个字符内' }),
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'code',
      label: '编码：',
      rules: z
        .string()
        .min(1, { message: '长度应在1至20个字符内' })
        .max(50, { message: '长度应在1至20个字符内' }),
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'value',
      label: '值：',
      rules: z
        .string()
        .min(1, { message: '长度应在1至100个字符内' })
        .max(100, { message: '长度应在1至100个字符内' }),
    },
  ],
  showDefaultActions: false,
});

const [Drawer, drawerApi] = useVbenDrawer({
  onCancel() {
    drawerApi.close();
  },
  onConfirm: async () => {
    await formApi.submitForm();
  },
  onOpenChange(isOpen: boolean) {
    if (isOpen) {
      formApi.resetForm();
      const { action, dict, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (dict) {
        formApi.setValues(dict);
      }
    }
  },
  title: '字典抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateDictApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createDictApi({ ...values });
  }
  await drawerApi.close();
  await gridCallback();
}
</script>
<template>
  <Drawer>
    <Form />
  </Drawer>
</template>
