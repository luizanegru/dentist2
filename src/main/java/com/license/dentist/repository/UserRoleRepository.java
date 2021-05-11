package com.license.dentist.repository;

import com.license.dentist.entities.Patient;
import com.license.dentist.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Integer> {
    @Query(value = "select user_id from user_roles where role_id = 2", nativeQuery = true)
    List<Integer> getIdDoctorUser();
}
