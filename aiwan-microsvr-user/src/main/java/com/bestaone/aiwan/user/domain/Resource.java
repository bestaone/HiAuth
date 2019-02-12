package com.bestaone.aiwan.user.domain;

import com.bestaone.aiwan.user.domain.enums.ResourceOperate;

public class Resource {

    private Long id;
    private ResourceOperate operate;
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceOperate getOperate() {
        return operate;
    }

    public void setOperate(ResourceOperate operate) {
        this.operate = operate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
