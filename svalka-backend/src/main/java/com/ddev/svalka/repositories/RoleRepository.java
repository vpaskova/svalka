package com.ddev.svalka.repositories;

import com.ddev.svalka.entities.ERole;
import com.ddev.svalka.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
