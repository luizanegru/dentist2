package com.license.dentist.controller;

import com.license.dentist.entities.Appointment;
import com.license.dentist.entities.Historic;
import com.license.dentist.entities.User;
import com.license.dentist.repository.AppointmentRepository;
import com.license.dentist.repository.HistoricRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.response.HistoricResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class AllProfileHistoryController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("/history")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<HistoricResponse> getHistory(@RequestParam Integer id) {
        try {
            Appointment appointment = appointmentRepository.getOne(id);
            User currentUser = userRepository.getOne(Long.valueOf(appointment.getPatient()));
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
