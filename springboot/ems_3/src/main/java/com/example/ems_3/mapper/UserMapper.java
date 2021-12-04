package com.example.ems_3.mapper;

import com.example.ems_3.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    Integer insert(@Param("username") String username, @Param("password") String password);

    User findByUsername(@Param("username") String username);

    Integer updateInfoById1(@Param("id") Integer id, @Param("username") String username);

    Integer updateInfoById2(@Param("id") Integer id, @Param("sex") String sex);

    Integer updateInfoById3(@Param("id") Integer id, @Param("age") Integer age);

    Integer updateInfoById4(@Param("id") Integer id, @Param("num") String num);

    User findById(@Param("id") Integer id);

    Integer changeAvatar(@Param("id")Integer id,@Param("uimg")String uimg);

/*
    User login(@Param("username") String username, @Param("password") String password);//有多个参数要做参数的绑定

    User searchuserid(@Param("username") String username);

    void register(@Param("username") String username,@Param("password")String password*/
    /*,@Param("sex") String sex,@Param("age") Integer age,@Param("num") Integer num,@Param("uimg") String uimg*//*
);

*/


//    void register(@Param("user") User user);//注册保存
//
//    void update(@Param("user") User user);//修改个人信息
//
//    void showDetails();

}
