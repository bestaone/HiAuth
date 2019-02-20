package com.bestaone.aiwan.user.domain;

import com.bestaone.aiwan.common.enums.ResourceDomainType;
import com.bestaone.aiwan.core.entity.BaseEntity;
import com.bestaone.aiwan.user.domain.enums.ResourceOperate;

public class Resource extends BaseEntity<Long> {

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
