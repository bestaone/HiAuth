package cn.hiauth.server.utils;

import cn.hiauth.server.entity.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DepartmentUtils {

    public static List<Department> sort(List<Department> deps) {
        // 找到所有根节点
        List<Department> root = new ArrayList<>();
        deps.forEach(i -> {
            if (i.getPid() == null) {
                root.add(i);
            }
        });
        // 从根节点开始构建
        List<Department> list = new ArrayList<>();
        root.forEach(i -> {
            list.add(i);
            list.addAll(getChild(i, deps));
        });
        return list;
    }

    private static List<Department> getChild(Department dep, List<Department> deps) {
        List<Department> list = new ArrayList<>();
        if (dep != null) {
            deps.forEach(i -> {
                if (Objects.equals(dep.getId(), i.getPid())) {
                    list.add(i);
                    list.addAll(getChild(i, deps));
                }
            });
        }
        return list;
    }

}
