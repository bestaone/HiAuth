package cn.hiauth.mgr.controller;

import cn.hiauth.mgr.MgrSvcStarter;
import cn.hiauth.mgr.domain.Manager;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MgrSvcStarter.class)
public class ManagerControllerTest {

    @Autowired
    public ManagerController controller;

    @Test
    public void CRUDTest() {

        //insert
        Manager o = new Manager();
        o.setUsername("宇宙_564");
        o.setPhoneNum("13433334444");
        o.setPassword("1111");
        o.setRegtime(LocalDateTime.now());

        o = controller.create(o);
        Assert.notNull(o, "新建失败");

        //update
        o.setUsername("CODE_234234");
        o = controller.update(o);
        Assert.notNull(o, "更新失败");

        //find by id
        o = controller.findById(o.getId());
        Assert.isTrue("CODE_234234".equals(o.getUsername()), "通过主键查找失败");

        //find map
        Set<Serializable> ids = new HashSet<>();
        ids.add(o.getId());
        List<Manager> findByIdsResp = controller.findByIds(ids);
        Assert.isTrue(findByIdsResp.size()>0, "通过ids查询失败");

        //page
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("username", "CODE_234234");
        pageMap.put("pageNum", 1);
        pageMap.put("pageSize", 10);
        IPage<Manager> pageResp = controller.page(pageMap);
        Assert.isTrue(pageResp.getTotal()>0, "page失败");

        //find one
        Map<String, Object> oneMap = new HashMap<>();
        oneMap.put("username", "CODE_234234");
        Object findOneResp = controller.findOne(oneMap);
        Assert.notNull(findOneResp, "单个查询失败");

        //limit list
        Map<String, Object> limitMap = new HashMap<>();
        limitMap.put("username", "CODE_234234");
        limitMap.put("offset", 0);
        limitMap.put("limit", 10);
        List<Manager> limitListResp = controller.limitList(limitMap);
        Assert.isTrue(limitListResp.size()>0, "limit失败");

        //delete
        boolean deleteResp = controller.delete(o.getId());
        Assert.isTrue(deleteResp, "删除失败");

    }

}
