import { baseRequestClient, requestClient } from '#/api/request';

export namespace AuthApi {
  /** 登录接口参数 */
  export interface LoginParams {
    password?: string;
    username?: string;
  }

  export interface SmsCodeDto {
    type: number;
    requestId: string;
    phoneNum: string;
  }

  export interface PhoneNumLoginDto {
    requestId: string;
    phoneNum: string;
    smsCode: string;
  }

  /** 登录接口返回值 */
  export interface LoginResult {
    accessToken: string;
  }

  export interface RefreshTokenResult {
    data: string;
    status: number;
  }
}

export interface RegisterDto {
  corpName: string;
  username: string;
  password: string;
  phoneNum: string;
  smsCode: string;
}

/**
 * 登录
 */
export async function loginApi(data: AuthApi.LoginParams) {
  return requestClient.post<AuthApi.LoginResult>('/unpapi/login/account', data);
}

export async function phoneNumLoginApi(data: AuthApi.PhoneNumLoginDto) {
  return requestClient.post<AuthApi.LoginResult>(
    '/unpapi/login/phoneNum',
    data,
  );
}

export async function getSmsCodeApi(data: AuthApi.SmsCodeDto) {
  return requestClient.post<boolean>('/unpapi/captcha/smsCode', data);
}

/**
 * 刷新accessToken
 */
export async function refreshTokenApi() {
  return baseRequestClient.post<AuthApi.RefreshTokenResult>('/auth/refresh', {
    withCredentials: true,
  });
}

/**
 * 退出登录
 */
export async function logoutApi() {
  return baseRequestClient.get('/api/logout', {
    withCredentials: true,
  });
}

/**
 * 获取用户权限码
 */
export async function getAccessCodesApi() {
  return requestClient.post<string[]>('/api/common/codes');
}

export async function registerApi(data: RegisterDto) {
  return requestClient.post<boolean>('/unpapi/register', data);
}
