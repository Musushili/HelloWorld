package com.example.ems_3.service;

import com.example.ems_3.entity.Cart;
import com.example.ems_3.vo.CartVO;

import java.util.List;

public interface CartService {
    Cart findByUid(Integer uid);

    List<CartVO> findVOByUid(Integer uid);

    Integer insert(Cart cart);

    Cart findByUidAndPid(Integer uid, Integer pid);

    Integer updateByUidAndPid(Integer uid, Integer pid, Integer number);


    Integer deleteByUidAndPid(Integer uid,Integer pid);
}
