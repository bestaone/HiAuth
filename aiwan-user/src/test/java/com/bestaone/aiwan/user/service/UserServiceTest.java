package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.user.Application;
import com.bestaone.aiwan.user.domain.User;
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

//@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    public UserService service;

    @Test
    public void CRUDTest() {

        //CREATE
        User o = new User();
        o.setUsername("test");
        o.setPassword("test");
        o.setCreateTime(new Date());
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
        List<User> list = service.findAll();
        PageInfo<User> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}
