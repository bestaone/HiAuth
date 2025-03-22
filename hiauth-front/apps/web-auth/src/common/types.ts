interface AppContext {
  /** 是否显示去管理员空间按钮 */
  showGoAdminSpaceBut: boolean;
}

interface Context {
  /** 全局配置 */
  app: AppContext;
}

export type { AppContext, Context };
