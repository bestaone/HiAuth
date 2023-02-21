import React, {useRef, useState} from 'react';
import {EllipsisOutlined, PlusOutlined} from '@ant-design/icons';
import {Button, Dropdown, Menu, Popconfirm} from 'antd';
import type {ActionType, ProColumns} from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import {SortOrder} from "antd/lib/table/interface";
import {Task} from "@/pages/biz/task-mgr/data";
import {createTask, deleteTask, pageTask, updateTask} from "@/pages/biz/task-mgr/service";

const columns: ProColumns<Task>[] = [
  {
    title: '序号',
    dataIndex: 'index',
    valueType: 'index',
    width: 48,
  },
  {
    title: '标题',
    dataIndex: 'title',
    copyable: true,
    ellipsis: true,
    tip: '任务标题'
  },
  {
    title: '归属部门',
    dataIndex: 'belongDep',
    width: 140,
    hideInSearch: true,
    editable: false,
    ellipsis: true
  },
  {
    title: '归属人',
    dataIndex: 'owner',
    width: 120,
    hideInSearch: true,
    editable: false,
    ellipsis: true
  },
  {
    title: '创建人',
    dataIndex: 'creater',
    width: 120,
    hideInSearch: true,
    editable: false,
    ellipsis: true
  },
  {
    title: '状态',
    dataIndex: 'status',
    filters: true,
    onFilter: true,
    valueType: 'select',
    width: 120,
    valueEnum: {
      new: {
        text: '新建',
        status: 'new',
      },
      doing: {
        text: '进行中',
        status: 'doing',
      },
      done: {
        text: '已完成',
        status: 'done',
      },
    },
  },
  {
    title: '创建时间',
    key: 'showTime',
    dataIndex: 'createTime',
    valueType: 'dateTime',
    sorter: true,
    hideInSearch: true,
    editable: false,
    width: 200,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    valueType: 'dateRange',
    hideInTable: true,
    search: {
      transform: (value) => {
        return { startTime: value[0], endTime: value[1] };
      },
    },
  },
  {
    title: '操作',
    valueType: 'option',
    width: 140,
    render: (text, record, _, action) => [
      <a key="editable" onClick={() => { action?.startEditable?.(record.id); }}>编辑</a>,
      <Popconfirm title="确定要删除该任务?" okText="是" cancelText="否"
        onConfirm={async () => {
          await deleteTask(record.id);
          action?.reload();
        }}
      >
        <a key="delete">删除</a>
      </Popconfirm>
    ],
  },
];

const menu = (
  <Menu>
    <Menu.Item key="1">1st item</Menu.Item>
    <Menu.Item key="2">2nd item</Menu.Item>
    <Menu.Item key="3">3rd item</Menu.Item>
  </Menu>
);

const tableRequest = async (params: any = {}, sort: Record<string, SortOrder>, filter: Record<string, React.ReactText[] | null>) => {
  console.debug(params, sort, filter);
  const resq = await pageTask({
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

export default () => {

  const actionRef = useRef<ActionType>();
  const [editableKeys, setEditableRowKeys] = useState<React.Key[]>([]);

  return (
    <ProTable<Task>
      rowKey="id"
      dateFormatter="string"
      headerTitle="任务列表"
      columns={columns}
      actionRef={actionRef}
      request={tableRequest}
      editable={{
        type: 'multiple',
        editableKeys,
        onSave: async (rowKey, task: Task) => {
          if (task && task.id && task.id > 0) {
            await updateTask(task);
          } else {
            await createTask(task)
          }
          actionRef.current?.reload();
        },
        onChange: setEditableRowKeys,
      }}
      options={{
        density: true,
        fullScreen: true,
        setting: true,
      }}
      pagination={{
        showQuickJumper: false,
        pageSize: 5,
      }}
      search={{
        labelWidth: 'auto',
        filterType: 'light',
      }}
      form={{
        // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
        syncToUrl: (values, type) => {
          if (type === 'get') {
            return {...values, createTimeRange: [values.startTime, values.endTime]};
          }
          return values;
        },
      }}
      toolBarRender={() => [
        // <Button key="button" icon={<PlusOutlined />} type="primary">新建</Button>,
        <Button key="add" icon={<PlusOutlined />} type="primary"
          onClick={() => {
            actionRef.current?.addEditRecord?.({id: -1, title: '', status: 'new'});
          }}>创建</Button>,
        <Dropdown key="menu" overlay={menu}>
          <Button>
            <EllipsisOutlined />
          </Button>
        </Dropdown>,
      ]}
    />
  );
};
