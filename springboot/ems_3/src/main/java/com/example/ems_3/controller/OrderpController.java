package com.example.ems_3.controller;

import com.example.ems_3.entity.Orderp;
import com.example.ems_3.service.OrderpService;
import com.example.ems_3.util.JWTUtils;
import com.example.ems_3.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RequestMapping("/orderp")
@RestController
public class OrderpController extends BaseController {
    @Autowired
    private OrderpService orderpService;

    @PostMapping("/addToOrderp")//  cart-->orderp 从购物车中购买商品
    /*
    * 根据pid和uid在t_cart中找到商品（此时购物车中的商品有number）调用create1
    * */
    public JsonResult<Orderp> addToOrderp(Integer pid, @RequestHeader("token") String token) {
        System.out.println("1.接收的pid="+pid);

        Integer uid = JWTUtils.getUserInfo(token);
        Orderp data = orderpService.create(uid, pid);
        return new JsonResult<Orderp>(200, data);
    }

    @PostMapping("/addToOrderp1")//  orderup-->orderp 从未付订单中付款
    /*
    * 未付订单中的商品不一定存在购物车中，所以必须接受商品的数量number，从t_product中找到商品
    * 添加到已付订单中
    * （后面思路发生更改）-->添加到购物车中同时添加到未付订单，所以--> 与此同时还要执行删除 购物车中该商品
    * */
    public JsonResult<Orderp> addToOrderp1(Integer pid,Integer number, @RequestHeader("token") String token) {
        System.out.println("2.接收的pid="+pid);
        System.out.println("接收的number="+number);

        Integer uid = JWTUtils.getUserInfo(token);
        Orderp data = orderpService.create1(uid, pid,number);
        return new JsonResult<Orderp>(200, data);
    }


    @PostMapping("/deleteByPid")//没有用上
    public JsonResult<Void> deleteByPid(Integer pid,@RequestHeader("token")String token) {
        System.out.println("接收的pid="+pid);

        Integer uid=JWTUtils.getUserInfo(token);
        orderpService.deleteByUidAndPid(uid,pid);
        return new JsonResult<Void>(200);

    }

    @PostMapping("/deleteByOid")//-->通过订单的id删除此订单记录
    /* 因为一个用户可以购买同一商品多次，所以只能通过t_orderp的主键 oid 找到此条记录并删除
    * */
    public JsonResult<Void> deleteByOid(Integer oid) {
        System.out.println("接收的oid="+oid);

        orderpService.deleteByOid(oid);
        return new JsonResult<Void>(200);

    }


    @GetMapping("/findByUid")//-->展示订单
    public JsonResult<List<Orderp>> findByUid(@RequestHeader("token") String token) {
        Integer uid = JWTUtils.getUserInfo(token);
        List<Orderp> data = orderpService.findByUid(uid);
        return new JsonResult<List<Orderp>>(200, data);
    }

}
/*从未付订单中添加到已付订单
找到(未付订单中的)商品-->添加到已付订单-->与此同时删除未付订单中的商品
        /orderp/addToOrderp1(pid,number)+
        /orderup/deleteByPid(pid)
*/