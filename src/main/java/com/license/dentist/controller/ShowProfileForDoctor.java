package com.license.dentist.controller;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.ProfileForDoctor;
import com.license.dentist.entities.User;
import com.license.dentist.repository.DoctorRepository;
import com.license.dentist.repository.PatientRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.DoctorRequest;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.response.DoctorResponse;
import com.license.dentist.response.HistoricResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class ShowProfileForDoctor {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/showProfileForDoctor")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<DoctorResponse> getUserHistoric(@RequestParam String userName) {
        try {
            User currentUser = userRepository.findByUsername(userName).get();
            boolean existsDoctorProfileForUser = doctorRepository.existsDoctorProfileForUser(currentUser.getId().toString());
            DoctorRequest doctorRequest = new DoctorRequest();
            if (existsDoctorProfileForUser) {
                ProfileForDoctor profileForDoctor = doctorRepository.getDoctorProfileByUserID(currentUser.getId().toString());
                doctorRequest.setCourses(profileForDoctor.getCourses());
                doctorRequest.setDescription(profileForDoctor.getDescription());
                doctorRequest.setSpecialization(profileForDoctor.getSpecialization());
            } else {
                return ResponseEntity.ok(new DoctorResponse(404, "NOT FOUND!", doctorRequest));

            }

            return ResponseEntity.ok(new DoctorResponse(200, "Get profile successfully!", doctorRequest));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new DoctorResponse(500, "Something went wrong", null));
        }
    }
}
