package cn.hiauth.server.api.dto.app;

import cn.hiauth.server.entity.App;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class AppPageDto extends PageBody {

    @TableField("cid")
    @Schema(description = "创建应用的企业CID")
    private Long cid;

    @Schema(description = "名称")
    private String name;

    @Override
    public LambdaQueryWrapper<App> toQueryWapper() {
        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(App::getCid, cid);
        }
        if (StringUtils.hasText(name)) {
            queryWrapper.like(App::getName, name);
        }
        return queryWrapper;
    }

}
