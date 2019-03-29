package com.bestaone.himall.goods.service;

import com.bestaone.himall.goods.domain.Goods;
import com.bestaone.himall.goods.GoodsApplication;
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
@SpringBootTest(classes = GoodsApplication.class)
public class GoodsServiceTest {

    @Autowired
    public GoodsService service;

    @Test
    public void CRUDTest() {

        //CREATE
        Goods o = new Goods();
        o.setTitle("test");
        o.setPrice(1F);
        o.setCreateTime(new Date());
        o.setCreateTime(new Date());
        service.save(o);
        Assert.assertNotNull(o.getId());

        //READ
        o = service.findById(o.getId());
        Assert.assertNotNull(o.getId());

        //UPDATE
        o.setTitle("CRUDTest1");
        service.save(o);
        o = service.findById(o.getId());
        Assert.assertTrue(o.getTitle().equals("CRUDTest1"));

        //PAGE
        PageHelper.startPage(1,1);
        List<Goods> list = service.findAll();
        PageInfo<Goods> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}
