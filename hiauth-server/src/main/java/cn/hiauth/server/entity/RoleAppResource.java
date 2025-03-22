package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_role_app_resource", autoResultMap = true)
@Schema(name = "RoleAppResource", description = "角色应用资源关联")
public class RoleAppResource extends BasicDO<Long> {

    @TableField("app_id")
    @Schema(description = "应用id")
    private Long appId;

    @TableField("cid")
    @Schema(description = "租户id")
    private Long cid;

    @TableField("role_id")
    @Schema(description = "角色id")
    private Long roleId;

    @TableField("app_resource_id")
    @Schema(description = "应用资源id")
    private Long appResourceId;

}