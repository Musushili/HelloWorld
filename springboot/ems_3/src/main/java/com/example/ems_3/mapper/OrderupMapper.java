package com.example.ems_3.mapper;

import com.example.ems_3.entity.Orderp;
import com.example.ems_3.entity.Orderup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderupMapper {
    Integer deleteByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

    Integer deleteByOid(@Param("oid")Integer oid);

    Integer insert(Orderup orderup);

    List<Orderup> findByUid(@Param("uid") Integer uid);

    Orderup findByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

    Orderup findByOid(@Param("oid")Integer oid);

}
