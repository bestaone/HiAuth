package cn.hiauth.server.service;

import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.CorpLog;
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
 * 企业日志
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class CorpLogServiceTests {

    private static String txt = RandomUtil.randomString("abcdefghigklmn", 5);

    private static String txtNew = txt + "new";

    @Resource
    private CorpLogService service;

    @Test
    public void CRUDTest() {

        //add
        CorpLog o = new CorpLog();
        o.setOperator(txt);
        o.setOptTime(LocalDateTime.now());
        o.setOptIp(txt);
        o.setOptDesc(txt);
        o.setOptType(txt);
        o.setSourceSys(txt);
        o.setSourceTarget(txt);
        o.setSourceApi(txt);
        service.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //update
        o.setOperator(txtNew);
        service.updateById(o);

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");
        Assert.isTrue(txtNew.equals(o.getOperator()), "更新失败");

        //page
        Page<CorpLog> page = new Page<>(1, 2, true);
        IPage<CorpLog> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0, "分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}
