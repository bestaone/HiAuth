package cn.hiauth.resource.controller;

import cn.webestar.scms.commons.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/unpapi")
public class UnpapiController {

    @GetMapping("/metadata")
    public R<Map<String, Object>> metadata() {
        Map<String, Object> data = new HashMap<>(5);
        data.put("name", "hiauth-resource");
        data.put("description", "hiauth-resource");
        data.put("version", "1.0.0");
        return R.success(data);
    }

}