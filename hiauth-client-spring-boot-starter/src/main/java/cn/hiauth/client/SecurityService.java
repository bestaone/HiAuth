package cn.hiauth.client;

/**
 * @author zgs
 */
public interface SecurityService {

    SecurityUser loadSecurityUser(Authentication auth);

}
