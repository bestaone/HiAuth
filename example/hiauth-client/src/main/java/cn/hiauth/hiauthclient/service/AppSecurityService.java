package cn.hiauth.hiauthclient.service;


import cn.hiauth.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AppSecurityService implements SecurityService {

    @Override
    public SecurityUser loadSecurityUser(Authentication auth) {
        return null;
    }

    @Override
    public List<SecurityCorp> loadUserCorps(Long userId) {
        return List.of(new SecurityCorp(1L, "租户1"), new SecurityCorp(2L, "租户2"), new SecurityCorp(3L, "租户3"));
    }

    @Override
    public Boolean switchCorp(Long id) {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        auth.setCid(id);
        // 通过 cid 和 userId 重新获取emp信息, 这里演示就设置成一样的吧
        auth.setEmpId(id);
        SessionContextHolder.refresh();
        return true;
    }

}
