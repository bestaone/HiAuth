package cn.hiauth.server.api.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SysMenuVo {

    private String path;
    private String name;
    private String redirect;
    private String component;
    private Map<String, ?> meta = new HashMap<>();
    private List<SysMenuVo> children = new ArrayList<>();

}
