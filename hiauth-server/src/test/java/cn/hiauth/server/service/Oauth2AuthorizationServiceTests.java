package cn.hiauth.server.service;

import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.Oauth2Authorization;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * oauth2认证
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class Oauth2AuthorizationServiceTests {

    private static final String txt = RandomUtil.randomString("abcdefghigklmn", 5);

    private static final String txtNew = txt + "new";

    @Resource
    private Oauth2AuthorizationService service;

    @Test
    public void CRUDTest() {

        //add
        Oauth2Authorization o = new Oauth2Authorization();
        o.setRegisteredClientId(txt);
        o.setPrincipalName(txt);
        o.setAuthorizationGrantType(txt);
        o.setAuthorizedScopes(txt);
        o.setAttributes(txt);
        o.setState(txt);
        o.setAuthorizationCodeValue(txt);
        o.setAuthorizationCodeIssuedAt(LocalDateTime.now());
        o.setAuthorizationCodeExpiresAt(LocalDateTime.now());
        o.setAuthorizationCodeMetadata(txt);
        o.setAccessTokenValue(txt);
        o.setAccessTokenIssuedAt(LocalDateTime.now());
        o.setAccessTokenExpiresAt(LocalDateTime.now());
        o.setAccessTokenMetadata(txt);
        o.setAccessTokenType(txt);
        o.setAccessTokenScopes(txt);
        o.setOidcIdTokenValue(txt);
        o.setOidcIdTokenIssuedAt(LocalDateTime.now());
        o.setOidcIdTokenExpiresAt(LocalDateTime.now());
        o.setOidcIdTokenMetadata(txt);
        o.setRefreshTokenValue(txt);
        o.setRefreshTokenIssuedAt(LocalDateTime.now());
        o.setRefreshTokenExpiresAt(LocalDateTime.now());
        o.setRefreshTokenMetadata(txt);
        o.setUserCodeValue(txt);
        o.setUserCodeIssuedAt(LocalDateTime.now());
        o.setUserCodeExpiresAt(LocalDateTime.now());
        o.setUserCodeMetadata(txt);
        o.setDeviceCodeValue(txt);
        o.setDeviceCodeIssuedAt(LocalDateTime.now());
        o.setDeviceCodeExpiresAt(LocalDateTime.now());
        o.setDeviceCodeMetadata(txt);
        service.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //update
        o.setRegisteredClientId(txtNew);
        service.updateById(o);

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");
        Assert.isTrue(txtNew.equals(o.getRegisteredClientId()), "更新失败");

        //page
        Page<Oauth2Authorization> page = new Page<>(1, 2, true);
        IPage<Oauth2Authorization> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0, "分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}
