package com.license.dentist.repository;

import com.license.dentist.entities.ERole;
import com.license.dentist.entities.Patient;
import com.license.dentist.entities.Role;
import com.license.dentist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByCnp(String cnp);

    @Query(value = "select  case when count(cnp)> 0 then true else false end from Patient  where cnp = :cnp")
    boolean existsPatientWithCnp( String cnp);

    @Query(value = "select * from patient_profile where username = :userId", nativeQuery = true)
    Patient getPatientByUserID( String userId);

    @Query(value = "select  case when count(username)> 0 then true else false end from Patient  where username = :userId")
    boolean existsProfileForUser( String userId);
}
