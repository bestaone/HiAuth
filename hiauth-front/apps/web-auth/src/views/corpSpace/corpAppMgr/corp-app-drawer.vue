<script lang="ts" setup>
import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useVbenForm, z } from '#/adapter/form';
import { updateCorpAppApi } from '#/api/core/corpApp';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'CorpAppDrawer' });

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
        disabled: true,
      },
      fieldName: 'name',
      label: '名称：',
      rules: z
        .string()
        .min(5, { message: '长度应在5至30个字符内' })
        .max(30, { message: '长度应在5至30个字符内' }),
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'clientId',
      label: 'ClientID：',
      rules: z
        .string()
        .min(5, { message: '长度应在5至20个字符内' })
        .max(50, { message: '长度应在5至50个字符内' }),
    },
    {
      component: 'Button',
      componentProps: {},
      fieldName: 'clientSecret',
      label: 'ClientSecret：',
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'scopes',
      label: 'Scopes：',
      rules: z
        .string()
        .min(5, { message: '长度应在5至30个字符内' })
        .max(30, { message: '长度应在5至30个字符内' }),
    },
    {
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入',
        min: 1,
        max: 10_000,
      },
      fieldName: 'accessTokenTimeToLive',
      label: 'Expire(H)：',
      rules: z
        .number()
        .min(1, { message: '长度应在1至10000个字符内' })
        .max(10_000, { message: '长度应在1至10000个字符内' }),
    },
    {
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入',
        rows: 5,
        maxlength: 500,
        showCount: true,
      },
      fieldName: 'redirectUris',
      label: '回调地址：',
      rules: z
        .string()
        .min(5, { message: '长度应在5至500个字符内' })
        .max(500, { message: '长度应在5至500个字符内' }),
    },
    {
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入',
        rows: 5,
        maxlength: 200,
        showCount: true,
        disabled: true,
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
      const { action, corpApp, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (corpApp) {
        formApi.setValues(corpApp);
      }
    }
  },
  title: '应用抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateCorpAppApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    // await createCorpAppApi({ ...values });
  }
  await drawerApi.close();
  await gridCallback();
}
</script>
<template>
  <Drawer>
    <Form>
      <template #clientSecret="slotProps">
        <Button
          v-bind="slotProps"
          style="
            padding: 5px;
            font-size: 0.8rem;
            border: solid 1px #dcdfe6;
            border-radius: 5px;
          "
        >
          重置
        </Button>
      </template>
    </Form>
  </Drawer>
</template>
