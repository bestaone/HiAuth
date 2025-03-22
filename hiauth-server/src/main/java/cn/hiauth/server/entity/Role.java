package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_role", autoResultMap = true)
@Schema(name = "Role", description = "角色")
public class Role extends BasicDO<Long> {

    @TableField("cid")
    @Schema(description = "cid")
    private Long cid;

    @TableField("name")
    @Schema(description = "名称")
    private String name;

    @TableField("remark")
    @Schema(description = "备注")
    private String remark;

    @TableField("creator")
    @Schema(description = "创建人")
    private Long creator;

    @TableField("updater")
    @Schema(description = "更新人")
    private Long updater;

    @TableField("create_time")
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @TableField("update_time")
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    @TableField("is_enable")
    @Schema(description = "是否可用")
    private Boolean isEnable;

}