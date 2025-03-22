<script lang="ts" setup>
import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useVbenForm, z } from '#/adapter/form';
import { EnableStatusUseNumOpt } from '#/api/core/common';
import { createDepApi, depTreeApi, updateDepApi } from '#/api/core/dep';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'DepDrawer' });

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
      component: 'ApiTreeSelect',
      componentProps: {
        api: depTreeApi,
        allowClear: true,
        childrenField: 'children',
        labelField: 'name',
        valueField: 'id',
      },
      fieldName: 'pid',
      label: '父部门：',
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'no',
      label: '编码：',
      rules: z
        .string()
        .min(2, { message: '长度应在2至10个字符内' })
        .max(10, { message: '长度应在2至10个字符内' }),
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
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入',
        min: 1,
        max: 9999,
      },
      defaultValue: 1,
      fieldName: 'sort',
      label: '排序：',
      rules: z
        .number()
        .gte(1, { message: '大小应在1至9999之间' })
        .lte(9999, { message: '大小应在1至9999之间' }),
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
      const { action, dep, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (dep) {
        formApi.setValues(dep);
      }
    }
  },
  title: '应用抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateDepApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createDepApi({ ...values });
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
