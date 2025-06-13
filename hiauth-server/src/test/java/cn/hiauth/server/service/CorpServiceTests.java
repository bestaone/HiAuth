package cn.hiauth.server.service;

import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.Corp;
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
 * 租户
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class CorpServiceTests {

    private static final String txt = RandomUtil.randomString("abcdefghigklmn", 5);

    private static final String txtNew = txt + "new";

    @Resource
    private CorpService service;

    @Test
    public void CRUDTest() {

        //add
        Corp o = new Corp();
        o.setName(txt);
        o.setStatus(1);
        o.setCreator(1L);
        o.setUpdater(1L);
        o.setDeleter(1L);
        o.setCreateTime(LocalDateTime.now());
        o.setUpdateTime(LocalDateTime.now());
        o.setDeleteTime(LocalDateTime.now());
        o.setIsDeleted(false);
        o.setAppCount(1);
        o.setDepCount(1);
        o.setEmpCount(1);
        o.setIcon(txt);
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
        Page<Corp> page = new Page<>(1, 2, true);
        IPage<Corp> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0, "分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}
