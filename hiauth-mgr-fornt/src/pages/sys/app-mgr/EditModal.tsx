import type {FC} from 'react';
import {ModalForm, ProFormText, ProFormTextArea, ProFormSelect} from '@ant-design/pro-form';
import type {AppListItem} from './data';
import {Button, Result, Descriptions} from 'antd';

type EditModalProps = {
  done: boolean;
  visible: boolean;
  current: Partial<AppListItem> | undefined;
  onDone: () => void;
  onSubmit: (values: AppListItem) => void;
  onCancel: () => void;
};

const EditModal: FC<EditModalProps> = (props) => {

  const { done, visible, current, onDone, onSubmit, onCancel, children } = props;

  if (!visible) {
    return null;
  }

  let style = {};
  if(!current?.clientId || !current?.clientSecret){
    style = {display:'none'};
  }

  const isAdd = current?.id===undefined || current?.id===null;

  return (
    <ModalForm<AppListItem>
      visible={visible}
      title={done ? null : `${isAdd ? '添加' : '编辑'}`}
      // className={styles.standardListForm}
      width={450}
      onFinish={async (values) => {
        onSubmit(values);
      }}
      initialValues={current}
      submitter={{
        render: (_, dom) => (done ? null : dom),
      }}
      trigger={<>{children}</>}
      modalProps={{
        onCancel: () => onCancel(),
        destroyOnClose: true,
        bodyStyle: done ? { padding: '72px 0' } : {},
      }}
    >
      {!done ? (
        <>
          <ProFormText name="id" hidden={true}/>
          <ProFormText
            name="clientName"
            label="应用名称"
            rules={[
              { required: true, message: '请输入应用名称' },
              { min: 10, message: '长度在10-30个字符串间' },
              { max: 30, message: '长度在10-30字符串间' }
            ]}
            placeholder="请输入"
            hasFeedback
          />
          <ProFormSelect
            name="clientAuthenticationMethods"
            label="授权方法"
            request={async () => [
              { label: 'client_secret_basic', value: 'client_secret_basic' },
            ]}
            rules={[{ required: true, message: '请选择授权方法' }]}
            placeholder="请选择授权方法"
          />
          <ProFormSelect
            name="scopes"
            label="授权范围"
            request={async () => [
              { label: '全部', value: 'read,write' },
              { label: '读取', value: 'read' },
              { label: '写入', value: 'write' },
            ]}
            rules={[{ required: true, message: '请选择授权范围' }]}
            placeholder="请选择授权范围"
          />
          <ProFormTextArea
            name="redirectUris"
            label="回调地址"
            rules={[
              { required: true, message: '请输入回调地址' },
              { min: 10, message: '长度在10-100个字符串间' },
              { max: 100, message: '长度在10-100字符串间' }
            ]}
            placeholder="请输入"
            hasFeedback
          />
        </>
      ) : (
        <Result
          status="success"
          title="操作成功"
          subTitle="请妥善保存clientSecret，丢失后无法再次获取！"
          extra={
            <div>
              <Descriptions style={style} column={1}>
                <Descriptions.Item label="clientId">{current?.clientId}</Descriptions.Item>
                <Descriptions.Item label="clientSecret">{current?.clientSecret}</Descriptions.Item>
              </Descriptions>
              <Button type="primary" onClick={onDone}>
                知道了
              </Button>
            </div>
          }
        />
      )}
    </ModalForm>
  );
};

export default EditModal;
