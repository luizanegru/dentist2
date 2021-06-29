package com.license.dentist.controller;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.ProfileForDoctor;
import com.license.dentist.entities.User;
import com.license.dentist.repository.DoctorRepository;
import com.license.dentist.repository.HistoricRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.repository.UserRoleRepository;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.request.ShowDoctorsRequest;
import com.license.dentist.request.ShowPatientsRequest;
import com.license.dentist.response.ShowDoctorsResponse;
import com.license.dentist.response.ShowPatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class ShowPatientsController {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HistoricRepository historicRepository;

    @GetMapping("/showPatients")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<ShowPatientResponse> getPatients() {
        List<ShowPatientsRequest> showPatientsRequests = new ArrayList<>();
        List<Integer> idsDoctorUser = userRoleRepository.getIdPatientUser();

        for (Integer id: idsDoctorUser) {
            User user = userRepository.getOne(id.longValue());
            ShowPatientsRequest showPatientsRequest = new ShowPatientsRequest();
            showPatientsRequest.setId(user.getId().intValue());
            showPatientsRequest.setFirstName(user.getFirstName());
            showPatientsRequest.setLastName(user.getLastName());


            showPatientsRequests.add(showPatientsRequest);
        }

        return ResponseEntity.ok(new ShowPatientResponse(200, "Get doctors successfully!", showPatientsRequests));
    }
}
