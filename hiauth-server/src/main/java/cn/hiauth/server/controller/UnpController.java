package cn.hiauth.server.controller;

import cn.hiauth.server.api.dto.RegisterDto;
import cn.hiauth.server.config.AppProperties;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.File;
import cn.hiauth.server.service.CorpService;
import cn.hiauth.server.service.FileService;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/unpapi")
public class UnpController {



    @Autowired
    private AppProperties appProperties;

    @Autowired
    private FileService fileService;

    @Autowired
    private CorpService corpService;

    @GetMapping("/info")
    public R<Map<String, String>> info() {
        Map<String, String> map = new HashMap<>();
        map.put("version", "version");
        map.put("buildTimestamp", "buildTimestamp");
        return R.success(map);
    }

    @PostMapping("/metadata")
    public R<Map<String, Object>> metadata(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>(5);
        data.put("usernamePlaceholder", appProperties.getLoginPageUsernamePlaceholder());
        data.put("passwordPlaceholder", appProperties.getLoginPagePasswordPlaceholder());
        data.put("encryptType", appProperties.getEncryptType());
        data.put("publicKey", appProperties.getPublicKey());
        return R.success(data);
    }

    @GetMapping("/image/{code}")
    public ResponseEntity<byte[]> getImageAsByteArray(@PathVariable("code") String code) {
        Assert.notNull(code, SysCode.biz(1), "文件编码错误");
        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(File::getCode, code);
        File file = fileService.getOne(queryWrapper);
        byte[] imageBytes = file.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public R<Boolean> register(@RequestBody @Validated RegisterDto dto) {
        corpService.register(dto);
        return R.success(Boolean.TRUE);
    }

}
