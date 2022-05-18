package com.example.demo.service;

import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerEmployee(User user){

        Optional<User> employeeOptional = userRepository.findUserByUsername(user.getUserName());

        if (employeeOptional.isPresent()){
            throw new IllegalStateException("Employee already exists");
        }

        Role emp_role = roleRepository.findByRoleName("Employee").get();

        Set<Role> emp_roles = new HashSet<>();
        emp_roles.add(emp_role);
        user.setRoles((Set<Role>) emp_roles);


        return userRepository.save(user);
    }

    public User registerContractor(User user){

        Optional<User> contractorOptional = userRepository.findUserByUsername(user.getUserName());

        if (contractorOptional.isPresent()){
            throw new IllegalStateException("Contractor already exists");
        }

        Role role = roleRepository.findByRoleName("Contractor").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles((Set<Role>) roles);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long user_id) {
        boolean exists = userRepository.existsById(user_id);

        if (!exists){
            throw new IllegalStateException("user does not exist");
        }

        userRepository.deleteById(user_id);
    }

    public void updateUser(Long user_id, String userName, String userPassword, String firstName, String lastName) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalStateException("user does not exist"));

        if (userName != null && userName.length() > 0 && !Objects.equals(user.getUserName(), userName)){
            Optional<User> userOptional = userRepository.findUserByUsername(userName);

            if (userOptional.isPresent()){
                throw new IllegalStateException("user exixsts");
            }
            user.setUserName(userName);
        }

        if (userPassword != null && userPassword.length() > 0 && !Objects.equals(user.getUserPassword(), userPassword)){
            user.setUserPassword(userPassword);
        }

        if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)){
            user.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)){
            user.setLastName(lastName);
        }
    }

    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role contractorRole = new Role();
        contractorRole.setRoleName("Contractor");
        contractorRole.setRoleDescription("contractor");
        roleRepository.save(contractorRole);

        Role employeeRole = new Role();
        employeeRole.setRoleName("Employee");
        employeeRole.setRoleDescription("employee");
        roleRepository.save(employeeRole);

        User adminUser = new User();
        adminUser.setFirstName("David");
        adminUser.setLastName("Titus");
        adminUser.setEmail("tbang350@gmal.com");
        adminUser.setUserName("tbang350");
        adminUser.setUserPassword(getEncodePassword("password"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);

        User contractor = new User();
        contractor.setFirstName("Kevin");
        contractor.setLastName("Smith");
        contractor.setEmail("ksmith32@gmal.com");
        contractor.setUserName("ksmith69");
        contractor.setUserPassword(getEncodePassword("password"));
        Set<Role> contractorRoles = new HashSet<>();
        contractorRoles.add(contractorRole);
        contractor.setRoles(contractorRoles);
        userRepository.save(contractor);

        User employee = new User();
        employee.setFirstName("James");
        employee.setLastName("Smith");
        employee.setEmail("ksmith32@gmal.com");
        employee.setUserName("jsmith69");
        employee.setUserPassword(getEncodePassword("password"));
        Set<Role> employeeRoles = new HashSet<>();
        employeeRoles.add(employeeRole);
        employee.setRoles(employeeRoles);
        userRepository.save(employee);

    }

    public String getEncodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
