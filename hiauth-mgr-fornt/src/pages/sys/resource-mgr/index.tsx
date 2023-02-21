// @ts-ignore
import React, {useEffect, useRef, useState} from 'react';
import {EllipsisOutlined} from '@ant-design/icons';
import {Button, Card, Dropdown, Menu, Tree, TreeDataNode} from 'antd';
import {PageContainer} from '@ant-design/pro-layout';
import type {ActionType, ProColumns} from "@ant-design/pro-table";
import ProTable from "@ant-design/pro-table";
import {limitResourceByGroupId, limitResourceGroup, pagePermissionByResourceId} from "@/pages/sys/resource-mgr/service";
import type {Resource, ResourceGroup} from "@/pages/sys/resource-mgr/data";
import {Permission} from "@/pages/sys/resource-mgr/data";
import {SortOrder} from "antd/lib/table/interface";
import {Key} from "rc-tree/lib/interface";

type TabItem = {
  tab: string;
  key: number;
  closable: boolean
};

export default () => {

  const actionRef = useRef<ActionType>();

  const [resourceGroupTabItems, setResourceGroupTabItems] = useState<TabItem[]>();
  const [resourceTreeItems, setResourceTreeItems] = useState<TreeDataNode[]>();
  const [resourceId, setResourceId] = useState<number|null>();

  const MAX_DEPTH = 10;
  function buildChild(id: number, list: Resource[], depth: number){
    if(list==null || list.length===0 || depth>MAX_DEPTH){
      return [];
    }
    const cs: TreeDataNode[] = [];
    list?.forEach((e: Resource) => {
      if(id===e.pid){
        const childrens = buildChild(e.id, list, depth+1);
        cs.push({ key: e.id, title: e.name, children: childrens });
      }
    });
    return cs;
  }

  const initResourceTree = (groupId: number) => {
    limitResourceByGroupId(groupId).then(({ data }) => {
      if(data && data.length>0) {
        const tree: TreeDataNode[] = buildChild(0, data, 0);
        setResourceTreeItems(tree);
        setResourceId(data[0].id);
      }
    });
  }

  const loadPermissions = (rId: number) => {
    setResourceId(rId);
  }

  const initResourceGroupPage = (groupId: number) => {
    initResourceTree(groupId);
  }

  const initResourceGroupTabList = () => {
    limitResourceGroup().then(({ data }) => {
      if(data && data.length>0) {
        const rgs = data?.map( (item: ResourceGroup) => {
          const e: TabItem = {
            tab: item.name,
            key: item.id,
            closable: false
          }
          return e;
        });
        setResourceGroupTabItems(rgs);
        initResourceGroupPage(rgs[0].key);
      }
    });
  }

  useEffect(() => {
    initResourceGroupTabList();
  }, []);

  const resourceTreeOnSelect = (selectedKeys: Key[]) => {
    if(selectedKeys.length>0) {
      loadPermissions(Number(selectedKeys[0]));
    }
  };

  const resourceGroupTabOnChange = (key: string) => {
    initResourceGroupPage(Number(key));
  }

  const tableRequest = async (params: any, sort: Record<string, SortOrder>, filter: Record<string, React.ReactText[] | null>) => {
    if(!params.resourceId) return null;
    console.debug(params, sort, filter);
    const resq = await pagePermissionByResourceId({
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

  const columns: ProColumns<Permission>[] = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'index',
      width: 80,
    },
    {
      title: '权限名称',
      key: 'name',
      dataIndex: 'name',
      hideInSearch: true
    },
    {
      title: '权限编码',
      key: 'code',
      dataIndex: 'code',
      hideInSearch: true
    },
    {
      title: '权限说明',
      key: 'remark',
      dataIndex: 'remark',
      hideInSearch: true
    },
  ];

  return (
    <>
      <PageContainer
        fixedHeader
        header={{
          extra: [
            <Button key="1">次要按钮</Button>,
            <Button key="2" type="primary">
              主要按钮
            </Button>,
            <Dropdown
              key="dropdown"
              trigger={['click']}
              overlay={
                <Menu>
                  <Menu.Item key="1">下拉菜单</Menu.Item>
                  <Menu.Item key="2">下拉菜单2</Menu.Item>
                </Menu>
              }
            >
              <Button style={{ padding: '0 8px' }}>
                <EllipsisOutlined />
              </Button>
            </Dropdown>,
          ],
        }}
        tabList={resourceGroupTabItems}
        tabProps={{
          type: 'line',
          hideAdd: true,
          onChange: (key) => resourceGroupTabOnChange(key),
        }}
      >

        <ProTable<Permission>
          headerTitle="权限列表"
          actionRef={actionRef}
          columns={columns}
          rowKey="key"
          pagination={{
            showSizeChanger: true,
          }}
          search={{
            filterType: 'light',
          }}
          params={{
            resourceId,
          }}
          request={ tableRequest }
          tableRender={(_, dom) => (
            <div style={{ display: 'flex', width: '100%' }} >
              <Card title="资源数" style={{ width: 300 }}>
                <Tree
                  onSelect={resourceTreeOnSelect}
                  treeData={resourceTreeItems}
                />
              </Card>

              <div style={{ flex: 1 }}>
                {dom}
              </div>
            </div>
          )}
          dateFormatter="string"
          options={{
            density: true,
            fullScreen: true,
            setting: true,
          }}
        />

      </PageContainer>
    </>
  );

};





