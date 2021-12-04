package com.example.ems_3.mapper;

import com.example.ems_3.entity.Cart;
import com.example.ems_3.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    Cart findByUid(@Param("uid") Integer uid);

    List<CartVO> findVOByUid(@Param("uid") Integer uid);

    Integer insert(Cart cart);

    Cart findByUidAndPid(@Param("uid")Integer uid,@Param("pid") Integer pid);

    Integer updateByUidAndPid(@Param("uid") Integer uid,@Param("pid") Integer pid,@Param("number") Integer number);

    Integer deleteByUidAndPid(@Param("uid")Integer uid,@Param("pid")Integer pid);
}
