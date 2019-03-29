package com.bestaone.himall.common.enums;

public enum ResourceDomainType {

    AUTH("认证系统"),USER("用户系统"),GOODS("商品系统"),ORDER("订单系统");

    private String text;

    ResourceDomainType(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
