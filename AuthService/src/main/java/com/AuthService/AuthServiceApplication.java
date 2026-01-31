package com.AuthService;

import com.AuthService.Entity.Role;
import com.AuthService.Enums.RoleTypes;
import com.AuthService.Repositorys.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepo roleRepo) {
        return args -> System.out.println("AuthService started ...");
	}

}
