package cn.hiauth.server.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.Oauth2AuthorizationConsent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * oauth2认证授权
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class Oauth2AuthorizationConsentServiceTests {

	private static String txt = RandomUtil.randomString("abcdefghigklmn",10);

	private static String txtNew = txt + "new";

    @Resource
    private Oauth2AuthorizationConsentService service;

	@Test
	public void CRUDTest() {

		//add
		Oauth2AuthorizationConsent o = new Oauth2AuthorizationConsent();
        o.setRegisteredClientId(txt);
        o.setPrincipalName(txt);
        o.setAuthorities(txt);
		service.save(o);
//		Assert.notNull(o.getId(), "添加失败");

		//update
//		//o.setName(txtNew);
//		service.updateById(o);
//
//		//get
//		o = service.getById(o.getId());
//		Assert.notNull(o.getId(), "主键查询失败");
//		//Assert.isTrue(txtNew.equals(o.getName()), "更新失败");
//
		//page
		Page<Oauth2AuthorizationConsent> page = new Page<>(1, 2, true);
		IPage<Oauth2AuthorizationConsent> oPage = service.page(page);
		Assert.isTrue(oPage.getTotal() > 0,"分页查询失败");
//
//		//delete
//		service.removeById(o.getId());
//		o = service.getById(o.getId());
//		Assert.isNull(o, "删除失败");

	}

}
