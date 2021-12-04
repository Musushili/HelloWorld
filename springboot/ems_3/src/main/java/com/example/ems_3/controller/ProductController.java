package com.example.ems_3.controller;

import com.example.ems_3.entity.Product;
import com.example.ems_3.service.ProductService;
import com.example.ems_3.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @GetMapping("/findById")//-->  展示商品详情页面 通过商品id在t_product中找到该商品并返回
    public JsonResult<Product> findById(Integer id) {//pid
        System.out.println("接收的id="+id);

        Product data = productService.findById(id);
        return new JsonResult<Product>(200, data);
    }

    @GetMapping("/findAllProByType")//-->主页面分类展示商品
    public JsonResult<List<Product>> findAllProByType(String type) {
        System.out.println("接收的type="+type);

        List<Product> data = productService.findAllProByType(type);
        return new JsonResult<List<Product>>(200, data);
    }

    @GetMapping("/findAllPro")//-->展示商品列表
    public JsonResult<List<Product>> findAllPro() {
        List<Product> data = productService.findAllPro();
        return new JsonResult<List<Product>>(200, data);
    }

    /*
    @GetMapping("/findAllPro")
    public List<Product> findAllPro() {
        List<Product> list = productService.findAllPro();
        if (list != null) {
            return list;
        } else {
            return null;
        }
    }

    @GetMapping("addToCart")
    public String addToCart(Product product1) {
        String status;
        try {
            Product product = productService.findPro(product1.getId());
            if (product != null) {
                productService.addToCart(product.getUid(), product.getPimg(), product.getProname(), product.getIntroduce(), product.getPrice(), product.getNumber(), product.getType());
            } else {
                productService.updatenumber(product1.getUid(),product1.getNumber()+product.getNumber());
            }
            status = "添加商品成功";
        } catch (Exception e) {
            e.printStackTrace();
            status = e.getMessage();
        }
        return status;
    }
*/
}
