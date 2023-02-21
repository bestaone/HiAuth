
export type Department = {
  id: number;
  pid: number;
  name: string;
  sort?: number;
  childrens: Department[];
};
