package cn.hiauth.server.api.vo;

import cn.hiauth.server.entity.Employee;
import cn.hiauth.server.utils.DateTimeUtils;
import cn.webestar.scms.commons.api.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class EmpVo {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "租户ID")
    private Long cid;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "员工号")
    private String no;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "是否为租户管理员")
    private Boolean isCorpAdmin;

    @Schema(description = "所属部门")
    private Set<Long> depIds;

    @Schema(description = "角色")
    private Set<Long> roleIds;

    public static EmpVo convert(Employee o, Set<Long> depIds, Set<Long> roleIds) {
        EmpVo vo = new EmpVo();
        vo.setId(o.getId());
        vo.setCid(o.getCid());
        vo.setUserId(o.getUserId());
        vo.setNo(o.getNo());
        vo.setName(o.getName());
        vo.setEmail(o.getEmail());
        vo.setCreateTime(DateTimeUtils.format(o.getCreateTime()));
        vo.setIsCorpAdmin(o.getIsCorpAdmin());
        vo.setDepIds(depIds);
        vo.setRoleIds(roleIds);
        return vo;
    }

    public static List<EmpVo> convert(List<Employee> os) {
        List<EmpVo> vos = new ArrayList<>();
        os.forEach(i -> vos.add(convert(i, null, null)));
        return vos;
    }

    public static PageVO<EmpVo> toPageVo(IPage<Employee> page) {
        List<EmpVo> vos = convert(page.getRecords());
        PageVO<EmpVo> pageVo = new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), vos);
        return pageVo;
    }

}
