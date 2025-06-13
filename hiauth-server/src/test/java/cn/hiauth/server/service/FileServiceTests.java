package cn.hiauth.server.service;

import cn.hiauth.server.ServerStarter;
import cn.hiauth.server.entity.File;
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
 * 文件
 */
@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ServerStarter.class)
class FileServiceTests {

    private static final String txt = RandomUtil.randomString("abcdefghigklmn", 5);

    private static final String txtNew = txt + "new";

    @Resource
    private FileService service;

    @Test
    public void CRUDTest() {

        //add
        File o = new File();
        o.setCode(txt);
        o.setOriginName(txt);
        o.setName(txt);
        o.setExt(txt);
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
        Page<File> page = new Page<>(1, 2, true);
        IPage<File> oPage = service.page(page);
        Assert.isTrue(oPage.getTotal() > 0, "分页查询失败");

        //delete
        service.removeById(o.getId());
        o = service.getById(o.getId());
        Assert.isNull(o, "删除失败");

    }

}
