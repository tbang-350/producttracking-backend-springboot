package com.example.demo.controller;

import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin("http://localhost:4200")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
       return jwtService.createJwtToken(jwtRequest);
    }
}
