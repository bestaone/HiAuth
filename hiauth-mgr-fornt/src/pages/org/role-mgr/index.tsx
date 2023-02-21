import React, {Key, useRef, useState} from 'react';
import type {ProColumns} from '@ant-design/pro-table';
import {EditableProTable} from '@ant-design/pro-table';
import {Button, Drawer, Popconfirm, Tabs, Tree, TreeDataNode} from "antd";
import {ActionType} from "@ant-design/pro-table/es";
import {SortOrder} from "antd/lib/table/interface";
import {createRole, deleteRole, pageRole, updateRole} from "@/pages/org/role-mgr/service";
import {Role} from "@/pages/org/role-mgr/data";
import {Permission, ResourceGroup, ResourceInfo} from "@/pages/sys/resource-mgr/data";
import {
  findResourceAndPermissionByRoleAndGroup,
  limitListByRoleIdAndGroupId,
  limitResourceGroup,
  updateRolePermission,
  updateRoleResource
} from "@/pages/sys/resource-mgr/service";
import {DataNode, EventDataNode} from "rc-tree/lib/interface";
import {ProfileOutlined, SettingOutlined} from "@ant-design/icons";
import {Access, useAccess} from 'umi';

interface CheckInfo {
  event: 'check';
  node: EventDataNode;
  checked: boolean;
  nativeEvent: MouseEvent;
  checkedNodes: DataNode[];
  checkedNodesPositions?: {
    node: DataNode;
    pos: string;
  }[];
  halfCheckedKeys?: Key[];
}

export default () => {

  const access = useAccess();
  const actionRef = useRef<ActionType>();
  const [editableKeys, setEditableRowKeys] = useState<React.Key[]>([]);
  const [dataSource, setDataSource] = useState<Role[]>([]);
  const [showRoleDetailInfoDrawer, setShowRoleDetailInfoDrawer] = useState<boolean>(false);

  const [resourceTreeCheckedKeys, setResourceTreeCheckedKeys] = useState<number[]>([]);

  const [resourceTreeItems, setResourceTreeItems] = useState<TreeDataNode[]>();
  const [resourceGroupActiveKey, setResourceGroupActiveKey] = useState<string>();
  const [resourceGroups, setResourceGroups] = useState<ResourceGroup[]>([]);
  const [currentShowRole, setCurrentShowRole] = useState<Role>();

  const MAX_DEPTH = 10;
  function buildChild(id: number, list: ResourceInfo[], depth: number){
    if(list==null || list.length===0 || depth>MAX_DEPTH){
      return [];
    }
    const cs: TreeDataNode[] = [];
    list?.forEach((e: ResourceInfo) => {
      if(id===e.pid){
        const childrens = buildChild(e.id, list, depth+1);
        if(e.permissions && e.permissions.length>0) {
          e.permissions.forEach((p: Permission)=>{
            childrens.push({ key: p.id, title: p.name, className: 'permission', icon: <SettingOutlined /> });
          });
        }
        cs.push({ key: e.id, title: e.name, children: childrens, className: 'resource', icon: <ProfileOutlined /> });
      }
    });
    return cs;
  }

  const initResourceTree = (roleId: number, groupId: number) => {
    limitListByRoleIdAndGroupId(groupId).then(({ data }) => {
      if(data && data.length>0) {
        const tree: TreeDataNode[] = buildChild(0, data, 0);
        setResourceTreeItems(tree);
        findResourceAndPermissionByRoleAndGroup(roleId, groupId).then(( resp ) => {
          setResourceTreeCheckedKeys(resp.data||[])
        });
      }
    });
  }

  const initResourceGroup = (roleId: number) => {
    limitResourceGroup().then(({ data }) => {
      if(data && data.length>0) {
        resourceGroups.length = 0;
        setResourceGroups(data);
        setResourceGroupActiveKey(`${data[0].id}`);
        initResourceTree(roleId, data[0].id);
      }
    });
  }

  const activeResourceGroupTab = (roleId: number, groupId: number) => {
    setResourceGroupActiveKey(`${groupId}`);
    initResourceTree(roleId, groupId);
  }

  const showRoleDetailInfo = (role: Role) => {
    setCurrentShowRole(role);
    if(resourceGroups.length===0) {
      initResourceGroup(role.id);
    } else {
      activeResourceGroupTab(role.id, resourceGroups[0].id);
    }
    setShowRoleDetailInfoDrawer(true);
  }

  const resourceGroupOnChange = (key: string) => {
    if(currentShowRole){
      activeResourceGroupTab(currentShowRole.id, Number(key));
    }
  }

  const onSelect = (keys: any, info: any) => {
    console.log(keys,info);
  }

  const onCheck = (keys: any, info: CheckInfo) => {
    const opType: number = info.checked ? 1 : 0;
    const dataType = info.node.className;
    const id = Number(info.node.key);

    if(dataType?.includes("resource")){
      updateRoleResource({
        opType,
        roleId: currentShowRole?.id,
        resourceId: id
      }).then(() => {
        setResourceTreeCheckedKeys(keys);
      });
    } else if(dataType?.includes("permission")){
      updateRolePermission({
        opType,
        roleId: currentShowRole?.id,
        permissionId: id
      }).then(() => {
        setResourceTreeCheckedKeys(keys);
      });
    }

  }

  // ss ///////////////////////////////////////////////////////////////

  const tableRequest = async (params: any, sort: Record<string, SortOrder>, filter: Record<string, React.ReactText[] | null>) => {
    console.debug(params, sort, filter);
    const resq = await pageRole({
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

  const columns: ProColumns<Role>[] = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'index',
      width: 50,
    },
    {
      title: '名称',
      dataIndex: 'name',
      formItemProps: (form, {rowIndex}) => {
        return {
          rules: rowIndex > 2 ? [
            { required: true, message: '名称不能为空' },
            { min: 3, message: '长度在3-20个字符串间' },
            { max: 20, message: '长度在3-20个字符串间' }
          ] : [],
        };
      },
      render: (_, role: Role) => <a onClick={ () => showRoleDetailInfo(role) }>{_}</a>,
    },
    {
      title: '编码',
      dataIndex: 'code',
      formItemProps: (form, {rowIndex}) => {
        return {
          rules: rowIndex > 3 ? [
            { required: true, message: '编码不能为空' },
            { min: 3, message: '长度在3-20个字符串间' },
            { max: 20, message: '长度在3-20个字符串间' }
          ] : [],
        };
      }
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
      hideInSearch: true,
      editable: false
    },
    {
      title: '创建时间',
      dataIndex: 'createTimeRange',
      valueType: 'dateTimeRange',
      hideInTable: true,
      initialValue: [],
    },
    {
      title: '状态',
      key: 'status',
      dataIndex: 'status',
      valueType: 'select',
      formItemProps: (form, {rowIndex}) => {
        return {
          rules: rowIndex > 6 ? [{ required: true, message: '状态不能为空' }] : [],
        };
      },
      valueEnum: {
        0: {
          text: '已禁用',
          status: 'Error',
        },
        1: {
          text: '已启用',
          status: 'Success',
        },
      },
    },
    {
      title: '描述',
      dataIndex: 'remark',
      hideInSearch: true
    },
    {
      title: '操作',
      valueType: 'option',
      width: 200,
      render: (text, record, _, action) => [
        <Access accessible={access.permissionFilter('role#modify')} fallback={<div className={"disabled"}>编辑</div>}>
          <a key="editable" onClick={ () => action?.startEditable?.(record.id) }>编辑</a>
        </Access>,
        <Access accessible={access.permissionFilter('role#delete')} fallback={<div className={"disabled"}>删除</div>}>
          <Popconfirm
            title="确定要删除该角色?"
            onConfirm={async () => {
              await deleteRole(record.id);
              action?.reload();
            }}
            okText="是"
            cancelText="否"
          >
            <a key="delete">删除</a>
          </Popconfirm>
        </Access>

      ],
    },
  ];

  return (
    <>
      <EditableProTable<Role>
        rowKey="id"
        headerTitle="角色列表"
        actionRef={actionRef}
        pagination={{
          showQuickJumper: false,
        }}
        search={{
          filterType: 'light',
        }}
        dateFormatter="string"
        maxLength={5}
        options={{
          density: true,
          fullScreen: true,
          setting: true,
        }}
        recordCreatorProps={false}
        toolBarRender={() => [
          <Button
            key="add"
            disabled={!access.permissionFilter('role#create')}
            onClick={() => {
              actionRef.current?.addEditRecord?.({
                id: -1,
                name: '',
                status: '',
                remark: ''
              });
            }}>创建</Button>
        ]}
        columns={columns}
        request={tableRequest}
        value={dataSource}
        onChange={setDataSource}
        editable={{
          type: 'multiple',
          editableKeys,
          onSave: async (rowKey, role, row) => {
            console.log(rowKey, role, row);
            if (role && role.id && role.id > 0) {
              await updateRole(role);
            } else {
              await createRole(role)
            }
            actionRef.current?.reload();
          },
          onChange: setEditableRowKeys,
        }}
      />
      <Drawer
        width={500}
        placement="right"
        visible={showRoleDetailInfoDrawer}
        closable={false}
        onClose={() => {
          setShowRoleDetailInfoDrawer(false);
        }}
      >
        <Tabs activeKey={resourceGroupActiveKey} onChange={resourceGroupOnChange}>
          {resourceGroups.map(group => (
            <Tabs.TabPane tab={group.name} key={`${  group.id}`} />
          ))}
        </Tabs>
        <Tree
          showIcon={false}
          checkable={true}
          checkStrictly={true}
          onSelect={onSelect}
          onCheck={onCheck}
          checkedKeys={resourceTreeCheckedKeys}
          treeData={resourceTreeItems}
        />
      </Drawer>
    </>
  );
}
