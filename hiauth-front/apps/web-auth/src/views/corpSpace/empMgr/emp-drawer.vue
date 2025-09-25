<script lang="ts" setup>
import type { EmpVo } from '#/api/core/emp';

import { ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';

import { useDebounceFn } from '@vueuse/core';

import { useVbenForm, z } from '#/adapter/form';
import { YesOrNoUseBoolOpt } from '#/api/core/common';
import { depTreeApi } from '#/api/core/dep';
import { createEmpApi, findUserApi, updateEmpApi } from '#/api/core/emp';
import { limitRoleApi } from '#/api/core/role';
import { ACTION } from '#/common/constants';

defineOptions({ name: 'EmpDrawer' });

const userKeyword = ref('');
const roleKeyword = ref('');
const drawerAction = ref<ACTION>(ACTION.VIEW);
const empRef = ref<EmpVo>();
let gridCallback = () => {};

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
  },
  handleSubmit: onSubmit,
  schema: [
    {
      component: 'Input',
      componentProps: {
        type: 'hidden',
      },
      fieldName: 'id',
    },
    {
      component: 'ApiTreeSelect',
      componentProps: {
        api: depTreeApi,
        showSearch: true,
        multiple: true,
        treeDefaultExpandAll: true,
        allowClear: true,
        childrenField: 'children',
        labelField: 'name',
        valueField: 'id',
      },
      fieldName: 'depIds',
      label: '部门：',
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'name',
      label: '姓名：',
      rules: z
        .string()
        .min(2, { message: '长度应在2至10个字符内' })
        .max(10, { message: '长度应在2至10个字符内' }),
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'no',
      label: '工号：',
      rules: z
        .string()
        .min(2, { message: '长度应在2至10个字符内' })
        .max(10, { message: '长度应在2至10个字符内' }),
    },
    {
      component: 'Input',
      componentProps: {
        placeholder: '请输入',
      },
      fieldName: 'email',
      label: '邮箱：',
      rules: z.string().email({ message: '请填写正确的邮箱' }),
    },
    {
      component: 'Select',
      componentProps: {
        options: YesOrNoUseBoolOpt,
        placeholder: '请选择',
      },
      fieldName: 'isCorpAdmin',
      label: '管理员：',
      rules: z.boolean({ required_error: '请选择状态' }),
    },
    {
      component: 'ApiSelect',
      componentProps: {
        api: limitRoleApi,
        labelField: 'name',
        valueField: 'id',
        allowClear: true,
        mode: 'multiple',
        // 禁止本地过滤
        filterOption: false,
        // 搜索词变化时记录下来， 使用useDebounceFn防抖。
        onSearch: useDebounceFn((value: string) => {
          roleKeyword.value = value;
        }, 1000),
        // 远程搜索参数。当搜索词变化时，params也会更新
        params: { keyword: roleKeyword.value },
        showSearch: true,
      },
      fieldName: 'roleIds',
      label: '角色：',
    },
    {
      component: 'ApiSelect',
      componentProps: () => {
        return {
          api: findUserApi,
          labelField: 'name',
          valueField: 'id',
          allowClear: true,
          // 禁止本地过滤
          filterOption: false,
          // 搜索词变化时记录下来， 使用useDebounceFn防抖。
          onSearch: useDebounceFn((value: string) => {
            userKeyword.value = value;
          }, 1000),
          // 远程搜索参数。当搜索词变化时，params也会更新
          params: { keyword: userKeyword.value, userId: empRef?.value?.userId },
          showSearch: true,
        };
      },
      fieldName: 'userId',
      label: '绑定账号：',
    },
  ],
  showDefaultActions: false,
});

const [Drawer, drawerApi] = useVbenDrawer({
  onCancel() {
    drawerApi.close();
  },
  onConfirm: async () => {
    await formApi.submitForm();
  },
  onOpenChange(isOpen: boolean) {
    if (isOpen) {
      formApi.resetForm();
      const { action, emp, callback } =
        drawerApi.getData<Record<string, any>>();
      const disabled = action === ACTION.VIEW;
      formApi.setState({ commonConfig: { disabled } });
      drawerApi.setState({ showConfirmButton: !disabled });
      drawerAction.value = action;
      gridCallback = callback;
      if (emp) {
        empRef.value = emp;
        formApi.setValues(emp);
      }
    }
  },
  title: '应用抽屉',
});

async function onSubmit(values: Record<string, any>) {
  const { valid } = await formApi.validate();
  if (!valid) return;
  if (drawerAction.value === ACTION.EDIT) {
    await updateEmpApi({ id: values.id, ...values });
  } else if (drawerAction.value === ACTION.ADD) {
    await createEmpApi({ ...values });
  }
  await drawerApi.close();
  await gridCallback();
}
</script>
<template>
  <Drawer>
    <Form />
  </Drawer>
</template>
