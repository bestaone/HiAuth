package com.bestaone.aiwan.misc.service;

import com.bestaone.aiwan.misc.Application;
import com.bestaone.aiwan.misc.domain.SysLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SysLogServiceTest {

    @Autowired
    public SysLogService service;

    @Test
    public void CRUDTest() {

        //CREATE
        SysLog o = new SysLog();
        o.setContent("test");
        o.setCreateTime(new Date());
        service.save(o);
        Assert.assertNotNull(o.getId());

        //READ
        o = service.findById(o.getId());
        Assert.assertNotNull(o.getId());

        //UPDATE
        o.setContent("CRUDTest1");
        service.save(o);
        o = service.findById(o.getId());
        Assert.assertTrue(o.getContent().equals("CRUDTest1"));

        //PAGE
        PageHelper.startPage(1,1);
        List<SysLog> list = service.findAll();
        PageInfo<SysLog> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}
