package com.license.dentist.controller;

import com.license.dentist.entities.Patient;
import com.license.dentist.repository.PatientRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.LoginRequest;
import com.license.dentist.request.ProfilePatientRequest;
import com.license.dentist.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class PatientProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/patientProfile")
    @PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> authenticateUser(@RequestBody ProfilePatientRequest profilePatientRequest) {
        Patient patient = new Patient();
        patient.setAddress(profilePatientRequest.getAddress());
        patient.setAge(profilePatientRequest.getAge());
        patient.setBirthDate(profilePatientRequest.getBirthDate());
        patient.setCnp(profilePatientRequest.getCnp());
        patient.setPhoneNumber(profilePatientRequest.getPhoneNumber());
        patient.setUser(userRepository.findByUsername(profilePatientRequest.getUsername()).get());

        patientRepository.save(patient);
        return ResponseEntity.ok(new MessageResponse("Profile for patient saved successfully!"));
    }
}
