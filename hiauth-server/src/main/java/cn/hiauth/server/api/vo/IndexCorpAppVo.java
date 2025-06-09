package cn.hiauth.server.api.vo;

import cn.hiauth.server.entity.CorpAppInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class IndexCorpAppVo {

    private Long cid;
    private String corpName;
    private List<CorpAppInfo> apps = new ArrayList<>();

    public static List<IndexCorpAppVo> convert(List<CorpAppInfo> cais) {
        List<IndexCorpAppVo> vos = new ArrayList<>();
        Map<Long, IndexCorpAppVo> map = new HashMap<>();
        if (cais != null && !cais.isEmpty()) {
            cais.forEach(o -> {
                IndexCorpAppVo cai = map.get(o.getCorpId());
                if (cai == null) {
                    cai = new IndexCorpAppVo();
                    cai.setCid(o.getCorpId());
                    cai.setCorpName(o.getCorpName());
                    cai.setApps(new ArrayList<>());
                    map.put(o.getCorpId(), cai);
                }
                cai.getApps().add(o);
            });
        }
        vos = map.values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return vos;
    }

}
