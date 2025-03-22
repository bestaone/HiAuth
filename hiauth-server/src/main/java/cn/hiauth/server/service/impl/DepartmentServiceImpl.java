package cn.hiauth.server.service.impl;

import cn.hiauth.server.api.vo.CommonTreeNodeVo;
import cn.hiauth.server.entity.Department;
import cn.hiauth.server.mapper.DepartmentMapper;
import cn.hiauth.server.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * 部门
 *
 * @author zgs
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private static List<CommonTreeNodeVo> buildDepTree(List<Department> list) {
        list.sort(Comparator.comparing(Department::getSort));
        List<CommonTreeNodeVo> vos = new ArrayList<>();
        for (Department o : list) {
            if (o.getPid() == null) {
                CommonTreeNodeVo vo = CommonTreeNodeVo.convertToVo(o);
                buildChild(vo, list, 1);
                vos.add(vo);
            }
        }
        return vos;
    }

    private static void buildChild(CommonTreeNodeVo parent, List<Department> list, int depth) {
        if (depth > 10) return;
        if (parent != null && list != null && !list.isEmpty()) {
            list.forEach(o -> {
                if (o.getPid() != null && o.getPid().equals(parent.getId())) {
                    CommonTreeNodeVo child = CommonTreeNodeVo.convertToVo(o);
                    buildChild(child, list, depth + 1);
                    parent.getChildren().add(child);
                }
            });
        }
    }

    @Override
    public Set<Long> findDepIdsByEmpId(Long cid, Long empId) {
        return baseMapper.findDepIdsByEmpId(cid, empId);
    }

    @Override
    public List<CommonTreeNodeVo> findDepTree(Long cid) {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(Department::getCid, cid);
        }
        List<Department> deps = baseMapper.selectList(queryWrapper);
        return buildDepTree(deps);
    }

}