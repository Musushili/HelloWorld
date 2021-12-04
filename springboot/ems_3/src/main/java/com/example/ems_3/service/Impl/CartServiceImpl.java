package com.example.ems_3.service.Impl;

import com.example.ems_3.entity.Cart;
import com.example.ems_3.mapper.CartMapper;
import com.example.ems_3.service.CartService;
import com.example.ems_3.service.ex.*;
import com.example.ems_3.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public Cart findByUid(Integer uid) {
        Cart cart = cartMapper.findByUid(uid);
        if (cart != null) {
            return cart;
        } else {
            throw new CartNotFoundException("购物车中查询商品失败");
        }
    }

    @Override
    public List<CartVO> findVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

/*
    @Override
    public Integer addToCart(Integer uid,Integer pid,Integer number) {
        return cartMapper.addToCart(uid,pid,number);
    }
*/

    @Override
    public Cart findByUidAndPid(Integer uid, Integer pid) {
        Cart cart = cartMapper.findByUidAndPid(uid, pid);
/*
        if (cart != null) {//购物车里面有该用户的该商品
            return cart;
        } else {//cart==null//没有
            //cartMapper.insert(cart);
            return cart;
            //throw new CartNotFoundException("购物车中查询商品失败");
        }
*/
        if(cart==null){
            System.out.println("该商品未在购物车中发现");
            return null;
        }else{
            return cart;
        }

    }

    @Override
    public Integer updateByUidAndPid(Integer uid, Integer pid, Integer number) {
        Integer data = cartMapper.updateByUidAndPid(uid, pid, number);
//        Integer data= cartMapper.updateByCid();
        if (data != null) {
            return data;
        } else {
            throw new UpdateException("更新购物车商品数量失败");
        }
    }

    @Override
    public Integer insert(Cart cart) {
        Integer data = cartMapper.insert(cart);
        if (data == 1) {
            return data;
        } else {
            throw new InsertException("商品插入购物车失败");
        }
    }
    @Override
    public Integer deleteByUidAndPid(Integer uid, Integer pid) {
        Integer data=cartMapper.deleteByUidAndPid(uid,pid);
        if (data == 1) {
            return data;
        } else {
            throw new DeleteException("删除购物车中商品失败");
        }
    }

}
