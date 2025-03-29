package cn.hiauth.server.api.dto.appClient;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class AppClientPageDto extends PageBody {

    @Schema(description = "租户id")
    private Long cid;

    @Schema(description = "关键字")
    private String keyword;

    @Override
    public LambdaQueryWrapper<Oauth2RegisteredClient> toQueryWapper() {
        LambdaQueryWrapper<Oauth2RegisteredClient> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(Oauth2RegisteredClient::getCid, cid);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Oauth2RegisteredClient::getClientName, keyword)
                    .or().like(Oauth2RegisteredClient::getClientId, keyword)
                    .or().like(Oauth2RegisteredClient::getScopes, keyword)
            );
        }
        return queryWrapper;
    }

}
