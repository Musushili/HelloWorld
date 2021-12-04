package com.example.ems_3.service.Impl;

import com.example.ems_3.entity.Orderp;
import com.example.ems_3.entity.Product;
import com.example.ems_3.mapper.CartMapper;
import com.example.ems_3.mapper.OrderpMapper;
import com.example.ems_3.mapper.ProductMapper;
import com.example.ems_3.service.OrderpService;
import com.example.ems_3.service.ex.DeleteException;
import com.example.ems_3.service.ex.InsertException;
import com.example.ems_3.service.ex.OrderpNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class OrderpServiceImpl implements OrderpService {
    @Autowired
    private OrderpMapper orderpMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Orderp> findByUid(Integer uid) {
        List<Orderp> list = orderpMapper.findByUid(uid);
        if (list == null) {
            throw new OrderpNotFoundException("该用户订单为空");
        }
        return list;
    }

    @Override
    public Orderp create(Integer uid, Integer pid) {
        Product product = productMapper.findById(pid);
        Orderp orderp = new Orderp();

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
        orderp.setUid(uid);
        orderp.setPid(pid);
        orderp.setPimg(product.getPimg());
        orderp.setProname(product.getProname());
        orderp.setIntroduce(product.getIntroduce());
        orderp.setPrice(product.getPrice());
        orderp.setType(product.getType());

        if (cartMapper.findByUidAndPid(uid, pid) != null) {//购物车中有该商品
            orderp.setNumber(product.getNumber());
        } else {
            orderp.setNumber(1);
        }
        Integer data = orderpMapper.insert(orderp);
        if (data != 1) {
            throw new InsertException("商品插入已付订单失败");
        } else {
            return orderp;
        }
    }


    @Override
    public Orderp create1(Integer uid, Integer pid, Integer number) {
        Product product = productMapper.findById(pid);
        Orderp orderp = new Orderp();

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
        orderp.setUid(uid);
        orderp.setPid(pid);
        orderp.setPimg(product.getPimg());
        orderp.setProname(product.getProname());
        orderp.setIntroduce(product.getIntroduce());
        orderp.setPrice(product.getPrice());
        orderp.setType(product.getType());
        orderp.setNumber(number);
/*
        if (cartMapper.findByUidAndPid(uid, pid) != null) {//购物车中有该商品
            orderp.setNumber(product.getNumber());
        } else {
            orderp.setNumber(1);
        }
*/
        Integer data = orderpMapper.insert(orderp);
        if (data != 1) {
            throw new InsertException("商品插入已付订单失败");
        } else {
            return orderp;
        }

    }


    @Override
    public Integer deleteByUidAndPid(Integer uid, Integer pid) {

//        if (orderpMapper.findByUid(oid) != null) {
        if (orderpMapper.findByUidAndPid(uid, pid) != null) {
            return orderpMapper.deleteByUidAndPid(uid, pid);
        } else {
            throw new DeleteException("删除订单时出错了");
        }
    }

        @Override
        public Integer deleteByOid(Integer oid) {
            if (orderpMapper.findByOid(oid) != null) {
                return orderpMapper.deleteByOid(oid);
            } else {
                throw new DeleteException("删除订单时出错了");
            }
        }

}
