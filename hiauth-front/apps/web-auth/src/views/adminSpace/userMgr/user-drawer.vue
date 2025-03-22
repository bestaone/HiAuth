<script lang="ts" setup>
import type { FileType } from 'ant-design-vue/es/upload/interface';

import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { Image, message, Upload } from 'ant-design-vue';

import { useVbenForm, z } from '#/adapter/form';
import { createUserApi, GenderTypeOpt, updateUserApi } from '#/api';
import {
  EnableStatusUseNumOpt,
  uploadImgApi,
  YesOrNoUseNumOpt,
} from '#/api/core/common';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'UserDrawer' });

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
      fieldName: 'avatar',
      label: '头像：',
      rules: 'required',
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'name',
      label: '姓名：',
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
      fieldName: 'username',
      label: '用户名：',
      rules: z
        .string()
        .min(5, { message: '长度应在5至20个字符内' })
        .max(20, { message: '长度应在5至20个字符内' }),
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'phoneNum',
      label: '手机号：',
      rules: z.string().regex(/^1[3-9]\d{9}$/, '请填写正确的手机号'),
    },
    {
      component: 'Select',
      componentProps: {
        options: GenderTypeOpt,
        placeholder: '请选择',
      },
      fieldName: 'gender',
      label: '性别：',
      rules: z.number({ required_error: '请选择性别' }),
    },
    {
      component: 'Select',
      componentProps: {
        options: YesOrNoUseNumOpt,
        placeholder: '请选择',
      },
      fieldName: 'isSysAdmin',
      label: '管理员：',
      rules: z.number({ required_error: '请选择是否管理员' }),
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
      const { action, user, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (user) {
        formApi.setValues(user);
      }
    }
  },
  title: '用户抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateUserApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createUserApi({ ...values });
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
  await formApi.setValues({ avatar: fileUrl });
};
</script>
<template>
  <Drawer>
    <Form>
      <template #avatar="{ value, disabled }">
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
