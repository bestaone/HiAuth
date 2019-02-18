package com.bestaone.aiwan.order.service;

import com.bestaone.aiwan.order.OrderApplication;
import com.bestaone.aiwan.order.domain.Order;
import com.bestaone.aiwan.order.domain.enums.OrderStatus;
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
@SpringBootTest(classes = OrderApplication.class)
public class OrderServiceTest {

    @Autowired
    public OrderService service;

    @Test
    public void CRUDTest() {

        //CREATE
        Order o = new Order();
        o.setTitle("test");
        o.setTotalAmount(1F);
        o.setCreateTime(new Date());
        o.setStatus(OrderStatus.UNPAY);
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
        List<Order> list = service.findAll();
        PageInfo<Order> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}
