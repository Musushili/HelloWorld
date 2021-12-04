package com.example.ems_3.controller;

import com.example.ems_3.entity.Orderup;
import com.example.ems_3.service.OrderupService;
import com.example.ems_3.util.JWTUtils;
import com.example.ems_3.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RequestMapping("/orderup")
@RestController
public class OrderupController extends BaseController {
    @Autowired
    private OrderupService orderupService;

    @PostMapping("/addToOrderup")//没有用上
    public JsonResult<Orderup> addToOrderup(Integer pid, @RequestHeader("token") String token) {
        System.out.println("接收的pid=" + pid);

        Integer uid = JWTUtils.getUserInfo(token);
        Orderup data = orderupService.create(uid, pid);
        return new JsonResult<Orderup>(200, data);
    }

    @PostMapping("/addToOrderup1")//-->从商品详情页添加到购物车的同时，添加到未付订单，同时秩序
    public JsonResult<Orderup> addToOrderup1(Integer pid, Integer number, @RequestHeader("token") String token) {
        System.out.println("2.接收的pid=" + pid);
        System.out.println("接收的number=" + number);

        Integer uid = JWTUtils.getUserInfo(token);
        Orderup data = orderupService.create1(uid, pid, number);
        return new JsonResult<Orderup>(200, data);
    }

    @GetMapping("/findByUid")//-->同展示已付订单
    public JsonResult<List<Orderup>> findByUid(@RequestHeader("token") String token) {

        Integer uid = JWTUtils.getUserInfo(token);
        List<Orderup> data = orderupService.findByUid(uid);
        return new JsonResult<List<Orderup>>(200, data);
    }

    @PostMapping("/deleteByPid")//没有用上，因为得到的查询结果可能不唯一
    public JsonResult<Void> deleteByPid(Integer pid, @RequestHeader("token") String token) {
        System.out.println("接收的pid=" + pid);

        Integer uid = JWTUtils.getUserInfo(token);
        orderupService.deleteByUidAndPid(uid, pid);
        return new JsonResult<Void>(200);
    }

    @PostMapping("/deleteByOid")//-->同删除已付订单的购买记录
    public JsonResult<Void> deleteByOid(Integer oid) {
        System.out.println("接收的oid="+oid);

        orderupService.deleteByOid(oid);
        return new JsonResult<Void>(200);

    }

}