<script lang="ts" setup>
import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useVbenForm, z } from '#/adapter/form';
import { updateAppClientApi } from '#/api/core/appClient';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'AppClientDrawer' });

const isResetClientSecret = ref<boolean>(false);
const clientSecret = ref<string>();
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
      isResetClientSecret.value = false;
      const { action, appClient, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (appClient) {
        formApi.setValues(appClient);
      }
    }
  },
  title: '应用抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateAppClientApi({
      id: values.id,
      ...values,
      clientSecret: clientSecret.value,
    });
  } else if (drawerAction.value === ACTION.ADD) {
    // await createAppClientApi({ ...values });
  }
  await drawerApi.close();
  await gridCallback();
}

function resetClientSecret() {
  clientSecret.value = '';
  isResetClientSecret.value = true;
}
</script>
<template>
  <Drawer>
    <Form>
      <template #clientSecret="slotProps">
        <input
          name="clientSecret"
          type="text"
          v-if="isResetClientSecret"
          v-model="clientSecret"
          class="ant-input css-dev-only-do-not-override-14589v w-full"
          minlength="5"
          maxlength="20"
        />
        <Button
          @click="resetClientSecret"
          v-if="!isResetClientSecret"
          v-bind="slotProps"
          style="
            height: 32px;
            font-size: 0.8rem;
            border: solid 1px #dcdfe6;
            border-radius: 8px;
          "
        >
          重置
        </Button>
      </template>
    </Form>
  </Drawer>
</template>
