package cn.hiauth.server.controller.common;

import cn.hiauth.server.api.vo.CurrentLoginUserVo;
import cn.hiauth.server.api.vo.SysMenuVo;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.config.rest.security.MySecurityUser;
import cn.hiauth.server.entity.File;
import cn.hiauth.server.service.FileService;
import cn.hiauth.server.utils.Constant;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import cn.webestar.scms.security.SessionContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/common")
public class CommonController {

    private static String menu;
    private static String corpMenu;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FileService fileService;

    @PostMapping("/userInfo")
    public R<CurrentLoginUserVo> userInfo() {
        MySecurityUser securityUser = (MySecurityUser) SessionContextHolder.getPrincipal();
        Assert.notNull(securityUser, SysCode.biz(1), "未登录，无法获取用户信息");
        CurrentLoginUserVo vo = new CurrentLoginUserVo();
        vo.setUserId(securityUser.getUserId());
        vo.setName(securityUser.getName());
        vo.setRealName(securityUser.getName());
        vo.setAvatar(securityUser.getAvatar());
        vo.setUsername(securityUser.getUsername());
        vo.setCid(securityUser.getCid());
        vo.setEmpId(securityUser.getEmpId());
        if (securityUser.getIsSysAdmin() != null && securityUser.getIsSysAdmin()) {
            vo.getRoles().add("sysAdmin");
        } else if (securityUser.getIsCorpAdmin() != null && securityUser.getIsCorpAdmin()) {
            vo.getRoles().add("corpAdmin");
        }
        return R.success(vo);
    }

//    @PostMapping("/userInfo1")
//    public R userInfo1() {
//        Map<String, Object> map = new HashMap<>();
//        String[] roles = new String[]{"corpAdmin"};
//        map.put("realName", "admin");
//        map.put("username", "admin");
//        map.put("roles", roles);
//        return R.success(map);
//    }

    @PostMapping("/codes")
    public R codes() {
        String[] codes = new String[]{"AC_100100", "AC_100110", "AC_100120", "AC_100010"};
        return R.success(codes);
    }

    @GetMapping("/sysMenus")
    public R<List<SysMenuVo>> sysMenus(@RequestParam("type") Integer type) throws IOException {
        String file = "/mock/sysMenu.json";
        String json = null;
        if (type == 1) {
            if (menu == null) {
                ClassPathResource resource = new ClassPathResource(file);
                menu = IOUtils.toString(resource.getInputStream());
                menu = menu.replace("\"{commonIsHide}\"", "false")
                        .replace("\"{systemIsHide}\"", "false")
                        .replace("\"{corpIsHide}\"", "true");
            }
            json = menu;
        } else if (type == 2) {
            if (corpMenu == null) {
                ClassPathResource resource = new ClassPathResource(file);
                corpMenu = IOUtils.toString(resource.getInputStream());
                corpMenu = corpMenu.replace("\"{commonIsHide}\"", "false")
                        .replace("\"{systemIsHide}\"", "true")
                        .replace("\"{corpIsHide}\"", "false");
            }
            json = corpMenu;
        }
        List<SysMenuVo> list = objectMapper.readValue(json, List.class);
        return R.success(list);
    }

    @GetMapping("/sysAction")
    public R<Map<String, ?>> sysAction() throws IOException {
        ClassPathResource resource = new ClassPathResource("/mock/sysAction.json");
        String content = IOUtils.toString(resource.getInputStream());
        Map<String, ?> map = objectMapper.readValue(content, Map.class);
        return R.success(map);
    }

    @PostMapping("/file/uploadImg")
    public R<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Assert.isTrue(!file.isEmpty(), SysCode.biz(1), "文件错误");
        Assert.isTrue(file.getSize() < 500 * 1024, SysCode.biz(2), "文件大小不得超过0.5M");
        String filename = file.getOriginalFilename();
        Assert.notNull(filename, SysCode.biz(3), "文件名错误");
        String extName = filename.substring(filename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replace("-", "") + extName;
        File f = new File();
        f.setCode(newFileName);
        f.setOriginName(filename);
        f.setName(newFileName);
        f.setExt(extName);
        f.setData(file.getBytes());
        fileService.save(f);
        Map<String, String> map = new HashMap<>();
        map.put("fileUrl", Constant.IMG_API + newFileName);
        return R.success(Constant.IMG_API + newFileName);
    }

}
