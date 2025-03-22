package cn.hiauth.server.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.SysLog;
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
 * 系统日志
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class SysLogServiceTests {

	private static String txt = RandomUtil.randomString("abcdefghigklmn",5);

	private static String txtNew = txt + "new";

    @Resource
    private SysLogService service;

	@Test
	public void CRUDTest() {

		//add
		SysLog o = new SysLog();
        o.setActor(txt);
        o.setActorIp(txt);
        o.setActorType(1);
        o.setEventTime(LocalDateTime.now());
		o.setEventType(txt);
		o.setEventLevel(1);
		o.setEventResult(txt);
        o.setEventDesc(txt);
		o.setSourceSys(txt);
		o.setSourceModule(txt);
        o.setCreateTime(LocalDateTime.now());
		service.save(o);
		Assert.notNull(o.getId(), "添加失败");

        //update
        o.setActor(txtNew);
        service.updateById(o);

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");
        Assert.isTrue(txtNew.equals(o.getActor()), "更新失败");

		//page
		Page<SysLog> page = new Page<>(1, 2, true);
		IPage<SysLog> oPage = service.page(page);
		Assert.isTrue(oPage.getTotal() > 0,"分页查询失败");

		//delete
		service.removeById(o.getId());
		o = service.getById(o.getId());
		Assert.isNull(o, "删除失败");

	}

}
