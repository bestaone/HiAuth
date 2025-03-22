package cn.hiauth.server.api.vo;

import cn.hiauth.server.entity.Corp;
import cn.webestar.scms.commons.api.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CorpVo {

    private Long id;
    private String name;
    private String icon;
    private Integer depCount;
    private Integer empCount;
    private Integer appCount;
    private Integer status;

    public static CorpVo convert(Corp corp) {
        CorpVo vo = new CorpVo();
        vo.setId(corp.getId());
        vo.setName(corp.getName());
        vo.setIcon(corp.getIcon());
        vo.setAppCount(corp.getAppCount());
        vo.setDepCount(corp.getDepCount());
        vo.setEmpCount(corp.getEmpCount());
        vo.setStatus(corp.getStatus());
        return vo;
    }

    public static List<CorpVo> convert(List<Corp> corps) {
        List<CorpVo> corpVos = new ArrayList<>();
        corps.forEach(i -> corpVos.add(convert(i)));
        return corpVos;
    }

    public static PageVO<CorpVo> toPageVo(IPage<Corp> page) {
        List<CorpVo> vos = convert(page.getRecords());
        PageVO<CorpVo> pageVo = new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), vos);
        return pageVo;
    }
}
