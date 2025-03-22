package cn.hiauth.server.api.dto.appResource;

import cn.hiauth.server.entity.AppResource;
import cn.webestar.scms.commons.api.CreateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class AppResourceCreateDto extends CreateBody {

    @Schema(description = "应用id")
    @NotNull(message = "不能为NULL")
    private Long appId;

    @Schema(description = "父节点")
    private Long pid;

    @Schema(description = "名称")
    @NotBlank(message = "不能为空")
    @Size(min = 5, max = 20, message = "长度必须在5到20个字符之间")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "编码")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 100, message = "长度必须在2到100个字符之间")
    private String code;

    @Schema(description = "URL")
    @Size(max = 100, message = "长度不能超过100")
    private String url;

    @Schema(description = "api")
    @Size(max = 100, message = "长度不能超过100")
    private String api;

    @Schema(description = "资源类型，1：目录、菜单，2：页面，3：功能、接口")
    @NotNull(message = "不能为空")
    private Integer type;

    @Schema(description = "备注")
    @Size(max = 100, message = "长度不能超过100")
    private String remark;

    @Schema(description = "扩展字段")
    private Map<String, ?> extend;

    @Override
    public AppResource toDO() {
        AppResource o = new AppResource();
        o.setAppId(appId);
        o.setPid(pid);
        o.setName(name);
        o.setSort(sort);
        o.setCode(code);
        o.setUrl(url);
        o.setApi(api);
        o.setType(type);
        o.setRemark(remark);
        o.setExtend(extend);
        return o;
    }

}
