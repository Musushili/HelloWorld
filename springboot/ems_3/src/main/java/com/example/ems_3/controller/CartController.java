package com.example.ems_3.controller;

import com.example.ems_3.entity.Cart;
import com.example.ems_3.entity.Product;
import com.example.ems_3.service.CartService;
import com.example.ems_3.service.ProductService;
import com.example.ems_3.util.JWTUtils;
import com.example.ems_3.util.JsonResult;
import com.example.ems_3.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RequestMapping("/cart")
@RestController
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    @GetMapping("/findByUid")//没有用上
    public JsonResult<Cart> findByUid(@RequestHeader("token") String token) {
        Integer uid = JWTUtils.getUserInfo(token);
        Cart data = cartService.findByUid(uid);
        return new JsonResult<Cart>(200, data);
    }

    @GetMapping("/findVOByUid")//-->展示购物车详细信息
    public JsonResult<List<CartVO>> findVOByUid(@RequestHeader("token") String token) {
        Integer uid = JWTUtils.getUserInfo(token);
        List<CartVO> data = cartService.findVOByUid(uid);
        return new JsonResult<List<CartVO>>(200, data);
    }

    @Autowired
    private ProductService productService;

    @PostMapping("/addToCart")//-->将商品添加到购物车
    /*首先判断该商品是否存在于该用户的购物车中
    * 1.存在-->修改购物车中该商品的数量number
    * 2.不存在-->在存放商品的表t_product中查找该商品(findById)，并且添加到购物车中
    * */
    public JsonResult<Void> addToCart(Integer pid, Integer number, @RequestHeader("token") String token) {
        System.out.println("1.接收的pid="+pid);
        System.out.println("接收的number="+number);

        Integer uid = JWTUtils.getUserInfo(token);
        Cart data = cartService.findByUidAndPid(uid, pid);
        if (data != null) {//存在购物车

            System.out.println("存在购物车");

            System.out.println("data.getNumcer="+data.getNumber());
            System.out.println("number="+number);

            data.setNumber(data.getNumber() + number);//修改商品的数量

            System.out.println("amount="+data.getNumber());

            cartService.updateByUidAndPid(data.getUid(), data.getPid(), data.getNumber());

            return new JsonResult<Void>(200);
        } else {
            //购物车不存在该商品，在product里面找到该pid的商品，添加到购物车中
            System.out.println("不存在购物车中");
            Product product = productService.findById(pid);
            Cart data1 = new Cart();
/*
            private Integer uid;
            private Integer id;
            private Integer pid;

            private String pimg;
            private String proname;
            private String introduce;
            private double price;
            private Integer number;
            private String type;
*/
            data1.setUid(uid);
            data1.setPid(pid);
            data1.setPimg(product.getPimg());
            data1.setProname(product.getProname());
            data1.setIntroduce(product.getIntroduce());
            data1.setPrice(product.getPrice());
            data1.setNumber(number);
            data1.setType(product.getType());

            //System.out.println(data1.getProname() + " " + data1.getNumber());
            cartService.insert(data1);
            return new JsonResult<Void>(200);
        }
    }

    @GetMapping("/findByUidAndPid")//-->展示商品详情页
    public JsonResult<Cart> findByUidAndPid(Integer pid, @RequestHeader("token") String token) {
        System.out.println("接收的pid="+pid );

        Integer uid = JWTUtils.getUserInfo(token);
        Cart data = cartService.findByUidAndPid(uid, pid);
        return new JsonResult<Cart>(200, data);
    }


    @PostMapping("/deleteByUidAndPid")//-->删除该用户购物车中的该商品
    public JsonResult<Void> deleteByUidAndPid(Integer pid, @RequestHeader("token") String token) {//cid
        System.out.println("接收的pid="+pid);

        Integer uid = JWTUtils.getUserInfo(token);
        Cart data = cartService.findByUidAndPid(uid, pid);
/*
        if(data==null) {
            throw  new ProductNotFoundException("该商品未在订单中发现");
        }else{
*/
        Integer data1 = cartService.deleteByUidAndPid(uid, pid);
        return new JsonResult<Void>(200);
/*
        if(data!=null){
*/

/*
        }else{//好像用不上，在购物车的都有cid
            JsonResult<Void> result=new JsonResult<Void>();
            result.setState(4007);
            result.setMessage("购物车中商品未被发现");
            return result;
        }
*/
    }
}
