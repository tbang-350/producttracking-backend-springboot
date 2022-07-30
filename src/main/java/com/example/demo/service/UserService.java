package com.example.demo.service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Dto.UserUpdateDto;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;



    public User registerContractor(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        Optional<User> contractorOptional = userRepository.findUserByUsername(user.getUserName());

        if (contractorOptional.isPresent()) {
            throw new IllegalStateException("Contractor already exists");
        }

        Role role = roleRepository.findByRoleName("Contractor").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles((Set<Role>) roles);
        user.setUserPassword(getEncodePassword(user.getUserPassword()));
        user.setRegisteredAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User registerEmployee(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        Optional<User> employeeOptional = userRepository.findUserByUsername(user.getUserName());

        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("Employee already exists");
        }

        Role emp_role = roleRepository.findByRoleName("Employee").get();

        Set<Role> emp_roles = new HashSet<>();
        emp_roles.add(emp_role);
        user.setRoles((Set<Role>) emp_roles);
        user.setUserPassword(getEncodePassword(user.getUserPassword()));
        user.setRegisteredAt(LocalDateTime.now());
        System.out.println(user.getUserPassword());

        return userRepository.save(user);
    }

    public User registerAdmin(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        Optional<User> employeeOptional = userRepository.findUserByUsername(user.getUserName());

        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("Admin already exists");
        }

        Role emp_role = roleRepository.findByRoleName("Admin").get();

        Set<Role> emp_roles = new HashSet<>();
        emp_roles.add(emp_role);
        user.setRoles((Set<Role>) emp_roles);
        user.setUserPassword(getEncodePassword(user.getUserPassword()));
        user.setRegisteredAt(LocalDateTime.now());
        System.out.println(user.getUserPassword());

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

    public ResponseEntity<User> updateContractor(UserUpdateDto userUpdateDto){
        User user = modelMapper.map(userUpdateDto, User.class);

        Role emp_role = roleRepository.findByRoleName("Contractor").get();

        Set<Role> emp_roles = new HashSet<>();
        emp_roles.add(emp_role);
        user.setRoles((Set<Role>) emp_roles);
        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser );
    }

    public ResponseEntity<User> updateEmployee(UserUpdateDto userUpdateDto){
        User user = modelMapper.map(userUpdateDto, User.class);

        Role emp_role = roleRepository.findByRoleName("Employee").get();

        Set<Role> emp_roles = new HashSet<>();
        emp_roles.add(emp_role);
        user.setRoles((Set<Role>) emp_roles);
        User updatedUser = userRepository.save(user);


        return ResponseEntity.ok(updatedUser );
    }

    public String getEncodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
