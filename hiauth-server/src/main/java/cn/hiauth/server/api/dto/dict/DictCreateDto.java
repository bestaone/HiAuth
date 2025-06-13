package cn.hiauth.server.api.dto.dict;

import cn.hiauth.server.entity.Dict;
import cn.webestar.scms.commons.api.CreateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictCreateDto extends CreateBody {

    @Schema(description = "cid")
    private Long cid;

    @Schema(description = "排序")
    private Integer sort;

    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 50, message = "长度必须在2到50个字符之间")
    @Schema(description = "编码")
    private String code;

    @Size(min = 2, max = 50, message = "长度必须在2到50个字符之间")
    @Schema(description = "父编码")
    private String pcode;

    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 20, message = "长度必须在2到20个字符之间")
    @Schema(description = "名称")
    private String name;

    @NotBlank(message = "不能为空")
    @Size(min = 1, max = 200, message = "长度必须在2到200个字符之间")
    @Schema(description = "值")
    private String value;

    @Override
    public Dict toDO() {
        Dict o = new Dict();
        o.setSort(sort);
        o.setCode(code);
        o.setPCode(pcode);
        o.setName(name);
        o.setValue(value);
        o.setIsEnable(true);
        o.setHasChild(false);
        o.setCreateTime(LocalDateTime.now());
        return o;
    }

}
