package cn.hiauth.server.controller.adminspace;

import cn.hiauth.server.api.dto.user.UserCreateDto;
import cn.hiauth.server.api.dto.user.UserPageDto;
import cn.hiauth.server.api.dto.user.UserUpdateDto;
import cn.hiauth.server.api.vo.UserVo;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.User;
import cn.hiauth.server.service.UserService;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.api.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/adminSpace/userMgr")
public class UserMgrController {

    private static String DEFAULT_PWD = "123456";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/page")
    public R<PageVO<UserVo>> page(@RequestBody @Valid UserPageDto dto) {
        Page<User> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        LambdaQueryWrapper<User> qw = dto.toQueryWapper();
        qw.orderByDesc(User::getCreateTime);
        IPage<User> page = userService.page(p, qw);
        PageVO<UserVo> pageVo = UserVo.toPageVo(page);
        return R.success(pageVo);
    }

    @PostMapping("/findById")
    public R<UserVo> findById(@RequestParam("id") Long id) {
        User o = userService.getById(id);
        return R.success(UserVo.convert(o));
    }

    @PostMapping("/create")
    public R<UserVo> create(@RequestBody @Valid UserCreateDto body) {
        User o = body.toDO();
        o.setPwd(passwordEncoder.encode(DEFAULT_PWD));
        userService.save(o);
        return R.success(UserVo.convert(o));
    }

    @PostMapping("/update")
    public R<UserVo> update(@RequestBody @Valid UserUpdateDto body) {
        User o = body.toDO();
        userService.updateById(o);
        return R.success(UserVo.convert(o));
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Set<Long> ids = map.get("ids");
        boolean b = userService.removeByIds(ids);
        return R.success(b);
    }

}
