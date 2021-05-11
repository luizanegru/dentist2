package com.license.dentist.repository;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.ProfileForDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<ProfileForDoctor, Integer> {
    @Query(value = "select * from doctor_profile where username = :userId", nativeQuery = true)
    ProfileForDoctor getDoctorProfileByUserID(String userId);

    @Query(value = "select case when count(username)> 0 then true else false end from ProfileForDoctor  where username = :userId")
    boolean existsDoctorProfileForUser( String userId);
}
