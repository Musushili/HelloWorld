package com.example.ems_3.service;

import com.example.ems_3.entity.Orderp;

import java.util.List;

public interface OrderpService {
//    Integer insert(Integer uid,Integer pid);//addToOrderp

    Integer deleteByOid(Integer oid);

    Integer deleteByUidAndPid(Integer uid, Integer pid);

    Orderp create(Integer uid, Integer pid);//addToOrderp

    Orderp create1(Integer uid, Integer pid, Integer number);//addToOrderp

    List<Orderp> findByUid(Integer uid);


}
