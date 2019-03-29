package com.bestaone.hiauth.service;

import com.bestaone.hiauth.HiAuthApplication;
import com.bestaone.hiauth.domain.Account;
import com.bestaone.hiauth.domain.User;
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
public class AccountServiceTest {

    @Autowired
    public AccountService service;

    @Test
    public void CRUDTest() {

        User user = new User();
        user.setId(1L);

        //CREATE
        Account o = new Account();
        o.setUser(user);
        service.save(o);
        Assert.assertNotNull(o.getId());

        //READ
        o = service.findById(o.getId());
        Assert.assertNotNull(o.getId());

        //UPDATE
        user.setId(2L);
        o.setUser(user);
        service.save(o);
        o = service.findById(o.getId());
        Assert.assertTrue(o.getUser().getId().equals(2L));

        //PAGE
        PageHelper.startPage(1,1);
        List<Account> list = service.findAll();
        PageInfo<Account> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}
