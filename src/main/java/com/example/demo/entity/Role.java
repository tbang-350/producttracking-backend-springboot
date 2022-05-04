package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Role {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long role_id;
    @Id
    private String roleName;
    private String roleDescription;

//    public Role(String roleName, String roleDescription) {
//        this.roleName = roleName;
//        this.roleDescription = roleDescription;
//    }
}
