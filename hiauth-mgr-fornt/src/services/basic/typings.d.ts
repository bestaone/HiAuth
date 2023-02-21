// @ts-ignore
/* eslint-disable */
declare namespace API {

  type BasicApiResponse<T> = {
    code: number;
    data?: T;
    message?: string;
  };

  export type Page<T> = {
    total: number;
    pages: number;
    current: number;
    records: T[];
  };

  type BasicLoginParams = {
    username?: string;
    password?: string;
    autoLogin?: boolean;
    type?: string;
  };

  type BasicLoginResult = {
    accessToken: string;
    refreshToken: string;
    expire: number;
    userId?: number;
  };

  // 结构需要调整为登陆数据
  type LoginState = {
    status?: string;
    type?: string;
    currentAuthority?: string;
  };

  type Token = {
    accessToken: string;
    refreshToken: string;
  };

  type CurrentUserInfo = {
    nickname?: string;
    avatar?: string;
    userId?: number;
    // email?: string;
    // signature?: string;
    // title?: string;
    // group?: string;
    // tags?: { key?: string; label?: string }[];
    // notifyCount?: number;
    unreadCount?: number;
    // country?: string;
    // access?: string;
    // geographic?: {
    //   province?: { label?: string; key?: string };
    //   city?: { label?: string; key?: string };
    // };
    // address?: string;
    // phone?: string;
  };

  type NoticeType = 'notification' | 'message' | 'event';

  type Notice = {
    id?: string;
    extra?: string;
    key?: string;
    read?: boolean;
    avatar?: string;
    title?: string;
    status?: string;
    datetime?: string;
    description?: string;
    type?: NoticeType;
  };

}
