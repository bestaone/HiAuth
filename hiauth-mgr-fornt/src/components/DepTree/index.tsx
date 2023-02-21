import {DeleteOutlined, DiffOutlined, ExclamationCircleOutlined, FormOutlined, RedoOutlined} from '@ant-design/icons';
import {Button, Card, Form, Input, InputNumber, Modal, Tree, TreeDataNode} from 'antd';
import React, {CSSProperties, useEffect, useState} from 'react';
import {addDep, deleteDepById, findDepAll, modifyDep} from "@/components/DepTree/service";
import {Department} from "@/components/DepTree/data";
import {useAccess} from "@@/plugin-access/access";

export type DepTreeProps = {
  visible?: boolean;
  style?: CSSProperties | undefined;
  onSelect?: any;
  onRefresh?: any;
};

const DepTree: React.FC<DepTreeProps> = (props) => {

  const MAX_DEPTH = 10;

  const access = useAccess();
  const [depList, setDepList] = useState<Department[]>([]);
  const [treeData, setTreeData] = useState<TreeDataNode[]>([]);
  const [expandedKeys, setExpandedKeys] = useState<string[]>([]);
  const [autoExpandParent, setAutoExpandParent] = useState<boolean>(true);
  const [currentDepId, setCurrentDepId] = useState<number|null>();
  const [editModalVisible, setEditModalVisible] = useState<boolean>(false);
  const [isAdd, setIsAdd] = useState<boolean>(false);

  useEffect(() => {
    reload();
  }, []);

  function refresh(){
    props.onRefresh();
    reload();
  }

  function reload(){
    findDepAll({}).then(({ data }) => {
      setDepList(data||[]);
      resetTree(data||[]);
    });
  }

  function resetTree(deps: Department[]){
    const tree = buildChild(0, deps, 0);
    setTreeData(tree);
  }

  function buildChild(id: number, list: Department[], depth: number){
    if(list==null || list.length===0 || depth>MAX_DEPTH){
      return [];
    }
    const cs: TreeDataNode[] = [];
    list?.forEach((e: Department) => {
      if(id===e.pid){
        const childrens = buildChild(e.id, list, depth+1);
        cs.push({ key: `${e.id}`, title: e.name, children: childrens });
      }
    });
    return cs;
  }

  const getDepartmentById = (id: number) => {
    return depList.find((dep: Department) => {
      return dep.id===id ? dep : null;
    })
  };

  const onChange = (e: any) => {
    setAutoExpandParent(true);
    const {value} = e.target;
    const keys: string[] = depList
      .map(item => {
        if (value && item.name.indexOf(value) > -1) {
          return item.pid.toString();
        }
        return '';
      })
      .filter((item, i, self) => {
        return item && self.indexOf(item) === i;
      });
    setExpandedKeys(keys);
  };

  const onExpand = (keys: any) => {
    setExpandedKeys(keys);
    setAutoExpandParent(false);
  };

  const onSelect = (ids: any) => {
    const depId = Number(ids[0]);
    props.onSelect(depId);
    setCurrentDepId(depId);
  };

  const deleteDep = () => {
     if(currentDepId) {
       Modal.confirm({
         title: '是否确认删除部门?',
         icon: <ExclamationCircleOutlined />,
         content: '部门下有子部门或者有员工将无法删除',
         okText: '是',
         okType: 'danger',
         cancelText: '否',
         onOk() {
           deleteDepById({id:currentDepId});
           const deps = depList.filter((item: Department) => {
             return item.id!==currentDepId;
           });
           setDepList(deps);
           resetTree(deps);
           setCurrentDepId(null);
         },
         onCancel() {},
       });
     } else {
       Modal.warning({
         title: '请先选择一个部门?',
         icon: <ExclamationCircleOutlined />,
         content: '一次只能选择一个部门'
       });
     }
  };

  const [form] = Form.useForm();

  const add = () => {
    form.resetFields();
    form.setFieldsValue({pid:currentDepId});
    setEditModalVisible(true);
    setIsAdd(true);
  };

  const edit = () => {
    if(currentDepId) {
      const dep = getDepartmentById(currentDepId);
      form.setFieldsValue(dep)
      setEditModalVisible(true);
      setIsAdd(false);
    } else {
      Modal.warning({
        title: '请先选择一个部门.',
        icon: <ExclamationCircleOutlined />,
        content: '请选择要编辑的部门。'
      });
    }
  };

  const editOnCancel = () => {
    setEditModalVisible(false);
  }

  const editSubmiHandle = async (dep: Department) => {
    if(dep.id){
      await modifyDep(dep);
    } else {
      await addDep(dep);
    }
    setEditModalVisible(false);
    reload();
  };

  return (
    <>
      <Card
        title="部门树"
        style={props.style}
        extra={
          <>
            <Button type="text" size='small' icon={<RedoOutlined onClick={refresh}/>} />
            <Button type="text" size='small' disabled={!access.permissionFilter('department#create')} icon={<DiffOutlined onClick={add}/>} />
            <Button type="text" size='small' disabled={!access.permissionFilter('department#modify')} icon={<FormOutlined onClick={edit}/>} />
            <Button type="text" size='small' disabled={!access.permissionFilter('department#delete')} icon={<DeleteOutlined onClick={deleteDep}/>} />
          </>
        }
      >
        <Input placeholder="搜索部门" style={{ marginBottom:'10PX'}} onChange={onChange}/>
        <Tree
          onSelect={onSelect}
          onExpand={onExpand}
          autoExpandParent={autoExpandParent}
          expandedKeys={expandedKeys}
          treeData={treeData}
        >
        </Tree>
      </Card>
      <Modal
        width={300}
        visible={editModalVisible}
        title={`${isAdd ? '添加' : '编辑'}部门`}
        okText="确定"
        cancelText="取消"
        onCancel={editOnCancel}
        onOk={() => {
          form
            .validateFields()
            .then(async (dep: Department) => {
              await editSubmiHandle(dep);
              form.resetFields();
            })
            .catch(info => {
              console.log('Validate Failed:', info);
            });
        }}
      >
        <Form<Department>
          form={form}
          layout="horizontal"
          name="form_in_modal"
          initialValues={{}}
        >
          <Form.Item name="id" hidden={true}>
            <Input />
          </Form.Item>
          <Form.Item name="pid" hidden={true}>
            <Input />
          </Form.Item>
          <Form.Item name="name" label="名称" rules={[{ required: true, message: '请输入部门名称!' }]}>
            <Input />
          </Form.Item>
          <Form.Item name="sort" label="排序" rules={[{ required: true, message: '请输入排序号!' }]}>
            <InputNumber min={0} max={99999} />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};

export default DepTree;
