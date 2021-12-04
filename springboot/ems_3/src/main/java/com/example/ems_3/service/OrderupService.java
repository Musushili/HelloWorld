package com.example.ems_3.service;

import com.example.ems_3.entity.Orderp;
import com.example.ems_3.entity.Orderup;

import java.util.List;

public interface OrderupService {

    Integer deleteByUidAndPid(Integer uid, Integer pid);

    Orderup create(Integer uid, Integer pid);//addToOrderp

    Orderup create1(Integer uid, Integer pid, Integer number);//addToOrderp

    List<Orderup> findByUid(Integer uid);

    Integer deleteByOid(Integer oid);

}
