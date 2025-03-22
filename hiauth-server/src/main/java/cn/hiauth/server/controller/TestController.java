package cn.hiauth.server.controller;

import cn.hiauth.server.config.rest.ResourceApi;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping
public class TestController {

    @GetMapping("/unpapi/test/exception")
    public R<String> testException() throws Exception {
        throw new Exception("exception");
    }

    @GetMapping("/unpapi/test/byZero")
    public R<String> testByZero() {
        int i = 1 / 0;
        return R.success("byZero");
    }

    @GetMapping("/unpapi/test/assert")
    public R<String> testAssert() {
        Assert.isTrue(false, SysCode.biz(1), "assert false");
        return R.success("testAssert");
    }

}
