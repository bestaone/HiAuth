package cn.hiauth.mgr.service;

import cn.hiauth.mgr.MgrSvcStarter;
import cn.hiauth.mgr.domain.User;
import cn.hiauth.mgr.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MgrSvcStarter.class)
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @Test
    public void CRUDTest() {

        //add
        User o = new User();
        o.setUsername("宇宙_564");
        o.setPhoneNum("13433334444");
        o.setPassword("1111");
        o.setRegtime(LocalDateTime.now());
        service.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");

        //update
        o.setUsername("火星_345334");
        service.updateById(o);

        //page
        Page<User> page = new Page<>(1, 2, true);
        IPage<User> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0,"分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}




























