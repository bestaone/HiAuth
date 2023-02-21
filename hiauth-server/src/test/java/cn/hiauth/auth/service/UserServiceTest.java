package cn.hiauth.auth.service;

import cn.hiauth.auth.AuthStarter;
import cn.hiauth.auth.domain.User;
import cn.hiauth.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Rollback(true)
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthStarter.class)
public class UserServiceTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void encodPwd() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        String pwd = passwordEncoder.encode("123456");
        log.debug("passwordEncoder:{}", pwd);
    }

    @Test
    public void CRUDTest() {

        //add
        User o = new User();
        o.setPhoneNum("1233423534");
        o.setCreaterId(1L);
        o.setUpdaterId(1L);
        o.setUsername("宇宙_564");
        o.setPassword("1111");
        o.setCreateTime(LocalDateTime.now());
        o.setUpdateTime(LocalDateTime.now());
        o.setRegtime(LocalDateTime.now());
        o.setStatus(1);
        repository.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //get
        o = repository.findById(o.getId()).get();
        Assert.notNull(o.getId(), "主键查询失败");

        //update
        o.setUsername("火星_345334");
        repository.save(o);

        //page
        PageRequest pageRequest = PageRequest.of(1, 1);
        repository.findAll(pageRequest);
        Assert.isTrue(pageRequest.getPageSize() > 0,"分页查询失败");

        //delete
        repository.deleteById(o.getId());
        o = repository.findById(o.getId()).orElse(null);
        Assert.isNull(o, "删除失败");

    }

}




























