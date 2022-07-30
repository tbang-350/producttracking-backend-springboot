package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto implements Serializable {
    private final static long serialVersionUID = 1L;

    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userPassword;
    private String phoneNumber;
    private LocalDateTime registeredAt;
}
