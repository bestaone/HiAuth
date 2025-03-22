<script lang="ts" setup>
import type {VbenFormSchema} from '@vben/common-ui';
import {AuthenticationLogin, SliderCaptcha, z} from '@vben/common-ui';

import {computed, markRaw, onBeforeMount, ref} from 'vue';
import {$t} from '@vben/locales';

import {getMetadataApi} from '#/api/core/common';
import {useAuthStore} from '#/store';
import {useContentStore} from '#/store/content';

defineOptions({ name: 'Login' });

const authStore = useAuthStore();
const contentStore = useContentStore();
const up = ref<string>('xxx');
const pp = ref<string>('sww');

onBeforeMount(async () => {
  const { publicKey, encryptType, usernamePlaceholder, passwordPlaceholder } = await getMetadataApi();
  up.value = usernamePlaceholder;
  pp.value = passwordPlaceholder;
  contentStore.setEncrypt(publicKey, encryptType);
});

const formSchema = computed((): VbenFormSchema[] => {
  return [
    {
      component: 'VbenInput',
      componentProps: {
        placeholder: up.value,
      },
      fieldName: 'username',
      label: $t('authentication.username'),
      defaultValue: '',
      rules: z.string().min(1, { message: $t('authentication.usernameTip') }),
    },
    {
      component: 'VbenInputPassword',
      componentProps: {
        placeholder: pp.value,
      },
      fieldName: 'password',
      label: $t('authentication.password'),
      defaultValue: '',
      rules: z.string().min(1, { message: $t('authentication.passwordTip') }),
    },
    {
      component: markRaw(SliderCaptcha),
      fieldName: 'captcha',
      rules: z.boolean().refine((value) => value, {
        message: $t('authentication.verifyRequiredTip'),
      }),
    },
  ];
});
</script>

<template>
  <AuthenticationLogin
    title="欢迎使用👋🏻"
    sub-title="请输入您的帐户信息以开始管理您的项目"
    :form-schema="formSchema"
    :loading="authStore.loginLoading"
    @submit="authStore.authLogin"
  />
</template>
