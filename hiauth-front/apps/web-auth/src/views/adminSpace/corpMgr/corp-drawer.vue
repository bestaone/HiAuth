<script lang="ts" setup>
import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useVbenForm, z } from '#/adapter/form';
import { EnableStatusUseNumOpt } from '#/api/core/common';
import { createCorpApi, updateCorpApi } from '#/api/core/corp';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'CorpDrawer' });

const drawerAction = ref<ACTION>(ACTION.VIEW);
let gridCallback = () => {};

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
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'name',
      label: '名称：',
      rules: z
        .string()
        .min(5, { message: '长度应在5至30个字符内' })
        .max(30, { message: '长度应在5至30个字符内' }),
    },
    {
      component: 'Select',
      componentProps: {
        options: EnableStatusUseNumOpt,
        placeholder: '请选择',
      },
      fieldName: 'status',
      label: '状态：',
      rules: z.number({ required_error: '请选择状态' }),
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
      const { action, corp, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (corp) {
        formApi.setValues(corp);
      }
    }
  },
  title: '应用抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateCorpApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createCorpApi({ ...values });
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
