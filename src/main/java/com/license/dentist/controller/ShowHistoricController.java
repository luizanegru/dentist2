package com.license.dentist.controller;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.Patient;
import com.license.dentist.entities.User;
import com.license.dentist.repository.HistoricRepository;
import com.license.dentist.repository.PatientRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.request.ShowPatientProfileRequest;
import com.license.dentist.response.HistoricResponse;
import com.license.dentist.response.PatientProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class ShowHistoricController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoricRepository historicRepository;

    @GetMapping("/showHistoric")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<HistoricResponse> getUserHistoric(@RequestParam String userName) {
        try {
            User currentUser = userRepository.findByUsername(userName).get();
            boolean existsHistoricForUser = historicRepository.existsHistoricForUser(currentUser.getId().toString());
            HistoricRequest historicRequest = new HistoricRequest();
            if(existsHistoricForUser) {
                Historic  historic = historicRepository.getHistoricByUserID(currentUser.getId().toString());
                historicRequest.setAllergy(historic.getAllergy());
                historicRequest.setDisease(historic.getDisease());
                historicRequest.setOther(historic.getOther());
                historicRequest.setGender(historic.getGender());
            } else {
                return ResponseEntity.ok(new HistoricResponse(404, "NOT FOUND!", historicRequest));

            }

            return ResponseEntity.ok(new HistoricResponse(200, "Get profile successfully!", historicRequest));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new HistoricResponse(500, "Something went wrong", null));
        }
    }

}
