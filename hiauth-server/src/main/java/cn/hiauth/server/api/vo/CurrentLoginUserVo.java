package cn.hiauth.server.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CurrentLoginUserVo {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "CID")
    private Long cid;

    @Schema(description = "员工id")
    private Long empId;

    @Schema(description = "名称")
    private String name;
    private String realName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "用户名")
    private String username;

//    @Schema(description = "是否为企业管理员")
//    private Boolean isCorpAdmin;
//
//    @Schema(description = "是否为系统管理员")
//    private Boolean isSysAdmin;

    @Schema(description = "角色")
    private Set<String> roles = new HashSet<>();

}
