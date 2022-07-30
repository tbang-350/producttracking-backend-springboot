package com.example.demo.controller;

import com.example.demo.Dto.UserDto;
import com.example.demo.Dto.UserUpdateDto;
import com.example.demo.entity.ContractorChartdata;
import com.example.demo.entity.EmployeeChartdata;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/registerContractor")
    public User registerContractor(@RequestBody UserDto userDto){
        return userService.registerContractor(userDto);
    }

    @PostMapping("/registerEmployee")
    public User registerEmployee(@RequestBody UserDto userDto){
        return userService.registerEmployee(userDto);
    }

    @PostMapping("/registerAdmin")
    public User registerAdmin(@RequestBody UserDto userDto){
        return userService.registerAdmin(userDto);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getContractors")
    public List<User> getContractors(){
        return userRepository.findAllContractors();
    }

    @GetMapping("/getEmployees")
    public List<User> getEmployee(){
        return userRepository.findAllEmployees();
    }

    @DeleteMapping("/deleteUser/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long user_id){
        userService.deleteUser(user_id);
    }

    @PutMapping("/updateContractor")
    public ResponseEntity<User> updateContractor(@RequestBody UserUpdateDto userUpdateDto){
        return userService.updateContractor(userUpdateDto);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<User> updateEmployee(@RequestBody UserUpdateDto userUpdateDto){
        return userService.updateEmployee(userUpdateDto);
    }

    @GetMapping("/countContractors")
    public int countContractors(){
        return  userRepository.countAllContractors();
    }

    @GetMapping("/countEmployees")
    public int countEmployees(){
        return userRepository.countAllEmployees();
    }
    @GetMapping("/countAllUsers")
    public int countAllUsers(){
        return userRepository.countAllUsers();
    }

    @GetMapping("/getChartdata")
    public EmployeeChartdata getChartdata(){
        return userRepository.getChartdata();
    }

    @GetMapping("/getContractorChartdata")
    public ContractorChartdata getContractorChartdata(){
        return userRepository.getContractorChartdata();
    }


}
