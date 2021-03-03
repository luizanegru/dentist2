package com.license.dentist.repository;

import com.license.dentist.entities.ERole;
import com.license.dentist.entities.Role;
import com.license.dentist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Integer id);
    Optional<Role> findByName(ERole name);
}
