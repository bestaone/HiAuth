package cn.hiauth.client;

import lombok.Data;

@Data
public class SecurityOrg {

    private Long id;
    private String name;

    public SecurityOrg(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
