package cn.hiauth.server.controller.corpspace;

import cn.hiauth.server.api.dto.appClient.AppClientCreateDto;
import cn.hiauth.server.api.dto.appClient.AppClientLimitDto;
import cn.hiauth.server.api.dto.appClient.AppClientPageDto;
import cn.hiauth.server.api.dto.appClient.AppClientUpdateDto;
import cn.hiauth.server.api.vo.CorpAppVo;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.App;
import cn.hiauth.server.entity.Oauth2RegisteredClient;
import cn.hiauth.server.service.AppService;
import cn.hiauth.server.service.Oauth2RegisteredClientService;
import cn.hiauth.server.utils.Oauth2RegisteredClientUtils;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import cn.webestar.scms.commons.api.PageVO;
import cn.webestar.scms.security.SessionContextHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/corpSpace/appClientMgr")
public class AppClientMgrController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Oauth2RegisteredClientService oauth2RegisteredClientService;

    @Autowired
    private AppService appService;

    @PostMapping("/page")
    public R<PageVO<CorpAppVo>> page(@RequestBody @Valid AppClientPageDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        Page<Oauth2RegisteredClient> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        IPage<Oauth2RegisteredClient> page = oauth2RegisteredClientService.page(p, dto.toQueryWapper());
        Set<Long> appIds = new HashSet<>();
        page.getRecords().forEach(i -> appIds.add(i.getAppId()));
        Map<Long, App> apps = new HashMap<>();
        if (!appIds.isEmpty()) {
            apps = appService.findByIds(appIds);
        }
        return R.success(CorpAppVo.toPageVo(page, apps));
    }

    @PostMapping("/findById")
    public R<CorpAppVo> findById(@RequestParam("id") String id) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Oauth2RegisteredClient o = oauth2RegisteredClientService.getById(id);
        App app = appService.getById(o.getAppId());
        return R.success(CorpAppVo.convert(o, app));
    }

    @PostMapping("/add")
    public R<Boolean> add(@RequestBody @Valid AppClientCreateDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCorpId(cid);
        List<Oauth2RegisteredClient> list = new ArrayList<>();
        for (Long appId : dto.getAppIds()) {
            App app = appService.getById(appId);
            String clientSecret = passwordEncoder.encode("123456");
            Oauth2RegisteredClient o = Oauth2RegisteredClientUtils.getDefaultClient(dto.getCorpId(), appId, app.getName(), clientSecret);
            list.add(o);
        }
        Boolean b = oauth2RegisteredClientService.saveBatch(list);
        return R.success(b);
    }

    @PostMapping("/update")
    public R<Oauth2RegisteredClient> update(@RequestBody @Valid AppClientUpdateDto body) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Oauth2RegisteredClient o = body.toDO();
        if (StringUtils.isNotBlank(o.getClientSecret())) {
            o.setClientSecret(passwordEncoder.encode(o.getClientSecret()));
        }
        oauth2RegisteredClientService.updateById(o);
        return R.success(o);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<String>> map) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Set<String> ids = map.get("ids");
        boolean b = oauth2RegisteredClientService.removeByIds(ids);
        return R.success(b);
    }

    @PostMapping("/limitNotHaveApp")
    public R<List<App>> limitNotHaveApp(@RequestBody @Valid AppClientLimitDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        List<App> list = appService.limitNotHaveApp(dto);
        return R.success(list);
    }

    @PostMapping("/limitHaveApp")
    public R<List<App>> limitHaveApp(@RequestBody @Valid AppClientLimitDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        List<App> list = appService.limitHaveApp(dto);
        return R.success(list);
    }

}
