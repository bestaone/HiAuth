package cn.hiauth.server.api.vo;

import cn.hiauth.server.entity.App;
import cn.hiauth.server.entity.AppResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CorpResourceTreeNodeVo {

    @Schema(description = "应用id")
    private Long appId;
    @Schema(description = "资源id")
    private Long rid;
    @Schema(description = "1:应用，2：资源")
    private Integer type;
    private String name;
    private List<CorpResourceTreeNodeVo> children = new ArrayList<>();
    ;

    public static CorpResourceTreeNodeVo convertToVo(App o) {
        CorpResourceTreeNodeVo vo = new CorpResourceTreeNodeVo();
        vo.setAppId(o.getId());
        vo.setType(1);
        vo.setName(o.getName());
        return vo;
    }

    public static CorpResourceTreeNodeVo convertToVo(AppResource o) {
        CorpResourceTreeNodeVo vo = new CorpResourceTreeNodeVo();
        vo.setAppId(o.getAppId());
        vo.setRid(o.getId());
        vo.setType(2);
        vo.setName(o.getName());
        return vo;
    }

}
