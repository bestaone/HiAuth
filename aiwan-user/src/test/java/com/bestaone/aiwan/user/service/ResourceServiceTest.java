package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.user.Application;
import com.bestaone.aiwan.user.domain.Resource;
import com.bestaone.aiwan.user.domain.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ResourceServiceTest {

    @Autowired
    public ResourceService service;

    @Test
    public void CRUDTest() {

        //CREATE
        Resource o = new Resource();
        o.setCode("CRUDTest");
        service.save(o);
        Assert.assertNotNull(o.getId());

        //READ
        o = service.findById(o.getId());
        Assert.assertNotNull(o.getId());

        //UPDATE
        o.setCode("CRUDTest1");
        service.save(o);
        o = service.findById(o.getId());
        Assert.assertTrue(o.getCode().equals("CRUDTest1"));

        //PAGE
        PageHelper.startPage(1,1);
        List<Resource> list = service.findAll();
        PageInfo<Resource> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}
