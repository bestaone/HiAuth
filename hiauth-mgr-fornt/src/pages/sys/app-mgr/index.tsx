import {Button, Drawer, Dropdown, Menu, message, Modal} from 'antd';
import React, {useRef, useState} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import ProTable, {ActionType, ProColumns} from '@ant-design/pro-table';
import {EllipsisOutlined} from '@ant-design/icons';
import ProDescriptions, {ProDescriptionsItemProps} from "@ant-design/pro-descriptions";

import EditModal from "./EditModal";
import {createApp, deleteApp, pageApp, updateApp} from './service';
import {AppListItem} from "./data";
import {SortOrder} from "antd/lib/table/interface";

const AppList: React.FC = () => {

  const actionRef = useRef<ActionType>();
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const [editDone, setEditDone] = useState<boolean>(false);
  const [editVisible, setEditVisible] = useState<boolean>(false);
  const [current, setCurrent] = useState<Partial<AppListItem> | undefined>(undefined);

  // handle
  const addHandle = async (app: AppListItem) => {
    try {
      const {data} = await createApp({ ...app });
      message.success('添加成功');
      setCurrent(data);
      setEditDone(true);
      return true;
    } catch (error) {
      message.error('添加失败请重试！');
      return false;
    }
  };

  const updateHandle = async (app: AppListItem) => {
    try {
      const {data} = await updateApp({
        id: app.id,
        clientName: app.clientName,
        clientAuthenticationMethods: app.clientAuthenticationMethods,
        redirectUris: app.redirectUris,
        scopes: app.scopes
      });
      message.success('修改成功');
      setCurrent(data);
      setEditDone(true);
      return true;
    } catch (error) {
      message.error('修改失败请重试！');
      return false;
    }
  };

  const deleltHandle = async (id: string) => {
    try {
      await deleteApp(id);
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
    const resq = await pageApp({
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
  const showDetailModal = (app?: AppListItem) => {
    setCurrent(app);
    setShowDetail(true);
  };

  const showEditOrAddModal = (app?: AppListItem) => {
    setEditVisible(true);
    if(app){
      setCurrent(app);
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

  const editOnSubmitHandle = (app: AppListItem) => {
    if(app?.id){
      updateHandle(app);
    } else {
      addHandle(app);
    }
  };

  // 行action
  const rowAction = (key: string | number, app: AppListItem) => {
    if (key === 'edit') showEditOrAddModal(app);
    else if (key === 'delete') {
      Modal.confirm({
        title: '删除账号',
        content: '确定删除该账号吗？',
        okText: '确认',
        cancelText: '取消',
        onOk: () => { deleltHandle(app.id) }
      });
    }
  };

  const MoreBtn: React.FC<{ item: AppListItem; }> = ({ item }) => (
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
  const columns: ProColumns<AppListItem>[] = [
    {
      title: '应用名称',
      dataIndex: 'clientName',
      render: (_, app: AppListItem) => [
        <a onClick={ () => showDetailModal(app) }>{_}</a>
      ],
    },
    {
      title: '应用ID',
      dataIndex: 'clientId',
      copyable: true,
      ellipsis: true
    },
    {
      title: '授权方式',
      dataIndex: 'clientAuthenticationMethods',
      width: 100,
      hideInSearch: true,
      editable: false,
      ellipsis: true
    },
    {
      title: '授权类型',
      dataIndex: 'authorizationGrantTypes',
      hideInSearch: true,
      editable: false,
      ellipsis: true
    },
    {
      title: '回调地址',
      dataIndex: 'redirectUris',
      hideInSearch: true,
      editable: false,
      copyable: true,
      ellipsis: true
    },
    {
      title: '权限范围',
      dataIndex: 'scopes',
      hideInSearch: true,
      editable: false,
      ellipsis: true
    },
    {
      title: '失效时间',
      key: 'showTime',
      dataIndex: 'clientSecretExpiresAt',
      valueType: 'dateTime',
      sorter: true,
      hideInSearch: true,
      editable: false
    },
    {
      title: '操作',
      width: '150px',
      key: 'option',
      valueType: 'option',
      render: (_, record: AppListItem) => [
        <a key="modify" onClick={ () => showEditOrAddModal(record) }>修改</a>,
        <MoreBtn key="more" item={record} />,
      ],
    },
  ];

  // 表格
  return (
    <PageContainer>
      <ProTable<AppListItem>
        rowKey="id"
        headerTitle="账号管理"
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
      <Drawer width={600} title="账号详情" visible={showDetail} closable={true}
        onClose={() => {
          setCurrent({});
          setShowDetail(false);
        }}
      >
        {current?.clientName && (
          <ProDescriptions<AppListItem>
            title={current?.clientName}
            column={1}
            request={async () => ({
              data: current || {},
            })}
            params={{ id: current?.id }}
            columns={columns as ProDescriptionsItemProps<AppListItem>[]}
          />
        )}
      </Drawer>
      <EditModal
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

export default AppList;
