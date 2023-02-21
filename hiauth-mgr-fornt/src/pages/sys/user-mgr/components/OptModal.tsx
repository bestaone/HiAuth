import type {FC} from 'react';
import {ModalForm, ProFormText,} from '@ant-design/pro-form';
import type {UserListItem} from '../data.d';
import {Button, Result} from 'antd';

type OptModalProps = {
  done: boolean;
  visible: boolean;
  current: Partial<UserListItem> | undefined;
  onDone: () => void;
  onSubmit: (values: UserListItem) => void;
  onCancel: () => void;
};

const OptModal: FC<OptModalProps> = (props) => {

  const { done, visible, current, onDone, onSubmit, onCancel, children } = props;

  if (!visible) {
    return null;
  }

  const isAdd = current?.id===undefined || current?.id===null;

  return (
    <ModalForm<UserListItem>
      visible={visible}
      title={done ? null : `${isAdd ? '添加' : '编辑'}用户`}
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
            name="username"
            label="用户名"
            rules={[
              { required: true, message: '请输入用户名' },
              { min: 3, message: '长度在3-20个字符串间' },
              { max: 20, message: '长度在3-20个字符串间' }
            ]}
            placeholder="请输入"
            hasFeedback
          />
          <ProFormText
            name="phoneNum"
            label="手机号码"
            rules={[
              { required: true, message: '请输入手机号码' },
              { pattern: /^1\d{10}$/, message: '手机号格式错误！'}
            ]}
            placeholder="请输入"
            hasFeedback
          />
          <ProFormText.Password
            name="password"
            label="密码"
            rules={[
              { required: isAdd, message: '请输入密码' },
              { min: 3, message: '长度在3-20个字符串间' },
              { max: 20, message: '长度在3-20个字符串间' }
            ]}
            placeholder="请输入"
            hasFeedback
          />
          <ProFormText.Password
            name="rePassword"
            label="确认密码"
            rules={[
              { required: isAdd, message: '请再次输入密码' },
              ({ getFieldValue }) => ({
                validator(_, value) {
                  if (!value || getFieldValue('password') === value) {
                    return Promise.resolve();
                  }
                  return Promise.reject(new Error('两次密码不一致!'));
                },
              }),
            ]}
            placeholder="请输入"
            hasFeedback
          />
        </>
      ) : (
        <Result
          status="success"
          title="操作成功"
          subTitle="一系列的信息描述，很短同样也可以带标点。"
          extra={
            <Button type="primary" onClick={onDone}>
              知道了
            </Button>
          }
        />
      )}
    </ModalForm>
  );
};

export default OptModal;
