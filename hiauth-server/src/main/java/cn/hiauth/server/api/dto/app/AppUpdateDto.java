package cn.hiauth.server.api.dto.app;

import cn.hiauth.server.entity.App;
import cn.webestar.scms.commons.api.UpdateBody;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppUpdateDto extends UpdateBody {

    @Schema(description = "id")
    private Long id;

    @TableField("cid")
    @Schema(description = "创建应用的企业CID")
    private Long cid;

    @Schema(description = "图标")
    @Size(max = 200, message = "长度不能超过200")
    private String icon;

    @Schema(description = "名称")
    @NotBlank(message = "不能为空")
    @Size(min = 5, max = 20, message = "长度必须在5到20个字符之间")
    private String name;

    @Schema(description = "备注")
    @Size(max = 100, message = "长度不能超过100")
    private String remark;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public App toDO() {
        App o = new App();
        o.setId(id);
        o.setCid(cid);
        o.setIcon(icon);
        o.setName(name);
        o.setRemark(remark);
        return o;
    }


}
