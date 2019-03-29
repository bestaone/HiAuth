package com.bestaone.himall.order.mapper;

import com.bestaone.himall.core.mapper.BaseMapper;
import com.bestaone.himall.order.domain.Order;
import com.bestaone.himall.order.enums.OrderStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order,Long> {

    @Override
    @Insert("INSERT INTO orderx (id,no,title,totalAmount,createTime,status) VALUES (#{id},#{no},#{title},#{totalAmount},#{createTime},#{status})")
    void insert(Order order);

    @Override
    @Delete("DELETE FROM orderx WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Update("UPDATE orderx SET no=#{no},title=#{title},totalAmount=#{totalAmount},createTime=#{createTime},status=#{status} WHERE id =#{id}")
    void update(Order order);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM orderx WHERE id=#{id}")
    Order findById(Long id);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM orderx")
    List<Order> findAll();

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM orderx WHERE status = #{status}")
    List<Order> findByStatus(OrderStatus status);

}
