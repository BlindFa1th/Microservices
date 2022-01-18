package com.team.up.serviceroles.repo;

import com.team.up.serviceroles.repo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findById(Long Id);
}
