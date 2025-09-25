package cn.hiauth.server.config.web.auth;

import cn.hiauth.server.entity.Employee;
import cn.hiauth.server.entity.Oauth2RegisteredClient;
import cn.hiauth.server.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@JsonDeserialize(using = AuthUserDeserializer.class)
public class AuthUser implements UserDetails, CredentialsContainer {

    private Long appId;
    private Long cid;
    private Long userId;
    private Long empId;
    private String name;
    private String username;
    private String password;
    private String phoneNum;
    private String avatarUrl;
    private Collection<AuthGrantedAuthority> authorities;
    private Boolean isCorpAdmin;
    private Boolean isSysAdmin;

    public AuthUser() {
    }

    public AuthUser(final Oauth2RegisteredClient client, final User user, final Employee employee, final Collection<AuthGrantedAuthority> authorities) {
        if (client != null) {
            this.appId = client.getAppId();
        }
        if (employee != null) {
            this.empId = employee.getId();
            this.cid = employee.getCid();
            this.isCorpAdmin = employee.getIsCorpAdmin();
        }
        this.userId = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPwd();
        this.phoneNum = user.getPhoneNum();
        this.avatarUrl = user.getAvatarUrl();
        this.isSysAdmin = user.getIsSysAdmin();
        this.authorities = authorities;
    }

    public AuthUser(Long appId, Long cid, Long userId, Long empId, String name, String username, String password, String phoneNum, String avatarUrl, Boolean isSysAdmin, Collection<AuthGrantedAuthority> authorities, Boolean isCorpAdmin) {
        this.appId = appId;
        this.cid = cid;
        this.userId = userId;
        this.empId = empId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.avatarUrl = avatarUrl;
        this.isSysAdmin = isSysAdmin;
        this.authorities = authorities;
        this.isCorpAdmin = isCorpAdmin;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<AuthGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}