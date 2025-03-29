<script lang="ts" setup>
import type { FileType } from 'ant-design-vue/es/upload/interface';

import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { Image, message, Upload } from 'ant-design-vue';

import { useVbenForm, z } from '#/adapter/form';
import { createAppApi, updateAppApi } from '#/api/core/app';
import { uploadImgApi } from '#/api/core/common';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'AppDrawer' });

const apiUrl = import.meta.env.VITE_GLOB_API_URL;
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
      component: 'Upload',
      componentProps: {},
      fieldName: 'icon',
      label: '图标：',
      rules: 'required',
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
      const { action, app, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (app) {
        formApi.setValues(app);
      }
    }
  },
  title: '应用抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateAppApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createAppApi({ ...values });
  }
  await drawerApi.close();
  await gridCallback();
}

const beforeUpload = async (file: FileType) => {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isJpgOrPng) {
    message.error('You can only upload JPG/PNG file!');
  }
  const isLt500K = file.size / 1024 < 500;
  if (!isLt500K) {
    message.error('Image must smaller than 500KB!');
  }
  return isJpgOrPng && isLt500K;
};

const customRequest = async (options: any) => {
  const { file } = options;
  const fileUrl = await uploadImgApi(file);
  await formApi.setValues({ icon: fileUrl });
};
</script>

<template>
  <Drawer>
    <Form>
      <template #icon="{ value, disabled }">
        <Upload
          :before-upload="beforeUpload"
          :custom-request="customRequest"
          :disabled="disabled"
          :show-upload-list="false"
          accept=".png, .jpg, .jpeg"
          list-type="picture-card"
        >
          <Image
            :preview="disabled ?? false"
            :src="apiUrl + value"
            style="width: 80px; height: 80px"
          />
        </Upload>
      </template>
    </Form>
  </Drawer>
</template>
