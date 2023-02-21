import {Button, Drawer, Dropdown, Menu, message, Modal} from 'antd';
import React, {useRef, useState} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import ProTable, {ActionType, ProColumns} from '@ant-design/pro-table';
import {EllipsisOutlined} from '@ant-design/icons';
import ProDescriptions, {ProDescriptionsItemProps} from "@ant-design/pro-descriptions";

import OptModal from "./components/OptModal";
import {createUser, deleteUser, pageUser, updateUser} from './service';
import {UserListItem} from "./data";
import {SortOrder} from "antd/lib/table/interface";

const UserList: React.FC = () => {

  const actionRef = useRef<ActionType>();
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const [editDone, setEditDone] = useState<boolean>(false);
  const [editVisible, setEditVisible] = useState<boolean>(false);
  const [current, setCurrent] = useState<Partial<UserListItem> | undefined>(undefined);

  // handle
  const addHandle = async (values: UserListItem) => {
    try {
      await createUser({ ...values });
      message.success('添加成功');
      return true;
    } catch (error) {
      message.error('添加失败请重试！');
      return false;
    }
  };

  const updateHandle = async (user: UserListItem) => {
    try {
      await updateUser({
        id: user.id,
        username: user.username,
        phoneNum: user.phoneNum,
        password: user.password
      });
      message.success('修改成功');
      return true;
    } catch (error) {
      message.error('修改失败请重试！');
      return false;
    }
  };

  const deleltHandle = async (id: number) => {
    try {
      await deleteUser(id);
      message.success('删除成功');
      setShowDetail(false);
      actionRef.current?.reload();
      return true;
    } catch (error) {
      message.error('删除失败请重试！');
      return false;
    }
  };

  // table request
  const tableRequest = async (params: any, sorter: Record<string, SortOrder>, filter: Record<string, any>) => {
    console.debug(params, sorter, filter);
    const resq = await pageUser({
      pageNum: params.current,
      pageSize: params.pageSize,
      ...params
    });
    return Promise.resolve({
      success: true,
      total: resq?.data?.total,
      data: resq?.data?.records,
    });
  };

  // 窗口
  const showDetailModal = (user?: UserListItem) => {
    setCurrent(user);
    setShowDetail(true);
  };

  const showEditOrAddModal = (item?: UserListItem) => {
    setEditVisible(true);
    if(item){
      setCurrent(item);
    } else {
      setCurrent({});
    }
  };

  // 编辑添加窗口
  const editOnCancelHandle = () => {
    setEditDone(false);
    setEditVisible(false);
  };

  const editOnDoneHandle = () => {
    editOnCancelHandle();
    actionRef.current?.reload();
  };

  const editOnSubmitHandle = (user: UserListItem) => {
    setEditDone(true);
    if(user?.id){
      updateHandle(user);
    } else {
      addHandle(user);
    }
  };

  // 行action
  const rowAction = (key: string | number, user: UserListItem) => {
    if (key === 'edit') showEditOrAddModal(user);
    else if (key === 'delete') {
      Modal.confirm({
        title: '删除账号',
        content: '确定删除该账号吗？',
        okText: '确认',
        cancelText: '取消',
        onOk: () => { deleltHandle(user.id) }
      });
    }
  };

  const MoreBtn: React.FC<{
    item: UserListItem;
  }> = ({ item }) => (
    <Dropdown
      overlay={
        <Menu onClick={({ key }) => rowAction(key, item)}>
          <Menu.Item key="edit">编辑</Menu.Item>
          <Menu.Item key="delete">删除</Menu.Item>
        </Menu>
      }
    >
      <a>
        <EllipsisOutlined />
      </a>
    </Dropdown>
  );

  // 表格列配置
  const columns: ProColumns<UserListItem>[] = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'index',
      width: 50,
    },
    {
      title: '用户名',
      dataIndex: 'username',
      render: (_, user: UserListItem) => [
        <a onClick={ () => showDetailModal(user) }>{_}</a>
      ],
    },
    {
      title: '手机号',
      dataIndex: 'phoneNum',
    },
    {
      title: '注册时间',
      dataIndex: 'regtime',
      valueType: 'dateTime',
      hideInSearch: true
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueType: 'select',
      valueEnum: {
        0: {text: '禁用'},
        1: {text: '正常'},
      },
    },
    {
      title: '操作',
      width: '150px',
      key: 'option',
      valueType: 'option',
      render: (_, record: UserListItem) => [
        <a key="modify" onClick={ () => showEditOrAddModal(record) }>修改</a>,
        <MoreBtn key="more" item={record} />,
      ],
    },
  ];

  // 表格
  return (
    <PageContainer>
      <ProTable<UserListItem>
        rowKey="id"
        // headerTitle="账号管理"
        tooltip='账号管理'
        columns={columns}
        actionRef={actionRef}
        request={tableRequest}
        pagination={{
          showQuickJumper: false,
        }}
        search={{
          filterType: 'light',
        }}
        dateFormatter="string"
        options={{
          density: true,
          fullScreen: true,
          setting: true,
        }}
        toolBarRender={() => [
          <Button
            key="add"
            onClick={ () => showEditOrAddModal() }>
            创建
          </Button>
        ]}
      />
      <Drawer
        width={560}
        title="账号详情"
        visible={showDetail}
        closable={true}
        onClose={() => {
          setCurrent({});
          setShowDetail(false);
        }}
      >
        {current?.username && (
          <ProDescriptions<UserListItem>
            column={2}
            title={current?.username}
            request={async () => ({
              data: current || {},
            })}
            params={{
              id: current?.id,
            }}
            columns={columns as ProDescriptionsItemProps<UserListItem>[]}
          />
        )}
      </Drawer>
      <OptModal
        current={current}
        done={editDone}
        visible={editVisible}
        onDone={editOnDoneHandle}
        onSubmit={editOnSubmitHandle}
        onCancel={editOnCancelHandle}
      />
    </PageContainer>
  );
};

export default UserList;
