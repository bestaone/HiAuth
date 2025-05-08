import type { Recordable, UserInfo } from '@vben/types';

import { ref } from 'vue';
import { useRouter } from 'vue-router';

import { LOGIN_PATH } from '@vben/constants';
import { preferences } from '@vben/preferences';
import { resetAllStores, useAccessStore, useUserStore } from '@vben/stores';

import { notification } from 'ant-design-vue';
import { defineStore } from 'pinia';

import {
  getAccessCodesApi,
  getUserInfoApi,
  loginApi,
  logoutApi,
  phoneNumLoginApi,
} from '#/api';
import { updateContext } from '#/common';
import { ROLE_CORP_ADMIN } from '#/common/constants';
import { $t } from '#/locales';
import { useContentStore } from '#/store/content';
import { encrypt } from '#/utils/rsa';

const apiUrl = import.meta.env.VITE_GLOB_API_URL;

export const useAuthStore = defineStore('auth', () => {
  const accessStore = useAccessStore();
  const userStore = useUserStore();
  const router = useRouter();
  const contentStore = useContentStore();

  const loginLoading = ref(false);

  /**
   * 异步处理登录操作
   * Asynchronously handle the login process
   * @param params 登录表单数据
   * @param onSuccess
   */
  async function authLogin(
    params: Recordable<any>,
    onSuccess?: () => Promise<void> | void,
  ) {
    const ePwd =
      contentStore.encryptType === 'RSA'
        ? encrypt(contentStore.publicKey, params.password)
        : params.password;
    const { accessToken } = await loginApi({ ...params, password: ePwd });
    return changeSpace(accessToken, onSuccess);
  }

  async function smsCodeLogin(
    params: Recordable<any>,
    onSuccess?: () => Promise<void> | void,
  ) {
    const { accessToken } = await phoneNumLoginApi({
      requestId: params.phoneNumber,
      phoneNum: params.phoneNumber,
      smsCode: params.code,
    });
    return changeSpace(accessToken, onSuccess);
  }

  async function changeSpace(
    accessToken: string,
    onSuccess?: () => Promise<void> | void,
  ) {
    // 异步处理用户登录操作并获取 accessToken
    let userInfo: null | UserInfo = null;
    try {
      loginLoading.value = true;

      // 如果成功获取到 accessToken
      if (accessToken) {
        accessStore.setAccessToken(accessToken);

        // 获取用户信息并存储到 accessStore 中
        const [fetchUserInfoResult, accessCodes] = await Promise.all([
          fetchUserInfo(),
          getAccessCodesApi(),
        ]);

        userInfo = fetchUserInfoResult;
        userStore.setUserInfo(userInfo);
        accessStore.setAccessCodes(accessCodes);

        const { roles } = userInfo;
        const isCorpSpace: boolean =
          roles !== undefined &&
          roles.some((role) => {
            return ROLE_CORP_ADMIN.includes(role);
          });
        updateContext({ app: { showGoAdminSpaceBut: isCorpSpace } });

        if (accessStore.loginExpired) {
          accessStore.setLoginExpired(false);
        } else {
          onSuccess
            ? await onSuccess?.()
            : await router.push(
              userInfo.homePath || preferences.app.defaultHomePath,
            );
        }

        if (userInfo?.realName) {
          notification.success({
            description: `${$t('authentication.loginSuccessDesc')}:${userInfo?.realName}`,
            duration: 3,
            message: $t('authentication.loginSuccess'),
          });
        }
      }
    } finally {
      loginLoading.value = false;
    }

    return { userInfo };
  }

  async function logout(redirect: boolean = true) {
    try {
      await logoutApi();
    } catch {
      // 不做任何处理
    }
    resetAllStores();
    accessStore.setLoginExpired(false);

    // 回登录页带上当前路由地址
    await router.replace({
      path: LOGIN_PATH,
      query: redirect
        ? {
            redirect: encodeURIComponent(router.currentRoute.value.fullPath),
          }
        : {},
    });
  }

  async function fetchUserInfo() {
    const userInfo: UserInfo = await getUserInfoApi();
    userStore.setUserInfo(userInfo);
    userInfo.avatar = apiUrl + userInfo.avatar;
    return userInfo;
  }

  function $reset() {
    loginLoading.value = false;
  }

  return {
    $reset,
    authLogin,
    smsCodeLogin,
    changeSpace,
    fetchUserInfo,
    loginLoading,
    logout,
  };
});
