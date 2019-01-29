package com.bestaone.aiwan.goods.mapper;

import com.bestaone.aiwan.goods.domain.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Insert("INSERT INTO goods (id,title,price,amount,createTime) VALUES (#{id},#{title},#{price},#{amount},#{createTime})")
    void insert(Goods user);

    @Delete("DELETE FROM goods WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE goods SET title=#{title},price=#{price},amount=#{amount},createTime=#{createTime} WHERE id =#{id}")
    int update(Goods user);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM goods WHERE id=#{id}")
    Goods findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM goods")
    List<Goods> findAll();

}
