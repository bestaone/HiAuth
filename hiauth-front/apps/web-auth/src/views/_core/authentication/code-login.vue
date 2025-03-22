<script lang="ts" setup>
import type { VbenFormSchema } from '@vben/common-ui';

import { computed, ref, useTemplateRef } from 'vue';

import { AuthenticationCodeLogin, z } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { message } from 'ant-design-vue';

import { getSmsCodeApi } from '#/api';
import { useAuthStore } from '#/store';

defineOptions({ name: 'CodeLogin' });

const authStore = useAuthStore();
const loading = ref(false);
const CODE_LENGTH = 6;
const loginRef =
  useTemplateRef<InstanceType<typeof AuthenticationCodeLogin>>('loginRef');

const formSchema = computed((): VbenFormSchema[] => {
  return [
    {
      component: 'VbenInput',
      componentProps: {
        placeholder: $t('authentication.mobile'),
      },
      fieldName: 'phoneNumber',
      defaultValue: '13712345678',
      label: $t('authentication.mobile'),
      rules: z
        .string()
        .min(1, { message: $t('authentication.mobileTip') })
        .refine((v) => /^\d{11}$/.test(v), {
          message: $t('authentication.mobileErrortip'),
        }),
    },
    {
      component: 'VbenPinInput',
      componentProps: {
        codeLength: CODE_LENGTH,
        createText: (countdown: number) => {
          const text =
            countdown > 0
              ? $t('authentication.sendText', [countdown])
              : $t('authentication.sendCode');
          return text;
        },
        placeholder: $t('authentication.code'),
        handleSendCode: async () => {
          // 模拟发送验证码
          // Simulate sending verification code
          loading.value = true;
          const formApi = loginRef.value?.getFormApi();
          if (!formApi) {
            loading.value = false;
            throw new Error('formApi is not ready');
          }
          await formApi.validateField('phoneNumber');
          const isPhoneReady = await formApi.isFieldValid('phoneNumber');
          if (!isPhoneReady) {
            loading.value = false;
            throw new Error('Phone number is not Ready');
          }
          const { phoneNumber } = await formApi.getValues();
          await getSmsCodeApi({
            type: 1,
            requestId: phoneNumber,
            phoneNum: phoneNumber,
          });
          loading.value = false;
          message.success(`短信验证码已发送`);
        },
      },
      fieldName: 'code',
      label: $t('authentication.code'),
      rules: z.string().length(CODE_LENGTH, {
        message: $t('authentication.codeTip', [CODE_LENGTH]),
      }),
    },
  ];
});
</script>
<template>
  <AuthenticationCodeLogin
    ref="loginRef"
    :form-schema="formSchema"
    :loading="authStore.loginLoading"
    @submit="authStore.smsCodeLogin"
  />
</template>
