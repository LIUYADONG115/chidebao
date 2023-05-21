package com.tw.heima.paiyipai.controller.model;

import lombok.Data;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−05-16 9:18 下午
 */
@Data
public class User {
    private String name;
    private int age;
    public User(){
        name = "刘亚东";
        age = 28;
    }
}
