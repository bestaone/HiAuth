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
@TableName(value = "t_dict", autoResultMap = true)
@Schema(name = "Dict", description = "字典")
public class Dict extends BasicDO<Long> {

    @TableField("cid")
    @Schema(description = "cid")
    private Long cid;

    @TableField("sort")
    @Schema(description = "排序")
    private Integer sort;

    @TableField("code")
    @Schema(description = "编码")
    private String code;

    @TableField("p_code")
    @Schema(description = "父编码")
    private String pCode;

    @TableField("name")
    @Schema(description = "名称")
    private String name;

    @TableField("value")
    @Schema(description = "值")
    private String value;

    @TableField("is_enable")
    @Schema(description = "是否启用")
    private Boolean isEnable;

    @TableField("create_time")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @TableField("has_child")
    @Schema(description = "是否有子节点")
    private Boolean hasChild;

}