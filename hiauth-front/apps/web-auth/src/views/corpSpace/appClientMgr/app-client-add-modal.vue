<script lang="ts" setup>
import type { AppVo } from '#/api/core/app';

import { ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { Radio } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { addAppClientsApi, limitNotHaveAppApi } from '#/api/core/appClient';

defineOptions({ name: 'FormModelDemo' });

const apiUrl = import.meta.env.VITE_GLOB_API_URL;
const apps = ref<AppVo[]>([]);
let gridCallback = () => {};

const [Form, formApi] = useVbenForm({
  handleSubmit: onSubmit,
  commonConfig: {
    labelWidth: 0,
  },
  schema: [
    {
      component: 'RadioGroup',
      componentProps: {},
      fieldName: 'appId',
    },
  ],
  showDefaultActions: false,
});

const [Modal, modalApi] = useVbenModal({
  onCancel() {
    modalApi.close();
  },
  onConfirm: async () => {
    await formApi.validateAndSubmitForm();
  },
  onOpenChange: async (isOpen: boolean) => {
    if (isOpen) {
      await formApi.resetForm();
      const param = { offset: 0, limit: 100 };
      apps.value = await limitNotHaveAppApi(param);
    }
  },
  title: '添加应用',
});

async function onSubmit(values: Record<string, any>) {
  const { callback } = modalApi.getData<Record<string, any>>();
  gridCallback = callback;
  await addAppClientsApi({ appIds: [values.appId] });
  await modalApi.close();
  await gridCallback();
}
</script>
<template>
  <Modal class="w-[1000px]">
    <Form>
      <template #appId="slotProps">
        <Radio.Group v-bind="slotProps">
          <Radio
            v-for="app in apps"
            :key="app.id"
            :label="app.name"
            :name="app.name"
            :value="app.id"
            border
            class="app-checkbox"
          >
            <div>
              <img :alt="app.name" :src="apiUrl + app.icon" class="app-icon" />
              <div class="app-name">{{ app.name }}</div>
            </div>
          </Radio>
        </Radio.Group>
      </template>
    </Form>
  </Modal>
</template>
<style scoped lang="scss">
.app-checkbox {
  padding: 5px;
  margin: 10px 5px;
  border: solid 1px #dcdfe6;
  border-radius: 5px;
}

.app-icon {
  float: left;
  height: 60px;
}

.app-name {
  width: 120px;
  height: 60px;
  padding: 0 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 16px;
  line-height: 60px;
  white-space: nowrap;
}
</style>
