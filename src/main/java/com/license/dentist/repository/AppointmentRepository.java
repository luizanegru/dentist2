package com.license.dentist.repository;

import com.license.dentist.entities.Appointment;
import com.license.dentist.entities.Historic;
import com.license.dentist.entities.ProfileForDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "select * from appointment where patient = :userId", nativeQuery = true)
    List<Appointment> getAppointmentForUser(Integer userId);

    @Query(value = "select * from appointment where doctor = :userId", nativeQuery = true)
    List<Appointment> getAppointmentForDoctor(Integer userId);
}
