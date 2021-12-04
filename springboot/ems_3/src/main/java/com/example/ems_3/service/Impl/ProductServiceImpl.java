package com.example.ems_3.service.Impl;

import com.example.ems_3.entity.Product;
import com.example.ems_3.mapper.ProductMapper;
import com.example.ems_3.service.ProductService;
import com.example.ems_3.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);

        System.out.println("pid="+id);//
        System.out.println("Number"+product.getNumber());

        if (product != null) {
            product.setNumber(0);//t_product 存的number都为0
            product.setUid(0);
            return product;
        } else {
            throw new ProductNotFoundException("商品查找失败");
        }
    }

    @Override
    public List<Product> findAllProByType(String type) {
        List<Product> data = productMapper.findAllProByType(type);
        if (data != null) {
            for (Product product : data) {
                product.setNumber(0);
                product.setUid(0);
            }
            return data;
        } else {
            throw new ProductNotFoundException("展示购物车中商品失败");
        }
    }


    @Override
    public List<Product> findAllPro() {
        List<Product> data = productMapper.findAllPro();
        if (data != null) {
            for (Product product : data) {
                product.setNumber(0);
                product.setUid(0);
            }
            return data;
        } else {
            throw new ProductNotFoundException("展示购物车中商品失败");
        }
    }

/*


    @Override
    public List<Product> findAllPro() {
        return productMapper.findAllpro();
    }

//    @Override
//    public void addToCart(Integer uid, String pimg, String proname, String introduce, double price, Integer number, String type);
//        productMapper.addToCart(uid,pimg,proname,introduce,price,number,type);
//    }


    @Override
    public void addToCart(Integer uid, String pimg, String proname, String introduce, double price, Integer number, String type) {
        productMapper.addToCart(uid,pimg,proname,introduce,price,number,type);
    }

    @Override
    public void updatenumber(Integer id, Integer number) {
        productMapper.updatenumber(id, number);
    }

    @Override
    public Product findPro(Integer id) {
        return productMapper.findPro(id);
    }
*/
}