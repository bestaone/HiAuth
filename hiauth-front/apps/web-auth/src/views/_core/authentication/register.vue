<script lang="ts" setup>
import type { VbenFormSchema } from '@vben/common-ui';
import type { Recordable } from '@vben/types';

import { computed, h, ref, useTemplateRef } from 'vue';
import { useRouter } from 'vue-router';

import { AuthenticationRegister, z } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { message, notification } from 'ant-design-vue';

import { getSmsCodeApi, registerApi } from '#/api';

defineOptions({ name: 'Register' });

const loading = ref(false);
const CODE_LENGTH = 6;
const registerRef =
  useTemplateRef<InstanceType<typeof AuthenticationRegister>>('registerRef');

const router = useRouter();

const formSchema = computed((): VbenFormSchema[] => {
  return [
    {
      component: 'VbenInput',
      componentProps: {
        placeholder: $t('authentication.mobile'),
      },
      fieldName: 'phoneNum',
      defaultValue: '',
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
          return countdown > 0
            ? $t('authentication.sendText', [countdown])
            : $t('authentication.sendCode');
        },
        placeholder: $t('authentication.code'),
        handleSendCode: async () => {
          const formApi = registerRef.value?.getFormApi();
          if (!formApi) {
            throw new Error('formApi is not ready');
          }
          await formApi.validateField('phoneNum');
          const isPhoneReady = await formApi.isFieldValid('phoneNum');
          if (!isPhoneReady) {
            throw new Error('Phone number is not Ready');
          }
          const { phoneNum } = await formApi.getValues();
          const smsCode = await getSmsCodeApi({
            type: 1,
            requestId: phoneNum,
            phoneNum,
          });
          message.success(`短信验证码:${smsCode}`);
        },
      },
      fieldName: 'smsCode',
      label: $t('authentication.code'),
      rules: z.string().length(CODE_LENGTH, {
        message: $t('authentication.codeTip', [CODE_LENGTH]),
      }),
    },
    {
      component: 'VbenInput',
      componentProps: {
        placeholder: '租户名称',
      },
      fieldName: 'corpName',
      defaultValue: '',
      label: '租户名称',
      rules: z
        .string()
        .min(5, { message: '长度应在5至30个字符内' })
        .max(30, { message: '长度应在5至30个字符内' }),
    },
    {
      component: 'VbenInput',
      componentProps: {
        placeholder: '用户名',
      },
      fieldName: 'username',
      defaultValue: '',
      label: $t('authentication.username'),
      rules: z.string().min(1, { message: $t('authentication.usernameTip') }),
    },
    {
      component: 'VbenInputPassword',
      componentProps: {
        passwordStrength: true,
        placeholder: $t('authentication.password'),
      },
      fieldName: 'password',
      defaultValue: '',
      label: $t('authentication.password'),
      renderComponentContent() {
        return {
          strengthText: () => $t('authentication.passwordStrength'),
        };
      },
      rules: z.string().min(1, { message: $t('authentication.passwordTip') }),
    },
    {
      component: 'VbenInputPassword',
      componentProps: {
        placeholder: $t('authentication.confirmPassword'),
      },
      dependencies: {
        rules(values) {
          const { password } = values;
          return z
            .string({ required_error: $t('authentication.passwordTip') })
            .min(1, { message: $t('authentication.passwordTip') })
            .refine((value) => value === password, {
              message: $t('authentication.confirmPasswordTip'),
            });
        },
        triggerFields: ['password'],
      },
      fieldName: 'confirmPassword',
      defaultValue: '',
      label: $t('authentication.confirmPassword'),
    },
    {
      component: 'VbenCheckbox',
      fieldName: 'agreePolicy',
      renderComponentContent: () => ({
        default: () =>
          h('span', [
            $t('authentication.agree'),
            h(
              'a',
              {
                class: 'vben-link ml-1 ',
                href: '',
              },
              `${$t('authentication.privacyPolicy')} & ${$t('authentication.terms')}`,
            ),
          ]),
      }),
      rules: z.boolean().refine((value) => !!value, {
        message: $t('authentication.agreeTip'),
      }),
    },
  ];
});

async function handleSubmit(value: Recordable<any>) {
  loading.value = true;
  const { corpName, username, password, phoneNum, smsCode } = value;
  await registerApi({ corpName, username, password, phoneNum, smsCode });
  notification.success({
    message: '注册成功',
    description: '账号注册成功，请登录!',
    duration: 5,
    onClick: () => {
      router.push('/auth/login');
    },
    onClose: () => {
      router.push('/auth/login');
    },
  });
}
</script>

<template>
  <AuthenticationRegister
    ref="registerRef"
    :form-schema="formSchema"
    :loading="loading"
    @submit="handleSubmit"
  >
    <template #title> 注册账号 </template>
  </AuthenticationRegister>
</template>
