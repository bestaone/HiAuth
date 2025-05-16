package cn.hiauth.client.gateway;

import lombok.Data;

@Data
public class SecurityCorp {

    private Long id;
    private String name;

    public SecurityCorp(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
