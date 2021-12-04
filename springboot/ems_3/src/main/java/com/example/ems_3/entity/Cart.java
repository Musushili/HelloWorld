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


public class Cart implements Serializable {
    private Integer uid;
    private Integer id;
    private Integer pid;

    private String pimg;
    private String proname;
    private String introduce;
    private double price;
    private Integer number;
    private String type;
}
