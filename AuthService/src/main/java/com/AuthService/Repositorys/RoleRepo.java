package com.AuthService.Repositorys;

import java.util.Optional;

import com.AuthService.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    boolean existsByName(String roleName);

    Optional<Role> findByName(String name);
}
