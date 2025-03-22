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
@TableName(value = "t_sys_log", autoResultMap = true)
@Schema(name = "SysLog", description = "系统日志")
public class SysLog extends BasicDO<Long> {

    @TableField("cid")
    @Schema(description = "cid")
    private Long cid;

    @TableField("actor")
    @Schema(description = "事件触发者")
    private String actor;

    @TableField("actor_ip")
    @Schema(description = "触发者ip")
    private String actorIp;

    @TableField("actor_type")
    @Schema(description = "触发者类型，1：系统，2：员工")
    private Integer actorType;

    @TableField("actor_emp_id")
    @Schema(description = "触发者员工id")
    private Long actorEmpId;

    @TableField("actor_user_id")
    @Schema(description = "事件触发者用户id")
    private Long actorUserId;

    @TableField("event_time")
    @Schema(description = "事件产生时间")
    private LocalDateTime eventTime;

    @TableField("event_type")
    @Schema(description = "事件分类")
    private String eventType;

    @TableField("event_level")
    @Schema(description = "事件等级")
    private Integer eventLevel;

    @TableField("event_result")
    @Schema(description = "事件结果")
    private String eventResult;

    @TableField("event_desc")
    @Schema(description = "事件描述")
    private String eventDesc;

    @TableField("username")
    @Schema(description = "触发者的用户名")
    private String username;

    @TableField("source_sys")
    @Schema(description = "事件产生的系统")
    private String sourceSys;

    @TableField("source_module")
    @Schema(description = "事件产生的模块")
    private String sourceModule;

    @TableField("create_time")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}