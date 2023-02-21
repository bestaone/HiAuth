package cn.hiauth.mgr.service;

import cn.hiauth.mgr.MgrSvcStarter;
import cn.hiauth.mgr.domain.RegisteredClient;
import cn.hiauth.mgr.mapper.RegisteredClientMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

//@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MgrSvcStarter.class)
public class RegisteredClientServiceTest {

    @Autowired
    private RegisteredClientService service;

    @Autowired
    private RegisteredClientMapper mapper;

    @Test
    public void CRUDTest() {

        //add
        RegisteredClient o = new RegisteredClient();
        o.setClientId("clientid_2342342j3");
        o.setClientName("clientname_11111");
        o.setClientAuthenticationMethods("client_secret_basic");
        o.setAuthorizationGrantTypes("refresh_token,client_credentials,password,authorization_code");
        o.setScopes("read,write");
        o.setClientSettings("{}");
        o.setTokenSettings("{}");

        service.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");

        //update
        o.setClientName("clientname_22222");
        service.updateById(o);

        //page
        Page<RegisteredClient> page = new Page<>(1, 2, true);
        IPage<RegisteredClient> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0,"分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}




























