package cn.hiauth.server.service.impl;

import cn.hiauth.server.api.dto.RegisterDto;
import cn.hiauth.server.api.vo.CorpResourceTreeNodeVo;
import cn.hiauth.server.api.vo.IndexCorpAppVo;
import cn.hiauth.server.entity.*;
import cn.hiauth.server.mapper.*;
import cn.hiauth.server.service.CorpService;
import cn.hutool.core.lang.Snowflake;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.SysCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 租户
 */
@Service
public class CorpServiceImpl extends ServiceImpl<CorpMapper, Corp> implements CorpService {

    @Autowired
    private Snowflake idGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppResourceMapper appResourceMapper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private CorpMapper corpMapper;

    @Autowired
    private CorpAppMapper corpAppMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    private static void buildTree(CorpResourceTreeNodeVo parent, List<AppResource> list, int depth) {
        for (AppResource o : list) {
            if (o.getPid() == null && o.getAppId() != null && o.getAppId().equals(parent.getAppId())) {
                CorpResourceTreeNodeVo vo = CorpResourceTreeNodeVo.convertToVo(o);
                buildChild(vo, list, 1);
                parent.getChildren().add(vo);
            }
        }
    }

    private static void buildChild(CorpResourceTreeNodeVo parent, List<AppResource> list, int depth) {
        if (depth > 10) {
            return;
        }
        if (parent != null && list != null && !list.isEmpty()) {
            list.forEach(o -> {
                if (o.getPid() != null && o.getPid().equals(parent.getRid())) {
                    CorpResourceTreeNodeVo child = CorpResourceTreeNodeVo.convertToVo(o);
                    buildChild(child, list, depth + 1);
                    parent.getChildren().add(child);
                }
            });
        }
    }

    @Override
    public List<CorpResourceTreeNodeVo> findCorpAppResourceTree(Long cid) {

        // 查询应用
        List<App> apps = appMapper.findByCid(cid);
        Set<Long> appIds = apps.stream().map(App::getId).collect(Collectors.toSet());

        // 查询应用资源
        LambdaQueryWrapper<AppResource> qw2 = new LambdaQueryWrapper<>();
        qw2.in(AppResource::getAppId, appIds);
        List<AppResource> appResources = appResourceMapper.selectList(qw2);
        appResources.sort(Comparator.comparing(AppResource::getSort));

        // 构建树
        List<CorpResourceTreeNodeVo> vos = new ArrayList<>();
        apps.forEach(o -> {
            CorpResourceTreeNodeVo vo = CorpResourceTreeNodeVo.convertToVo(o);
            buildTree(vo, appResources, 1);
            vos.add(vo);
        });

        return vos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterDto dto) {
        LambdaQueryWrapper<Corp> corpQw = new LambdaQueryWrapper<>();
        corpQw.eq(Corp::getName, dto.getCorpName());
        boolean corpExists = corpMapper.exists(corpQw);
        Assert.isTrue(!corpExists, SysCode.ERROR.getCode(), "租户已存在");

        LambdaQueryWrapper<User> userQw = new LambdaQueryWrapper<>();
        userQw.eq(User::getUsername, dto.getUsername()).or().eq(User::getPhoneNum, dto.getPhoneNum());
        boolean userExists = userMapper.exists(userQw);
        Assert.isTrue(!userExists, SysCode.ERROR.getCode(), "用户已存在");

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        Corp corp = dto.toCorp();
        corp.setId(idGenerator.nextId());
        corpMapper.insert(corp);

        User user = dto.toUser();
        user.setId(idGenerator.nextId());
        userMapper.insert(user);

        Employee employee = new Employee();
        employee.setId(idGenerator.nextId());
        employee.setCid(corp.getId());
        employee.setName(user.getName());
        employee.setUserId(user.getId());
        employee.setIsCorpAdmin(true);
        employeeMapper.insert(employee);
    }

    @Override
    public List<IndexCorpAppVo> findIndexCorpAppByUserId(Long userId) {
        List<CorpAppInfo> cpis = corpAppMapper.limitCorpAppInfoByUserId(userId);
        return IndexCorpAppVo.convert(cpis);
    }

}