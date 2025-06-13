package cn.hiauth.server.service;

import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.Oauth2RegisteredClient;
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
 * oauth2客户端
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class Oauth2RegisteredClientServiceTests {

    private static final String txt = RandomUtil.randomString("abcdefghigklmn", 5);

    private static final String txtNew = txt + "new";

    @Resource
    private Oauth2RegisteredClientService service;

    @Test
    public void CRUDTest() {

        //add
        Oauth2RegisteredClient o = new Oauth2RegisteredClient();
        o.setClientId(txt);
        o.setClientIdIssuedAt(LocalDateTime.now());
        o.setClientSecret(txt);
        o.setClientSecretExpiresAt(LocalDateTime.now());
        o.setClientName(txt);
        o.setClientAuthenticationMethods(txt);
        o.setAuthorizationGrantTypes(txt);
        o.setRedirectUris(txt);
        o.setPostLogoutRedirectUris(txt);
        o.setScopes(txt);
        o.setClientSettings(txt);
        o.setTokenSettings(txt);
        o.setAppId(1L);
        o.setCid(1L);
        service.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //update
        o.setClientId(txtNew);
        service.updateById(o);

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");
        Assert.isTrue(txtNew.equals(o.getClientId()), "更新失败");

        //page
        Page<Oauth2RegisteredClient> page = new Page<>(1, 2, true);
        IPage<Oauth2RegisteredClient> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0, "分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}
