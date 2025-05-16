package cn.hiauth.client;

import java.util.List;

/**
 * @author zgs
 */
public interface SecurityService {

    SecurityUser loadSecurityUser(Authentication auth);

    /**
     * 根据用户ID查询所属租户列表，按照最近登录时间排序
     */
    List<SecurityCorp> loadUserCorps(Long userId);

    /**
     * 切换租户
     */
    Boolean switchCorp(Long id);

}
