package com.license.dentist.controller;

import com.license.dentist.entities.Patient;
import com.license.dentist.entities.User;
import com.license.dentist.repository.PatientRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.LoginRequest;
import com.license.dentist.request.ProfilePatientRequest;
import com.license.dentist.response.MessageResponse;
import com.license.dentist.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class PatientProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/patient")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createProfile(@RequestBody ProfilePatientRequest profilePatientRequest) {
        Patient patient = new Patient();
        User currentUser = userRepository.findByUsername(profilePatientRequest.getUsername()).get();
        boolean existProfileForUser = patientRepository.existsProfileForUser(currentUser.getId().toString());
        if(existProfileForUser) {
            Patient patientToDelete = patientRepository.getPatientByUserID(currentUser.getId().toString());
            patientRepository.delete(patientToDelete);
        }
        boolean existCnp = patientRepository.existsPatientWithCnp(profilePatientRequest.getCnp());
        if(existCnp) {
            return ResponseEntity.badRequest().body(new MessageResponse("CNP is duplicated!"));
        }
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
