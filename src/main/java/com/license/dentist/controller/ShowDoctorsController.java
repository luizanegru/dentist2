package com.license.dentist.controller;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.ProfileForDoctor;
import com.license.dentist.entities.User;
import com.license.dentist.repository.DoctorRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.repository.UserRoleRepository;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.request.ShowDoctorsRequest;
import com.license.dentist.response.HistoricResponse;
import com.license.dentist.response.ShowDoctorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class ShowDoctorsController {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/showDoctors")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<ShowDoctorsResponse> getDoctors() {
            List<ShowDoctorsRequest> showDoctorsRequests = new ArrayList<>();
            List<Integer> idsDoctorUser = userRoleRepository.getIdDoctorUser();

            for (Integer id: idsDoctorUser) {
                User user = userRepository.getOne(id.longValue());
                ShowDoctorsRequest showDoctorsRequest = new ShowDoctorsRequest();
                showDoctorsRequest.setId(user.getId().intValue());
                showDoctorsRequest.setFirstName(user.getFirstName());
                showDoctorsRequest.setLastName(user.getLastName());

                ProfileForDoctor profileForDoctor = doctorRepository.getDoctorProfileByUserID(id.toString());
                if(profileForDoctor != null) {
                    showDoctorsRequest.setCourses(profileForDoctor.getCourses());
                    showDoctorsRequest.setDescription(profileForDoctor.getDescription());
                    showDoctorsRequest.setSpecialization(profileForDoctor.getSpecialization());
                }
                showDoctorsRequests.add(showDoctorsRequest);
            }

        return ResponseEntity.ok(new ShowDoctorsResponse(200, "Get doctors successfully!", showDoctorsRequests));
    }
}
