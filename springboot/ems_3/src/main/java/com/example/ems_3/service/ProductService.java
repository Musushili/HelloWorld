package com.example.ems_3.service;

import com.example.ems_3.entity.Product;

import java.util.List;

public interface ProductService {
    Product findById(Integer id);

    List<Product> findAllProByType(String type);

    List<Product> findAllPro();

/*
    public List<Product>findAllPro();
    public void addToCart(Integer uid,String pimg,String proname,String introduce,double price,Integer number,String type);

    public Product findPro(Integer id);
    public void updatenumber(Integer id,Integer number);
*/
}
