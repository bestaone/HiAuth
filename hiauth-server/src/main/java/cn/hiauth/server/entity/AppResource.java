package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_app_resource", autoResultMap = true)
@Schema(name = "AppResource", description = "系统资源")
public class AppResource extends BasicDO<Long> {

    @TableField("pid")
    @Schema(description = "父节点")
    private Long pid;

    @TableField("app_id")
    @Schema(description = "应用id")
    private Long appId;

    @TableField("code")
    @Schema(description = "编码")
    private String code;

    @TableField("url")
    @Schema(description = "URL")
    private String url;

    @TableField("api")
    @Schema(description = "api")
    private String api;

    @TableField("sort")
    @Schema(description = "排序")
    private Integer sort;

    @TableField("name")
    @Schema(description = "名称")
    private String name;

    @TableField("type")
    @Schema(description = "资源类型，1：目录、菜单，2：页面，3：功能、接口")
    private Integer type;

    @TableField("remark")
    @Schema(description = "备注")
    private String remark;

    @Schema(description = "扩展字段")
    @TableField(value = "extend", typeHandler = JacksonTypeHandler.class)
    private Map<String, ?> extend;

}