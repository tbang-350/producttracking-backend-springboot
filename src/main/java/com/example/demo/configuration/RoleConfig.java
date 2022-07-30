package com.example.demo.configuration;


import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Data
@Configuration
public class RoleConfig {

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            List<Role> roles = roleRepository.getRoles();

            if(roles.isEmpty()){
                Role adminRole = new Role(
                        "Admin",
                        "Admin role"
                );

                Role contractorRole = new Role(
                        "Contractor",
                        "Contractor role"
                );

                Role employeeRole = new Role(
                        "Employee",
                        "Employee role"
                );

                roleRepository.saveAll(List.of(adminRole, contractorRole, employeeRole));
            }
        };
    }
}

