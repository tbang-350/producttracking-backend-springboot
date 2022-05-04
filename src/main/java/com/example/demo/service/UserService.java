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

        Role role = roleDao.findById("Contractor").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setUserPassword(getEncodePassword(user.getUserPassword()));

       return userDao.save(user);
    }

    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role contractorRole = new Role();
        contractorRole.setRoleName("Contractor");
        contractorRole.setRoleDescription("contractor");
        roleDao.save(contractorRole);

        Role employeeRole = new Role();
        employeeRole.setRoleName("Employee");
        employeeRole.setRoleDescription("employee");
        roleDao.save(employeeRole);

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

//        User contractor = new User();
//        contractor.setFirstname("Kevin");
//        contractor.setLastname("Smith");
//        contractor.setEmail("ksmith32@gmal.com");
//        contractor.setUserName("ksmith69");
//        contractor.setUserPassword(getEncodePassword("password"));
//        Set<Role> contractorRoles = new HashSet<>();
//        contractorRoles.add(contractorRole);
//        contractor.setRoles(contractorRoles);
//        userDao.save(contractor);
//
//        User employee = new User();
//        employee.setFirstname("James");
//        employee.setLastname("Smith");
//        employee.setEmail("ksmith32@gmal.com");
//        employee.setUserName("jsmith69");
//        employee.setUserPassword(getEncodePassword("password"));
//        Set<Role> employeeRoles = new HashSet<>();
//        employeeRoles.add(employeeRole);
//        employee.setRoles(employeeRoles);
//        userDao.save(employee);

    }

    public String getEncodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
