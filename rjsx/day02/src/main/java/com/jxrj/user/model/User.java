package com.jxrj.user.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class User {

    private String id;
    private String username;
    private String password;
    private int age;
    private String birthday;
    private String email;
    private String token;
}
