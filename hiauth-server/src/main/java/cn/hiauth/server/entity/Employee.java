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
@TableName(value = "t_employee", autoResultMap = true)
@Schema(name = "Employee", description = "员工")
public class Employee extends BasicDO<Long> {

    @TableField("cid")
    @Schema(description = "租户ID")
    private Long cid;

    @TableField("user_id")
    @Schema(description = "用户id")
    private Long userId;

    @TableField("no")
    @Schema(description = "员工号")
    private String no;

    @TableField("name")
    @Schema(description = "姓名")
    private String name;

    @TableField("email")
    @Schema(description = "邮箱")
    private String email;

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

    @TableField("is_corp_admin")
    @Schema(description = "是否为租户管理员")
    private Boolean isCorpAdmin;

    @TableLogic(value = "false", delval = "true")
    @TableField("is_deleted")
    @Schema(description = "是否已删除")
    private Boolean isDeleted;

}