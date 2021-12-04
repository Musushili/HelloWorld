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
@Accessors(chain = true)
public class User implements Serializable {
    private Integer id;

    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String num;

    private String uimg;

}
