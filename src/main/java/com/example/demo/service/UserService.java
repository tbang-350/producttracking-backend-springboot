package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
       return userDao.save(user);
    }

    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("Contractor");
        userRole.setRoleDescription("contractor");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setFirstname("David");
        adminUser.setLastname("Titus");
        adminUser.setEmail("tbang350@gmal.com");
        adminUser.setUserName("tbang350");
        adminUser.setUserPassword(getEncodePassword("password"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setFirstname("Kevin");
        user.setLastname("Smith");
        user.setEmail("ksmith32@gmal.com");
        user.setUserName("ksmith69");
        user.setUserPassword(getEncodePassword("password"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        userDao.save(user);

    }

    public String getEncodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
