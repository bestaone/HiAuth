import EditableTagGroup, {TagType} from "@/components/EditableTagGroup";
import {useEffect, useState} from "react";
import {addEmpRole, deleteEmpRole, getRoleByEmp, limitRole} from "@/pages/org/role-mgr/service";
import {Role} from "@/pages/org/role-mgr/data";

export default () => {

  const [tags, setTags] = useState<TagType[]>([]);
  const [currentDetailEmpId] = useState<number|null>(1);

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

  useEffect(() => {
    getRoleByEmp(1).then(({data})=>{
      const ts: TagType[] = [];
      data?.forEach( (e: Role) => {
        ts.push({ id: e.id, text: e.name, closeable: true })
      });
      setTags(ts);
    });
  }, []);

  return (
    <>
      <EditableTagGroup tags={tags} onSearch={searchRole} onClose={deleteRoleHandle} onAdd={addRoleHandle}></EditableTagGroup>
    </>
  );

};
