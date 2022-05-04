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
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long userid;
    private String firstname;
    private String lastname;
    private String email;
    @Id
    private String userName;
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns =
                    @JoinColumn(name = "userid")
            ,
            inverseJoinColumns =
                    @JoinColumn(name = "role_id")

    )
    private Set<Role> roles;

//    public User(String firstname, String lastname, String email, String username, String password, Set<Role> roles) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }
}
