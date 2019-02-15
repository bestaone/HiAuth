package com.bestaone.aiwan.auth.domain;

import com.bestaone.aiwan.auth.domain.enums.ResourceDomainType;
import com.bestaone.aiwan.auth.domain.enums.ResourceOperate;

public class Resource {

    private Long id;
    private ResourceDomainType domain;
    private ResourceOperate operate;
    private String code;

    public ResourceDomainType getDomain() {
        return domain;
    }

    public void setDomain(ResourceDomainType domain) {
        this.domain = domain;
    }

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
