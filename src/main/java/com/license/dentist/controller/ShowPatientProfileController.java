package com.license.dentist.controller;

import com.license.dentist.entities.Patient;
import com.license.dentist.entities.User;
import com.license.dentist.repository.PatientRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.ProfilePatientRequest;
import com.license.dentist.request.ShowPatientProfileRequest;
import com.license.dentist.response.PatientProfileResponse;
import com.license.dentist.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class ShowPatientProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/showPatient")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<PatientProfileResponse> getUserProfile(@RequestParam String userName) {
        try {
            User currentUser = userRepository.findByUsername(userName).get();
            boolean existsProfileForUser = patientRepository.existsProfileForUser(currentUser.getId().toString());
            ShowPatientProfileRequest showPatientProfileRequest = new ShowPatientProfileRequest();
            if(existsProfileForUser) {
                Patient patient = patientRepository.getPatientByUserID(currentUser.getId().toString());
                showPatientProfileRequest.setAddress(patient.getAddress());
                showPatientProfileRequest.setAge(patient.getAge());
                showPatientProfileRequest.setBirthDate(patient.getBirthDate());
                showPatientProfileRequest.setCnp(patient.getCnp());
                showPatientProfileRequest.setPhoneNumber(patient.getPhoneNumber());
            }

            showPatientProfileRequest.setUsername(currentUser.getUsername());
            showPatientProfileRequest.setFirstName(currentUser.getFirstName());
            showPatientProfileRequest.setLastName(currentUser.getLastName());
            showPatientProfileRequest.setEmail(currentUser.getEmail());

            return ResponseEntity.ok(new PatientProfileResponse(200, "Get profile successfully!", showPatientProfileRequest));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new PatientProfileResponse(500, "Something went wrong", null));
        }

    }
}
