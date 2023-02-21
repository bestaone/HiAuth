
export type ResourceGroup = {
  id: number;
  name: string;
  remark?: string
};

export type Resource = {
  id: number;
  groupId: number;
  pid: number;
  name: string;
  type: string;
  code: string;
  remark?: string;
};

export type Permission = {
  id: number;
  name: string;
  code: string;
  remark?: string;
};

export type ResourceInfo = {
  id: number;
  groupId: number;
  pid: number;
  name: string;
  type: string;
  code: string;
  permissions?: Permission[];
};
