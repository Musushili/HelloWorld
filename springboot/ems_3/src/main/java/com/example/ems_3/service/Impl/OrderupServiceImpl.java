package com.example.ems_3.service.Impl;

import com.example.ems_3.entity.Orderp;
import com.example.ems_3.entity.Orderup;
import com.example.ems_3.entity.Product;
import com.example.ems_3.mapper.CartMapper;
import com.example.ems_3.mapper.OrderupMapper;
import com.example.ems_3.mapper.ProductMapper;
import com.example.ems_3.service.OrderupService;
import com.example.ems_3.service.ex.DeleteException;
import com.example.ems_3.service.ex.InsertException;
import com.example.ems_3.service.ex.OrderupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class OrderupServiceImpl implements OrderupService {
    @Autowired
    private OrderupMapper orderupMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Orderup> findByUid(Integer uid) {
        List<Orderup> list = orderupMapper.findByUid(uid);
        if (list == null) {
            throw new OrderupNotFoundException("该用户订单为空");
        }
        return list;
    }


    @Override
    public Orderup create(Integer uid, Integer pid) {
        Product product = productMapper.findById(pid);
        Orderup orderup = new Orderup();

        /*    private Integer uid;//用户id
    private Integer oid;//订单id
    private Integer pid;//商品id

    private String pimg;
    private String proname;
    private String introduce;
    private double price;
    private Integer number;
    private String type;
*/
        orderup.setUid(uid);
        orderup.setPid(pid);
        orderup.setPimg(product.getPimg());
        orderup.setProname(product.getProname());
        orderup.setIntroduce(product.getIntroduce());
        orderup.setPrice(product.getPrice());
        orderup.setType(product.getType());

        if (cartMapper.findByUidAndPid(uid, pid) != null) {
            orderup.setNumber(product.getNumber());
        } else {
            orderup.setNumber(1);
        }
        Integer data = orderupMapper.insert(orderup);
        if (data != 1) {
            throw new InsertException("商品插入已付订单失败");
        } else {
            return orderup;
        }
    }
    @Override
    public Orderup create1(Integer uid, Integer pid, Integer number) {
        Product product = productMapper.findById(pid);
        Orderup orderup = new Orderup();

        /*    private Integer uid;//用户id
    private Integer oid;//订单id
    private Integer pid;//商品id

    private String pimg;
    private String proname;
    private String introduce;
    private double price;
    private Integer number;
    private String type;
*/
        orderup.setUid(uid);
        orderup.setPid(pid);
        orderup.setPimg(product.getPimg());
        orderup.setProname(product.getProname());
        orderup.setIntroduce(product.getIntroduce());
        orderup.setPrice(product.getPrice());
        orderup.setType(product.getType());
        orderup.setNumber(number);
/*
        if (cartMapper.findByUidAndPid(uid, pid) != null) {//购物车中有该商品
            orderp.setNumber(product.getNumber());
        } else {
            orderp.setNumber(1);
        }
*/
        Integer data = orderupMapper.insert(orderup);
        if (data != 1) {
            throw new InsertException("商品插入已付订单失败");
        } else {
            return orderup;
        }

    }


    @Override
    public Integer deleteByUidAndPid(Integer uid, Integer pid) {

//        if (orderpMapper.findByUid(oid) != null) {
        if (orderupMapper.findByUidAndPid(uid,pid) != null) {
            return orderupMapper.deleteByUidAndPid(uid, pid);
        } else {
            throw new DeleteException("删除订单时出错了");
        }
    }

    @Override
    public Integer deleteByOid(Integer oid) {
        if (orderupMapper.findByOid(oid) != null) {
            return orderupMapper.deleteByOid(oid);
        } else {
            throw new DeleteException("删除订单时出错了");
        }
    }
}
