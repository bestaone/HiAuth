package cn.hiauth.server.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.Department;
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
 * 部门
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class DepartmentServiceTests {

	private static String txt = RandomUtil.randomString("abcdefghigklmn",5);

	private static String txtNew = txt + "new";

    @Resource
    private DepartmentService service;

	@Test
	public void CRUDTest() {

		//add
		Department o = new Department();
        o.setCid(1L);
        o.setPid(1L);
        o.setSort(1);
        o.setNo(txt);
        o.setName(txt);
        o.setCreator(1L);
        o.setUpdater(1L);
        o.setCreateTime(LocalDateTime.now());
        o.setUpdateTime(LocalDateTime.now());
        o.setStatus(1);
        o.setRemark(txt);
		service.save(o);
		Assert.notNull(o.getId(), "添加失败");

        //update
        o.setNo(txtNew);
        service.updateById(o);

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");
        Assert.isTrue(txtNew.equals(o.getNo()), "更新失败");

		//page
		Page<Department> page = new Page<>(1, 2, true);
		IPage<Department> oPage = service.page(page);
		Assert.isTrue(oPage.getTotal() > 0,"分页查询失败");

		//delete
		service.removeById(o.getId());
		o = service.getById(o.getId());
		Assert.isNull(o, "删除失败");

	}

}
