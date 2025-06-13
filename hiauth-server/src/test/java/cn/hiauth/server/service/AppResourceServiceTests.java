package cn.hiauth.server.service;

import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.AppResource;
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

/**
 * 系统资源
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class AppResourceServiceTests {

    private static final String txt = RandomUtil.randomString("abcdefghigklmn", 5);

    private static final String txtNew = txt + "new";

    @Resource
    private AppResourceService service;

    @Test
    public void CRUDTest() {

        //add
        AppResource o = new AppResource();
        o.setPid(1L);
        o.setAppId(1L);
        o.setCode(txt);
        o.setUrl(txt);
        o.setName(txt);
        o.setType(1);
        o.setRemark(txt);
        service.save(o);
        Assert.notNull(o.getId(), "添加失败");

        //update
        o.setCode(txtNew);
        service.updateById(o);

        //get
        o = service.getById(o.getId());
        Assert.notNull(o.getId(), "主键查询失败");
        Assert.isTrue(txtNew.equals(o.getCode()), "更新失败");

        //page
        Page<AppResource> page = new Page<>(1, 2, true);
        IPage<AppResource> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0, "分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");
    }

   /* @Test
    public void findTreeTest() {
        AppResourceWithKeywordDto dto = new AppResourceWithKeywordDto();
        dto.setCorpId(1L);
        List<AppResourceVo> list = service.findListWithKeyword(dto);
        Assert.isTrue(list.size() > 0,"查询失败");
    }*/

}
