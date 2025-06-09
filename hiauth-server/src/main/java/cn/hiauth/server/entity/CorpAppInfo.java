package cn.hiauth.server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CorpAppInfo {

    @Schema(description = "租户id")
    private Long corpId;

    @Schema(description = "租户名称")
    private String corpName;

    @Schema(description = "应用id")
    private Long appId;

    @Schema(description = "应用名称")
    private String appName;

    @Schema(description = "应用首页")
    private String appHome;

    @Schema(description = "应用说明")
    private String appRemark;

}