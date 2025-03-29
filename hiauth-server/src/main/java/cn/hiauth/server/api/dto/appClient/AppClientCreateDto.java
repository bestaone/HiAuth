package cn.hiauth.server.api.dto.appClient;

import cn.hiauth.server.entity.CorpApp;
import cn.webestar.scms.commons.api.CreateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppClientCreateDto extends CreateBody {

    @Schema(description = "租户")
    private Long corpId;

    @Schema(description = "应用id")
    @NotNull(message = "不能为NULL")
    private Long[] appIds;

    @Override
    public CorpApp toDO() {
        return null;
    }

    public List<CorpApp> toDos() {
        List<CorpApp> list = new ArrayList<>();
        if (appIds != null && appIds.length > 0) {
            for (Long appId : appIds) {
                CorpApp o = new CorpApp();
                o.setCorpId(corpId);
                o.setAppId(appId);
                list.add(o);
            }
        }
        return list;
    }

}
