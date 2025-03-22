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
@TableName(value = "t_file", autoResultMap = true)
@Schema(name = "File", description = "文件")
public class File extends BasicDO<Long> {

    @TableField("origin_name")
    @Schema(description = "原始名称")
    private String originName;

    @TableField("code")
    @Schema(description = "编码")
    private String code;

    @TableField("name")
    @Schema(description = "名称")
    private String name;

    @TableField("ext")
    @Schema(description = "扩展名")
    private String ext;

    @TableField("data")
    @Schema(description = "数据")
    private byte[] data;

}