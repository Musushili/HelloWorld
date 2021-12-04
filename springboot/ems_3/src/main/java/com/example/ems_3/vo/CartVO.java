package com.example.ems_3.vo;

import com.example.ems_3.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
/*代表值对象的意思，通常用于业务层之间的数据传递，由new创建，由GC回收。
主要体现在视图的对象，对于一个WEB页面将整个页面的属性封装成一个对象，然后用一个VO对象在控制层与视图层进行传输交换。*/
public class CartVO extends Cart implements Serializable {
    private Integer uid;//用户id
    private Integer id;//购物车中的一条数据
    private Integer pid;//商品id

    private String pimg;
    private String proname;
    private String introduce;
    private double price;
    private Integer number;
    private String type;
}
