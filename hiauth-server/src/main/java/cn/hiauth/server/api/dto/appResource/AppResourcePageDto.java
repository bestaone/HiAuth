package cn.hiauth.server.api.dto.appResource;

import cn.hiauth.server.entity.AppResource;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class AppResourcePageDto extends PageBody {

    @Schema(description = "应用id")
    @NotNull(message = "不能为NULL")
    private Long appId;

    @Schema(description = "父节点")
    private Long pid;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "URL")
    private String url;

    @Schema(description = "api")
    private String api;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "资源类型，1：目录、菜单，2：页面，3：功能、接口")
    private Integer type;

    @Override
    public LambdaQueryWrapper<AppResource> toQueryWapper() {
        LambdaQueryWrapper<AppResource> queryWrapper = new LambdaQueryWrapper<>();
        if (appId != null) {
            queryWrapper.eq(AppResource::getAppId, appId);
        }
        if (pid != null) {
            queryWrapper.eq(AppResource::getPid, pid);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(AppResource::getCode, keyword)
                    .or().like(AppResource::getUrl, keyword)
                    .or().like(AppResource::getApi, keyword)
                    .or().like(AppResource::getName, keyword)
            );
        }
        if (StringUtils.hasText(code)) {
            queryWrapper.like(AppResource::getCode, code);
        }
        if (StringUtils.hasText(url)) {
            queryWrapper.like(AppResource::getUrl, url);
        }
        if (StringUtils.hasText(api)) {
            queryWrapper.like(AppResource::getApi, api);
        }
        if (StringUtils.hasText(name)) {
            queryWrapper.like(AppResource::getName, name);
        }
        if (type != null) {
            queryWrapper.eq(AppResource::getType, type);
        }
        return queryWrapper;
    }

}
