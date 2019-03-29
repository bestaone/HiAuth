package com.bestaone.hiauth.service;

import com.bestaone.hiauth.HiAuthApplication;
import com.bestaone.hiauth.domain.Role;
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

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HiAuthApplication.class)
public class RoleServiceTest {

    @Autowired
    public RoleService service;

    @Test
    public void CRUDTest() {

        //CREATE
        Role o = new Role();
        o.setName("CRUDTest");
        service.save(o);
        Assert.assertNotNull(o.getId());

        //READ
        o = service.findById(o.getId());
        Assert.assertNotNull(o.getId());

        //UPDATE
        o.setName("CRUDTest1");
        service.save(o);
        o = service.findById(o.getId());
        Assert.assertTrue(o.getName().equals("CRUDTest1"));

        //PAGE
        PageHelper.startPage(1,1);
        List<Role> list = service.findAll();
        PageInfo<Role> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}
