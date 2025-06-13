package cn.hiauth.server.service;

import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.User;
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
 * 用户
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class UserServiceTests {

    private static final String txt = RandomUtil.randomString("abcdefghigklmn", 5);

    private static final String txtNew = txt + "new";

    @Resource
    private UserService service;

    @Test
    public void CRUDTest() {

        //add
        User o = new User();
        o.setName(txt);
        o.setGender(1);
        o.setAvatarUrl(txt);
        o.setPhoneNum(txt);
        o.setUsername(txt);
        o.setPwd(txt);
        o.setStatus(1);
        o.setRegtime(LocalDateTime.now());
        o.setLastLoginTime(LocalDateTime.now());
        o.setCreator(1L);
        o.setUpdater(1L);
        o.setDeleter(1L);
        o.setCreateTime(LocalDateTime.now());
        o.setUpdateTime(LocalDateTime.now());
        o.setDeleteTime(LocalDateTime.now());
        o.setIsDeleted(false);
        o.setIsSysAdmin(false);
        service.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //update
        o.setName(txtNew);
        service.updateById(o);

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");
        Assert.isTrue(txtNew.equals(o.getName()), "更新失败");

        //page
        Page<User> page = new Page<>(1, 2, true);
        IPage<User> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0, "分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}
