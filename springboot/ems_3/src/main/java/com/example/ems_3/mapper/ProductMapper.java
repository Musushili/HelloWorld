package com.example.ems_3.mapper;

import com.example.ems_3.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    Product findById(@Param("id") Integer id);

    List<Product> findAllProByType(@Param("type") String type);

    List<Product> findAllPro();

/*
    List<Product> findAllpro();
    Product findPro(@Param("id") Integer id);
    void addToCart(@Param("uid") Integer uid, @Param("pimg") String pimg, @Param("proname") String proname, @Param("introduce") String introduce, @Param("price") double price, @Param("number") Integer number, @Param("type") String type);
    void updatenumber(@Param("id") Integer id,@Param("number") Integer number);
*/
}
