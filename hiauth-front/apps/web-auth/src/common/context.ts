import type { DeepPartial } from '@vben/types';

import type { Context } from './types';

import { markRaw, reactive, readonly } from 'vue';

import { merge, StorageManager } from '@vben/utils';

import { useDebounceFn } from '@vueuse/core';

import { defaultContext } from './config';

const STORAGE_KEY = 'app-context';

class ContextManager {
  private cache: null | StorageManager = new StorageManager({ prefix: 'auth' });
  private initialContext: Context = defaultContext;
  private saveContext: (preference: Context) => void;
  private state: Context = reactive<Context>({
    ...this.loadContext(),
  });

  constructor() {
    // 避免频繁的操作缓存
    this.saveContext = useDebounceFn(
      (context: Context) => this._saveContext(context),
      150,
    );
  }

  public getContext() {
    return readonly(this.state);
  }

  resetContext() {
    // 将状态重置为初始偏好设置
    Object.assign(this.state, this.initialContext);
    // 保存重置后的偏好设置
    this.saveContext(this.state);
    // 从存储中移除偏好设置项
    [STORAGE_KEY].forEach((key) => {
      this.cache?.removeItem(key);
    });
    this.updateContext(this.state);
  }

  public updateContext(updates: DeepPartial<Context>) {
    const mergedState = merge({}, updates, markRaw(this.state));
    Object.assign(this.state, mergedState);
    this.saveContext(this.state);
  }

  private _saveContext(preference: Context) {
    this.cache?.setItem(STORAGE_KEY, preference);
  }

  private loadCachedContext() {
    return this.cache?.getItem<Context>(STORAGE_KEY);
  }

  private loadContext(): Context {
    return this.loadCachedContext() || { ...defaultContext };
  }
}

const contextManager = new ContextManager();
export { ContextManager, contextManager };
