package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_corp", autoResultMap = true)
@Schema(name = "Corp", description = "租户")
public class Corp extends BasicDO<Long> {

    @TableField("name")
    @Schema(description = "名称")
    private String name;

    @TableField("icon")
    @Schema(description = "图标")
    private String icon;

    @TableField("app_count")
    @Schema(description = "应用数")
    private Integer appCount;

    @TableField("dep_count")
    @Schema(description = "部门数")
    private Integer depCount;

    @TableField("emp_count")
    @Schema(description = "员工数")
    private Integer empCount;

    @TableField("status")
    @Schema(description = "状态，1：正常")
    private Integer status;

    @TableField("creator")
    @Schema(description = "创建人")
    private Long creator;

    @TableField("updater")
    @Schema(description = "更新人")
    private Long updater;

    @TableField("deleter")
    @Schema(description = "删除人")
    private Long deleter;

    @TableField("create_time")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @TableField("update_time")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @TableField("delete_time")
    @Schema(description = "删除时间")
    private LocalDateTime deleteTime;

    @TableLogic(value = "false", delval = "true")
    @TableField("is_deleted")
    @Schema(description = "是否已删除")
    private Boolean isDeleted;

}