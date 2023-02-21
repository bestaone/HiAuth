// @ts-ignore
import React, {useEffect, useRef, useState} from 'react';
import type {ProColumns} from '@ant-design/pro-table';
import ProTable, {ActionType} from '@ant-design/pro-table';
import {SortOrder} from "antd/lib/table/interface";
import {
  addEmpWithDep,
  deleteEmpById,
  empUnbindUser,
  getDetailById,
  modifyEmp,
  modifyEmpWithDep,
  pageEmp
} from "@/pages/org/emp-mgr/service";
import {Employ} from "@/pages/org/emp-mgr/data";
import DepTree from "@/components/DepTree";
import {Button, Col, Divider, Drawer, Form, Input, Modal, Row, TreeSelect} from "antd";
import {Department} from "@/components/DepTree/data";
import {findDepAll} from "@/components/DepTree/service";
import {Access, useAccess} from "@@/plugin-access/access";
import {addEmpRole, deleteEmpRole, limitRole} from "@/pages/org/role-mgr/service";
import {Role} from "@/pages/org/role-mgr/data";
import EditableTagGroup, {TagType} from "@/components/EditableTagGroup";
import EditableTag, {EditableTagValueType} from "@/components/EditableTag";
import {User} from "@/pages/sys/user-mgr/data";
import {limitUnbindUser} from "@/pages/sys/user-mgr/service";

// table request
const tableRequest = async (params: any, sorter: Record<string, SortOrder>, filter: Record<string, any>) => {
  console.debug(params, sorter, filter);
  const resq = await pageEmp({
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

  const access = useAccess();
  const actionRef = useRef<ActionType>();
  const [depId, setDepId] = useState<number|null>();
  const [detailModalVisible, setDetailModalVisible] = useState<boolean>(false);

  const [isAdd, setIsAdd] = useState<boolean>(false);
  const [editModalVisible, setEditModalVisible] = useState<boolean>(false);
  const [depTreeData, setDepTreeData] = useState<any[]>();

  const [tags, setTags] = useState<TagType[]>([]);
  const [userTag, setUserTag] = useState<EditableTagValueType | null>();
  const [currentDetailEmpId, setCurrentDetailEmpId] = useState<number|null>();

  // 初始化
  useEffect(() => {
    initDepTreeData();
  }, []);

  // 构建部门数数据
  function initDepTreeData(){
    findDepAll({}).then(({ data }) => {
      const tree = buildChild(0, data||[], 0);
      setDepTreeData(tree);
    });
  }

  // 部门树选中函数
  const onSelect = (id: number) => {
    setDepId(id);
  }

  // 部门树刷新函数
  const onRefresh = () => {
    setDepId(null);
  }

  // 详情页关闭
  const detailModalOnClose = () => {
    setDetailModalVisible(false);
  }

  // 显示员工详情
  const showDetail = (emp: Employ) => {
    setCurrentDetailEmpId(emp.id);
    setDetailModalVisible(true);
    getDetailById(emp.id).then(({data})=>{
      const ts: TagType[] = [];
      data?.roles?.forEach( (e: Role) => {
        ts.push({ id: e.id, text: e.name, closeable: true })
      });
      setTags(ts);
      if(data?.user){
        setUserTag({
          id: data.user.id,
          text: data.user.username
        });
      } else {
        setUserTag(null);
      }
    });
  }

  const DescriptionItem = ({ title, content }: any) => (
    <div className="site-description-item-profile-wrapper">
      <p className="site-description-item-profile-p-label">
        <span>{title}</span> : <span>{content}</span>
      </p>
    </div>
  );

  // 员工编辑表单
  const [form] = Form.useForm();

  // 添加员工
  const add = () => {
    form.resetFields();
    setEditModalVisible(true);
    setIsAdd(true);
  };

  // 编辑员工
  const edit = (emp: Employ) => {
    getDetailById(emp.id).then(({ data }) => {
      form.resetFields();
      form.setFieldsValue(data)
      setIsAdd(false);
      setEditModalVisible(true);
    });
  };

  // 删除员工
  const deleteEmp = (emp: Employ) => {
    Modal.confirm({
      title: '删除员工',
      content: `确定删除员工 ${  emp.name} ？`,
      okText: '确认',
      cancelText: '取消',
      onOk: () => {
        deleteEmpById({id: emp.id}).then(() => {
          actionRef.current?.reload();
        });
      }
    });
  };

  // 取消编辑
  const editOnCancel = () => {
    setEditModalVisible(false);
  }

  // 提交编辑
  const editSubmiHandle = async (emp: Employ) => {
    if(isAdd){
      await addEmpWithDep(emp);
    } else {
      await modifyEmpWithDep(emp);
    }
    setEditModalVisible(false);
    actionRef.current?.reload();
  };

  // 递归创建部门树
  const MAX_DEPTH = 10;
  function buildChild(id: number, list: Department[], depth: number){
    if(list==null || list.length===0 || depth>MAX_DEPTH){
      return [];
    }
    const cs: any[] = [];
    list?.forEach((e: Department) => {
      if(id===e.pid){
        const childrens = buildChild(e.id, list, depth+1);
        cs.push({ key: e.id, title: e.name, value: e.id, children: childrens });
      }
    });
    return cs;
  }

  const searchRole = (value: string) => {
    return new Promise<TagType[]>((resolve) => {
      limitRole(value).then(({data})=>{
        const ts: TagType[] = [];
        data?.forEach( (e: Role) => {
          ts.push({ id: e.id, text: e.name, closeable: true })
        });
        resolve(ts);
      });
    });
  };

  const deleteRoleHandle = (roleId: number) => {
    return new Promise<boolean>((resolve) => {
      if(!currentDetailEmpId) {
        resolve(false);
        return;
      }
      deleteEmpRole(currentDetailEmpId, roleId).then(()=>{
        resolve(true);
      });
    });
  };

  const addRoleHandle = (roleId: number) => {
    return new Promise<boolean>((resolve) => {
      if(!currentDetailEmpId) {
        resolve(false);
        return;
      }
      addEmpRole(currentDetailEmpId, roleId).then(()=>{
        resolve(true);
      });
    });
  };

  const searchUser = (username: string) => {
    return new Promise<EditableTagValueType[]>((resolve) => {
      limitUnbindUser(username).then(({data})=>{
        const ts: EditableTagValueType[] = [];
        data?.forEach( (e: User) => {
          ts.push({ id: e.id, text: e.username })
        });
        resolve(ts);
      });
    });
  };

  const selectUserHandle = (userId: number) => {
    return new Promise<boolean>((resolve) => {
      if(!currentDetailEmpId) {
        resolve(false);
        return;
      }
      modifyEmp({id: currentDetailEmpId, userId });
    });
  };

  const unbindUser = (tag: EditableTagValueType) => {
    return new Promise<boolean>((resolve) => {
      if(!currentDetailEmpId) {
        resolve(false);
        return;
      }
      empUnbindUser(currentDetailEmpId, tag.id);
      resolve(true);
    });
  };

  const columns: ProColumns<Employ>[] = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'index',
      width: 80,
    },
    {
      title: '姓名',
      key: 'name',
      dataIndex: 'name',
      render: (_, emp: Employ) => <a onClick={ () => showDetail(emp) }>{_}</a>,
    },
    {
      title: '邮箱',
      key: 'email',
      dataIndex: 'email',
    },
    {
      title: '注册时间',
      dataIndex: 'createTime',
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
      render: (_, record: Employ) => [
        <Access accessible={access.permissionFilter('employ#modify')} fallback={<div className={"disabled"}>修改</div>}>
          <a key="modify" onClick={ () => edit(record) }>修改</a>
        </Access>,
        <Access accessible={access.permissionFilter('employ#delete')} fallback={<div className={"disabled"}>删除</div>}>
          <a key="delete" onClick={ () => deleteEmp(record) }>删除</a>
        </Access>
      ],
    },
  ];

  return (
    <>
      <ProTable<Employ>
        actionRef={actionRef}
        columns={columns}
        rowKey="id"
        pagination={{
          showSizeChanger: true,
        }}
        search={{
          filterType: 'light',
        }}
        tableRender={(_, dom) => (
          <div style={{ display: 'flex', width: '100%' }} >
            <DepTree
              visible={true}
              style={{ width: 300 }}
              onSelect={onSelect}
              onRefresh={onRefresh}
            />
            <div style={{ flex: 1 }}>
              {dom}
            </div>
          </div>
        )}
        toolBarRender={() => [
          <Button key="add" disabled={!access.permissionFilter('employ#create')} onClick={ () => add() }>创建</Button>,
        ]}
        params={{
          depId,
        }}
        request={ tableRequest }
        dateFormatter="string"
        headerTitle="员工列表"
        options={{
          density: true,
          fullScreen: true,
          setting: true,
        }}
      />

      <Modal
        width={400}
        visible={editModalVisible}
        title={`${isAdd ? '添加' : '编辑'}员工`}
        okText="确定"
        cancelText="取消"
        onCancel={editOnCancel}
        onOk={() => {
          form
            .validateFields()
            .then(async (emp: any) => {
              const depIds = emp.depIds.map((e: any)=>e.value);
              const primaryDepId = emp && emp.depIds.length>0 ? emp.depIds[0].value : null;
              const param: any = {...emp, depIds, primaryDepId}
              await editSubmiHandle(param);
              form.resetFields();
            })
            .catch(info => {
              console.log('Validate Failed:', info);
            });
        }}
      >
        <Form<Department>
          form={form}
          labelCol={{span: 6}}
          wrapperCol={{span: 16}}
          layout="horizontal"
          name="editModal"
          initialValues={{}}
        >
          <Form.Item name="id" hidden={true}>
            <Input />
          </Form.Item>

          <Form.Item
            name="name" label="名称"
            rules={[
              { required: true, message: '请输入姓名!' },
              { min: 2, message: '长度在2-20个字符串间' },
              { max: 20, message: '长度在2-20个字符串间' }
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            name="email" label="邮箱"
            rules={[
              { required: true, message: '请输入电子邮箱!' },
              { type: 'email', message: '请输入正确的电子邮箱!'}
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item name='depIds' label="部门">
            <TreeSelect

              multiple={true}
              treeCheckable={true}
              treeCheckStrictly={true}
              showCheckedStrategy={'SHOW_PARENT'}
              placeholder={'请设置部门'}
              treeData={depTreeData}
            />
          </Form.Item>

        </Form>
      </Modal>

      <Drawer
        width={540}
        // layout={"horizontal"}
        title="员工信息"
        placement="right"
        closable={true}
        onClose={detailModalOnClose}
        visible={detailModalVisible}
      >

        <p className="site-description-item-profile-p">基本信息</p>
        <Row>
          <Col span={12}>
            <DescriptionItem title="姓名" content="Lily" />
          </Col>
          <Col span={12}>
            <DescriptionItem title="账号" content="AntDesign@example.com" />
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <DescriptionItem title="城市" content="HangZhou" />
          </Col>
          <Col span={12}>
            <DescriptionItem title="国籍" content="China🇨🇳" />
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <DescriptionItem title="生日" content="February 2,1900" />
          </Col>
          <Col span={12}>
            <DescriptionItem title="Website" content="-" />
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <DescriptionItem title="签名" content="Make things as simple as possible but no simpler."/>
          </Col>
        </Row>
        <Divider />
        <p className="site-description-item-profile-p">公司信息</p>
        <Row>
          <Col span={12}>
            <DescriptionItem title="职位" content="Programmer" />
          </Col>
          <Col span={12}>
            <DescriptionItem title="岗位描述" content="Coding" />
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <DescriptionItem title="部门" content="XTech" />
          </Col>
          <Col span={12}>
            <DescriptionItem title="主管" content={<a>Lin</a>} />
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <DescriptionItem title="Skills" content="C / C + +, data structures, software engineering, operating systems, computer networks, databases, compiler theory, computer architecture, Microcomputer Principle and Interface Technology, Computer English, Java, ASP, etc."/>
          </Col>
        </Row>

        <Row>
          <Col span={24}>
            <DescriptionItem title="角色" content={<EditableTagGroup tags={tags} onSearch={searchRole} onClose={deleteRoleHandle} onAdd={addRoleHandle} />}/>
          </Col>
        </Row>

        <Row>
          <Col span={24}>
            <DescriptionItem title="账号" content={<EditableTag value={userTag} onSearch={searchUser} onSelect={selectUserHandle} onDelete={unbindUser} />}/>
          </Col>
        </Row>

        <Divider />
        <p className="site-description-item-profile-p">联系方式</p>
        <Row>
          <Col span={12}>
            <DescriptionItem title="邮箱" content="AntDesign@example.com" />
          </Col>
          <Col span={12}>
            <DescriptionItem title="手机号码" content="+86 181 0000 0000" />
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <DescriptionItem title="Github"
              content={
                <a href="http://github.com/ant-design/ant-design/">
                  github.com/ant-design/ant-design/
                </a>
              }
            />
          </Col>
        </Row>

      </Drawer>
    </>
  );
};
