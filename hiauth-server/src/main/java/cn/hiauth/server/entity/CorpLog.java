package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_corp_log", autoResultMap = true)
@Schema(name = "CorpLog", description = "企业日志")
public class CorpLog extends BasicDO<Long> {

    @TableField("operator")
    @Schema(description = "操作人")
    private String operator;

    @TableField("opt_time")
    @Schema(description = "操作时间")
    private LocalDateTime optTime;

    @TableField("opt_ip")
    @Schema(description = "操作id")
    private String optIp;

    @TableField("opt_desc")
    @Schema(description = "操作描述")
    private String optDesc;

    @TableField("opt_type")
    @Schema(description = "操作类型，增删改查等")
    private String optType;

    @TableField("source_sys")
    @Schema(description = "来源系统")
    private String sourceSys;

    @TableField("source_target")
    @Schema(description = "来源，页面或者模块等")
    private String sourceTarget;

    @TableField("source_api")
    @Schema(description = "来源接口")
    private String sourceApi;

    @Schema(description = "源数据")
    @TableField(value = "metadata", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

}