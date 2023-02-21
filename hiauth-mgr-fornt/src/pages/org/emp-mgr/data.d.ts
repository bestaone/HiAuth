import {User} from "@/pages/sys/user-mgr/data";

export type Employ = {
  id: number;
  name: string;
  email: string;
  createTime: number;
  status: number;
  depIds: number[];
  primaryDepId: number;
  roles?: Role[];
  user?: User;
};

export type EmployEdit = {
  id: number;
  userId?: number;
  name?: string;
  email?: string;
  resume?: string;
  adCode?: number;
  address?: string;
  telephone?: string;
  status?: number;
}

export type Department = {
  id: number;
  pid: number;
  name: string;
  createTime: number;
};
