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
@TableName(value = "t_corp_app", autoResultMap = true)
@Schema(name = "CorpApp", description = "租户应用关联")
public class CorpApp extends BasicDO<Long> {

    @TableField("app_id")
    @Schema(description = "应用id")
    private Long appId;

    @TableField("corp_id")
    @Schema(description = "租户id")
    private Long corpId;

}