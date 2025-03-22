package cn.hiauth.server.api.vo;

import cn.hiauth.server.entity.AppResource;
import cn.hiauth.server.entity.Department;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommonTreeNodeVo {

    private Long id;
    private String name;
    private List<CommonTreeNodeVo> children = new ArrayList<>();
    ;

    public static CommonTreeNodeVo convertToVo(AppResource o) {
        CommonTreeNodeVo vo = new CommonTreeNodeVo();
        vo.setId(o.getId());
        vo.setName(o.getName());
        return vo;
    }

    public static CommonTreeNodeVo convertToVo(Department o) {
        CommonTreeNodeVo vo = new CommonTreeNodeVo();
        vo.setId(o.getId());
        vo.setName(o.getName());
        return vo;
    }

}
