package com.example.demo.entity;

import lombok.Data;

@Data
public class JwtRequest {

    private String userName;
    private String userPassword;
}
