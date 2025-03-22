import type { Context } from './types';

import { contextManager } from './context';

// 偏好设置（带有层级关系）
const context: Context = contextManager.getContext.apply(contextManager);

// 更新偏好设置
const updateContext = contextManager.updateContext.bind(contextManager);

// 重置偏好设置
const resetContext = contextManager.resetContext.bind(contextManager);

export { context, contextManager, resetContext, updateContext };

export type * from './types';
