package cn.hiauth.server.controller.corpspace;

import cn.hiauth.server.api.dto.dict.DictCreateDto;
import cn.hiauth.server.api.dto.dict.DictLimitDto;
import cn.hiauth.server.api.dto.dict.DictPageDto;
import cn.hiauth.server.api.dto.dict.DictUpdateDto;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.Dict;
import cn.hiauth.server.service.DictService;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import cn.webestar.scms.commons.api.PageVO;
import cn.webestar.scms.security.SessionContextHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/corpSpace/dictMgr")
public class DictMgrController {

    @Autowired
    private DictService dictService;

    @PostMapping("/page")
    public R<PageVO<Dict>> page(@RequestBody @Valid DictPageDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        Page<Dict> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        IPage<Dict> page = dictService.pageByPcode(p, dto.toQueryWapper(), dto.getPCode(), dto.getIsRoot());
        return R.success(new PageVO<>(page));
    }

    @PostMapping("/findById")
    public R<Dict> findById(@RequestParam("id") Long id) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Dict o = dictService.getById(id);
        return R.success(o);
    }

    @PostMapping("/create")
    public R<Dict> create(@RequestBody @Valid DictCreateDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Dict dict = dto.toDO();
        dict.setCid(cid);
        dictService.save(dict);
        return R.success(dict);
    }

    @PostMapping("/update")
    public R<Dict> update(@RequestBody @Valid DictUpdateDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Dict o = dto.toDO();
        dictService.updateById(o);
        return R.success(o);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Set<Long> ids = map.get("ids");
        int childCount = dictService.childCount(ids);
        Assert.isTrue(childCount==0, SysCode.biz(2), "有子级数据，不能删除");
        boolean b = dictService.removeByIds(ids);
        return R.success(b);
    }

    @PostMapping("/findSubDict")
    public R<List<Dict>> findSubDict(@RequestBody @Valid DictLimitDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        Page<Dict> p = new Page<>(dto.getOffset(), dto.getLimit(), true);
        IPage<Dict> page = dictService.pageByPcode(p, dto.toQueryWapper(), dto.getPcode(), false);
        return R.success(page.getRecords());
    }

    @PostMapping("/findDict")
    public R<List<Dict>> findDict(@RequestBody @Valid DictLimitDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        List<Dict> list = dictService.list(dto.toQueryWapper());
        return R.success(list);
    }

}
