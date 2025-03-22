package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_department", autoResultMap = true)
@Schema(name = "Department", description = "部门")
public class Department extends BasicDO<Long> {

    @TableField("cid")
    @Schema(description = "租户id")
    private Long cid;

    @TableField("pid")
    @Schema(description = "父部门")
    private Long pid;

    @TableField("sort")
    @Schema(description = "排序号")
    private Integer sort;

    @TableField("no")
    @Schema(description = "部门编码")
    private String no;

    @TableField("name")
    @Schema(description = "部门名称")
    private String name;

    @TableField("creator")
    @Schema(description = "创建人")
    private Long creator;

    @TableField("updater")
    @Schema(description = "更新人")
    private Long updater;

    @TableField("create_time")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @TableField("update_time")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @TableField("status")
    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @TableField("remark")
    @Schema(description = "备注")
    private String remark;

}