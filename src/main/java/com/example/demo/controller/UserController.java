package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }

    @PostMapping("/registerContractor")
    public User registerContractor(User user){
        return userService.registerContractor(user);
    }

    @PostMapping("/registerEmployee")
    public User registerEmployee(User user){
        return userService.registerEmployee(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long user_id){
        userService.deleteUser(user_id);
    }

    @PutMapping("/updateUser/{user_id}")
    public void updateUser(
            @PathVariable("user_id") Long user_id,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String userPassword,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName
    ){
        userService.updateUser(user_id,userName,userPassword,firstName,lastName);
    }
    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "For Admin only";
    }

    @GetMapping("/forContractor")
    @PreAuthorize("hasRole('Contractor')")
    public String forContractor(){
        return "For Contractor  only";
    }

    @GetMapping("/forEmployee")
    @PreAuthorize("hasRole('Employee')")
    public String forEmployee(){
        return "For Employee only";
    }


}
