package com.example.ems_3.entity;

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
@Accessors(chain=true)


public class Orderup implements Serializable {
    private Integer uid;//用户id
    private Integer oid;//订单id
    private Integer pid;//商品id

    private String pimg;
    private String proname;
    private String introduce;
    private double price;
    private Integer number;
    private String type;

}
