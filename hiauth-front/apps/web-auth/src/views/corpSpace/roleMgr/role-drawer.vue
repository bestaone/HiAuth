<script lang="ts" setup>
import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useVbenForm, z } from '#/adapter/form';
import { EnableStatusUseBoolOpt } from '#/api/core/common';
import { createRoleApi, updateRoleApi } from '#/api/core/role';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'RoleDrawer' });

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
        .min(2, { message: '长度应在2至20个字符内' })
        .max(20, { message: '长度应在2至20个字符内' }),
    },
    {
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入',
        rows: 5,
        showCount: true,
        maxlength: 200,
      },
      fieldName: 'remark',
      label: '备注：',
      rules: z.string().max(200, { message: '长度应在200个字符内' }),
    },
    {
      component: 'Select',
      componentProps: {
        options: EnableStatusUseBoolOpt,
        placeholder: '请选择',
      },
      fieldName: 'isEnable',
      label: '状态：',
      rules: z.boolean({ required_error: '请选择状态' }),
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
      const { action, role, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (role) {
        formApi.setValues(role);
      }
    }
  },
  title: '应用抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateRoleApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createRoleApi({ ...values });
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
