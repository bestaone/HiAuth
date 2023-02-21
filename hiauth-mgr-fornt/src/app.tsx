import type {Settings as LayoutSettings} from '@ant-design/pro-layout';
import {PageLoading} from '@ant-design/pro-layout';
import {Modal, notification} from 'antd';
import type {RequestConfig, RunTimeLayoutConfig} from 'umi';
import {history, Link} from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
import {currentUser as queryCurrentUser, getCurrentPermissions, getCurrentResources} from './services/basic/api';
import {BookOutlined, LinkOutlined} from '@ant-design/icons';
import type {RequestOptionsInit} from 'umi-request';

const isDev = process.env.NODE_ENV === 'development';
const loginPath = '/user/login';

let netErrorModalCVisible = true;

/** 获取用户信息比较慢的时候会展示一个 loading */
export const initialStateConfig = {
  loading: <PageLoading />,
};

/**
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 * */
export async function getInitialState(): Promise<{
  settings?: Partial<LayoutSettings>;
  // zgs CurrentUser -> CurrentUserInfo
  currentUser?: API.CurrentUserInfo;
  fetchUserInfo?: () => Promise<API.CurrentUserInfo | undefined>;
  currentResources?: Set<string>;
  fetchUserResources?: () => Promise<Set<string> | undefined>;
  currentPermisstions?: Set<string> | undefined;
  fetchUserPermissions?: () => Promise<Set<string> | undefined>;
}> {

  const fetchUserInfo = async () => {
    try {
      const resp = await queryCurrentUser();
      // zgs
      return resp.data;
    } catch (error) {
      history.push(loginPath);
    }
    return undefined;
  };

  // 获取当前用户拥有的资源
  const fetchUserResources = async () => {
    try {
      const resp = await getCurrentResources("menuGroup");
      return new Set<string>(resp.data);
    } catch (error) {
      console.log(error);
    }
    return undefined;
  };

  // 获取当前用户拥有的权限
  const fetchUserPermissions = async () => {
    try {
      const resp = await getCurrentPermissions();
      return new Set<string>(resp.data);
    } catch (error) {
      console.log(error);
    }
    return undefined;
  };

  // 如果是登录页面，不执行
  if (history.location.pathname !== loginPath) {
    const currentUser = await fetchUserInfo();
    const currentResources = await fetchUserResources();
    const currentPermisstions = await fetchUserPermissions();
    return {
      settings: {},
      fetchUserInfo,
      fetchUserResources,
      fetchUserPermissions,
      currentUser,
      currentResources,
      currentPermisstions,
    };
  }
  return {
    settings: {},
    fetchUserInfo,
    fetchUserResources,
    fetchUserPermissions,
    currentResources: new Set<string>(),
  };
}

/**
 * 异常处理程序
    200: '服务器成功返回请求的数据。',
    201: '新建或修改数据成功。',
    202: '一个请求已经进入后台排队（异步任务）。',
    204: '删除数据成功。',
    400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
    401: '用户没有权限（令牌、用户名、密码错误）。',
    403: '用户得到授权，但是访问是被禁止的。',
    404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
    405: '请求方法不被允许。',
    406: '请求的格式不可得。',
    410: '请求的资源被永久删除，且不会再得到的。',
    422: '当创建一个对象时，发生一个验证错误。',
    500: '服务器发生错误，请检查服务器。',
    502: '网关错误。',
    503: '服务不可用，服务器暂时过载或维护。',
    504: '网关超时。',
 //-----English
    200: The server successfully returned the requested data. ',
    201: New or modified data is successful. ',
    202: A request has entered the background queue (asynchronous task). ',
    204: Data deleted successfully. ',
    400: 'There was an error in the request sent, and the server did not create or modify data. ',
    401: The user does not have permission (token, username, password error). ',
    403: The user is authorized, but access is forbidden. ',
    404: The request sent was for a record that did not exist. ',
    405: The request method is not allowed. ',
    406: The requested format is not available. ',
    410: 'The requested resource is permanently deleted and will no longer be available. ',
    422: When creating an object, a validation error occurred. ',
    500: An error occurred on the server, please check the server. ',
    502: Gateway error. ',
    503: The service is unavailable. ',
    504: The gateway timed out. ',
 * @see https://beta-pro.ant.design/docs/request-cn
 */

// zgs
const authHeaderInterceptor = (url: string, options: RequestOptionsInit) => {
  const authHeader = { Authorization: `Bearer ${localStorage.getItem('accessToken')}` };
  const request = {
    url: `${url}`,
    method: options.method,
    data: options.data,
    params: options.params,
    headers: authHeader
  }
  console.log(`REQUEST=`, request)
  return {
    url: `${url}`,
    options: { ...options, interceptors: true, headers: authHeader },
  };
};

// zgs
const responseInterceptor = async (response: Response, options: RequestOptionsInit) => {
  const basicResponse = await response.clone().json()
  const resp = {
    url: `${options.url}`,
    body: basicResponse
  }
  console.log(`RESPONSE=`, resp)
  const {code} = basicResponse;
  if(code === 40001){
    const { location } = history;
    if (location.pathname !== loginPath && netErrorModalCVisible) {
      netErrorModalCVisible = false;
      Modal.error({
        title: '登陆超时',
        content: '您的登陆时间已超时，请重新登陆！',
        okText: '确认',
        onOk: () => {
          netErrorModalCVisible = true;
          history.push(loginPath);
        }
      });
    }
  }
  return response;
};

export const request: RequestConfig = {
  errorHandler: (error: any) => {
    const { response } = error;
    if (!response) {
      notification.error({
        description: '您的网络发生异常，无法连接服务器',
        message: '网络异常',
      });
    }
    throw error;
  },
  // zgs
  requestInterceptors: [authHeaderInterceptor],
  responseInterceptors: [responseInterceptor],
};

// ProLayout 支持的api https://procomponents.ant.design/components/layout
export const layout: RunTimeLayoutConfig = ({ initialState }) => {
  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    waterMarkProps: {
      content: initialState?.currentUser?.nickname,
    },
    footerRender: () => <Footer />,
    onPageChange: () => {
      const { location } = history;
      // 如果没有登录，重定向到 login
      if (!initialState?.currentUser && location.pathname !== loginPath) {
        history.push(loginPath);
      }
    },
    links: isDev
      ? [
          <Link to="/umi/plugin/openapi" target="_blank">
            <LinkOutlined />
            <span>OpenAPI 文档</span>
          </Link>,
          <Link to="/~docs">
            <BookOutlined />
            <span>业务组件文档</span>
          </Link>,
        ]
      : [],
    menuHeaderRender: undefined,
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    ...initialState?.settings,
  };
};

