package com.example.ems_3.mapper;

import com.example.ems_3.entity.Orderp;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderpMapper {
    Integer deleteByUidAndPid(@Param("uid") Integer uid,@Param("pid")Integer pid);

    Integer deleteByOid(@Param("oid")Integer oid);

    Integer insert(Orderp orderp);

    List<Orderp> findByUid(@Param("uid") Integer uid);

    Orderp findByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

    Orderp findByOid(@Param("oid")Integer oid);

}
