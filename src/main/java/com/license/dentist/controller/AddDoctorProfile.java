package com.license.dentist.controller;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.ProfileForDoctor;
import com.license.dentist.entities.User;
import com.license.dentist.repository.DoctorRepository;
import com.license.dentist.repository.HistoricRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.DoctorRequest;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class AddDoctorProfile {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping("/addProfileForDoctor")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createProfileForDoctor(@RequestBody DoctorRequest doctorRequest) {
        User currentUser = userRepository.findByUsername(doctorRequest.getUser()).get();
        boolean existsDoctorProfileForUser = doctorRepository.existsDoctorProfileForUser(currentUser.getId().toString());
        if(existsDoctorProfileForUser) {
            ProfileForDoctor profileForDoctor = doctorRepository.getDoctorProfileByUserID(currentUser.getId().toString());
            if(!doctorRequest.getCourses().equals("")) {
                profileForDoctor.setCourses(doctorRequest.getCourses());
            } else {
                profileForDoctor.setCourses(profileForDoctor.getCourses());
            }
            if(!doctorRequest.getDescription().equals("")) {
                profileForDoctor.setDescription(doctorRequest.getDescription());
            } else {
                profileForDoctor.setDescription(profileForDoctor.getDescription());
            }
            if(!doctorRequest.getSpecialization().equals("")) {
                profileForDoctor.setSpecialization(doctorRequest.getSpecialization());
            } else {
                profileForDoctor.setSpecialization(profileForDoctor.getSpecialization());
            }
            profileForDoctor.setUser(currentUser);
            doctorRepository.save(profileForDoctor);

        } else {
            ProfileForDoctor profileForDoctor = new ProfileForDoctor();
            profileForDoctor.setUser(currentUser);
            profileForDoctor.setCourses(doctorRequest.getCourses());
            profileForDoctor.setDescription(doctorRequest.getDescription());
            profileForDoctor.setSpecialization(doctorRequest.getSpecialization());
            doctorRepository.save(profileForDoctor);
        }

        return ResponseEntity.ok(new MessageResponse("Profile for doctor saved successfully!"));
    }

}
