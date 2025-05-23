package cn.hiauth.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import cn.hiauth.server.config.web.auth.SimpleJdbcRegisteredClientRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 应用
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class SimpleJdbcRegisteredClientRepositoryTests {


    @Resource
    private SimpleJdbcRegisteredClientRepository repository;

	@Test
	public void test() throws JsonProcessingException {
		Set<String> clientIds = new HashSet<>();
		clientIds.add("himall");
		clientIds.add("dcss");
		List<RegisteredClient> list = repository.findByClientIds(clientIds);
		Assert.isTrue(!list.isEmpty(), "查询失败");
	}

}
