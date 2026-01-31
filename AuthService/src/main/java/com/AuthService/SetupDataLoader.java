package com.AuthService;

import com.AuthService.Entity.AppUser;
import com.AuthService.Entity.Role;
import com.AuthService.Enums.RoleTypes;
import com.AuthService.Repositorys.AuthRepo;
import com.AuthService.Repositorys.RoleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private AuthRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;
        createRoleIfNotFound(RoleTypes.ADMIN.name());
        createRoleIfNotFound(RoleTypes.USER.name());

        Role adminRole = roleRepository.findByName(RoleTypes.ADMIN.name()).orElseThrow(
                () -> new RuntimeException("Could not find the Specified Role")
        );
        
        // then just creating the admin user
        AppUser user = new AppUser();
        user.setUsername("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setUserRoles(Set.of(adminRole));
        
        AppUser existsByEmail = userRepository.findByEmail("test@test.com").orElse(null);
        if (existsByEmail == null) {
            userRepository.save(user);
        }
        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name).orElse(null);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }
    
}
