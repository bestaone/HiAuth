import { acceptHMRUpdate, defineStore } from 'pinia';

interface ContentState {
  /**
   * 加密公钥，和后台传输敏感数据时使用
   */
  publicKey: string;
  /**
   * 加密公钥的加密算法，默认为 RSA
   */
  encryptType: string;
}

/**
 * @zh_CN 上下文信息相关
 */
export const useContentStore = defineStore('core-content', {
  actions: {
    setEncrypt(publicKey: string, encryptType: string) {
      this.publicKey = publicKey;
      this.encryptType = encryptType;
    },
  },
  state: (): ContentState => ({
    publicKey: '',
    encryptType: 'RSA',
  }),
});

// 解决热更新问题
const hot = import.meta.hot;
if (hot) {
  hot.accept(acceptHMRUpdate(useContentStore, hot));
}
