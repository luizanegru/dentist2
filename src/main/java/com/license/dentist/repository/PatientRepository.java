package com.license.dentist.repository;

import com.license.dentist.entities.Patient;
import com.license.dentist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
