package com.bestaone.aiwan.goods.mapper;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.goods.domain.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods,Long> {

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM goods WHERE id=#{id}")
    Goods findById(Long id);

    @Override
    @Insert("INSERT INTO goods (id,title,price,amount,createTime) VALUES (#{id},#{title},#{price},#{amount},#{createTime})")
    void insert(Goods entity);

    @Override
    @Delete("DELETE FROM goods WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Update("UPDATE goods SET title=#{title},price=#{price},amount=#{amount},createTime=#{createTime} WHERE id =#{id}")
    void update(Goods entity);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM goods")
    List<Goods> findAll();

}
