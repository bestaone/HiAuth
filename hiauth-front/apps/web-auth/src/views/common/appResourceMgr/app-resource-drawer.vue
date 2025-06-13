<script lang="ts" setup>
import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useVbenForm, z } from '#/adapter/form';
import {
  appResourceTreeApi,
  AppResourceTypeOpt,
  createAppResourceApi,
  updateAppResourceApi,
} from '#/api/core/appResource';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'AppResourceDrawer' });

const drawerAction = ref<ACTION>(ACTION.VIEW);
const appIdRef = ref<string>('');

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
        api: getArTree,
        allowClear: true,
        childrenField: 'children',
        labelField: 'name',
        valueField: 'id',
      },
      fieldName: 'pid',
      label: '父权限：',
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
      component: 'Select',
      componentProps: {
        options: AppResourceTypeOpt,
        placeholder: '请选择',
      },
      fieldName: 'type',
      label: '类型：',
      defaultValue: 1,
      rules: z.number({ required_error: '请选择类型' }),
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
        .min(2, { message: '长度应在2至50个字符内' })
        .max(50, { message: '长度应在2至50个字符内' }),
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'url',
      label: '页面地址：',
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'api',
      label: '接口地址：',
    },
    {
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入',
        rows: 5,
        showCount: true,
        maxlength: 200,
      },
      fieldName: 'extend',
      label: '扩展：',
    },
    {
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入',
        rows: 3,
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
      formApi.resetForm();
      const { appId, action, appResource, callback } =
        drawerApi.getData<Record<string, any>>();
      appIdRef.value = appId;
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (appResource) {
        formApi.setValues(appResource);
      }
    }
  },
  title: '应用抽屉',
});

async function getArTree() {
  return appResourceTreeApi(appIdRef.value);
}

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateAppResourceApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createAppResourceApi({ appId: appIdRef.value, ...values });
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
