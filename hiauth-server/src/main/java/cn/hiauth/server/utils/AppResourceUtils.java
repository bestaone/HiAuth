package cn.hiauth.server.utils;

import cn.hiauth.server.entity.AppResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppResourceUtils {

    public static List<AppResource> sort(List<AppResource> datas) {
        // 找到所有根节点
        List<AppResource> root = new ArrayList<>();
        datas.forEach(i -> {
            if (i.getPid() == null) {
                root.add(i);
            }
        });
        // 从根节点开始构建
        List<AppResource> list = new ArrayList<>();
        root.forEach(i -> {
            list.add(i);
            list.addAll(getChild(i, datas));
        });
        return list;
    }

    private static List<AppResource> getChild(AppResource node, List<AppResource> datas) {
        List<AppResource> list = new ArrayList<>();
        if (node != null) {
            datas.forEach(i -> {
                if (Objects.equals(node.getId(), i.getPid())) {
                    list.add(i);
                    list.addAll(getChild(i, datas));
                }
            });
        }
        return list;
    }

}
