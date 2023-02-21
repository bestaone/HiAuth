/**
 * @see https://umijs.org/zh-CN/plugins/plugin-access
 * */
export default function access(initialState: { currentResources: Set<string>, currentPermisstions: Set<string> }) {
  const { currentResources, currentPermisstions } = initialState || {};
  return {
    permissionFilter: (permission: string) => {
      return currentPermisstions.has(permission);
    },
    resourceFilter: (key: string) => {
      return currentResources.has(key);
    },
    resourceRouteFilter: (route: any) => {
      const {resourceKey} = route;
      return currentResources.has(resourceKey);
    }
  };
}
