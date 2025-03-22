package cn.hiauth.server.api.vo;

import cn.hiauth.server.entity.App;
import cn.hiauth.server.entity.Oauth2RegisteredClient;
import cn.webestar.scms.commons.api.PageVO;
import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jayway.jsonpath.JsonPath;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class CorpAppVo extends BasicDO<String> {

    @Schema(description = "租户ID")
    private Long cid;

    @Schema(description = "租户ID")
    private Long appId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "说明")
    private String remark;


    @Schema(description = "clientId")
    private String clientId;

    @Schema(description = "")
    private String clientSecret;

    @TableField("client_name")
    @Schema(description = "")
    private String clientName;

    @Schema(description = "")
    private String redirectUris;

    @Schema(description = "")
    private String scopes;

    @Schema(description = "token有效期，分钟")
    private Integer accessTokenTimeToLive;

    public static CorpAppVo convert(Oauth2RegisteredClient o, App app) {
        CorpAppVo vo = new CorpAppVo();
        vo.setId(o.getId());
        vo.setCid(o.getCid());
        vo.setAppId(o.getAppId());
        vo.setClientId(o.getClientId());
//        vo.setClientSecret(o.getClientSecret());
//        if(!StringUtils.isBlank(o.getClientSecret())){
//            vo.setClientSecret("**********");
//        }
        vo.setClientName(o.getClientName());
        vo.setRedirectUris(o.getRedirectUris());
        vo.setScopes(o.getScopes());
        vo.setName(app.getName());
        vo.setIcon(app.getIcon());
        vo.setRemark(app.getRemark());
        try {
            Integer accessTokenTimeToLive = JsonPath.read(o.getTokenSettings(), "$.['settings\\.token\\.access-token-time-to-live'][1]");
            vo.setAccessTokenTimeToLive(accessTokenTimeToLive / 3600);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public static List<CorpAppVo> convert(List<Oauth2RegisteredClient> os, Map<Long, App> apps) {
        List<CorpAppVo> vos = new ArrayList<>();
        os.forEach(i -> {
            App app = apps.get(i.getAppId());
            if (app != null) {
                vos.add(convert(i, app));
            }
        });
        return vos;
    }

    public static PageVO<CorpAppVo> toPageVo(IPage<Oauth2RegisteredClient> page, Map<Long, App> apps) {
        List<CorpAppVo> vos = convert(page.getRecords(), apps);
        PageVO<CorpAppVo> pageVo = new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), vos);
        return pageVo;
    }

}