package com.license.dentist.controller;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.Patient;
import com.license.dentist.entities.User;
import com.license.dentist.repository.HistoricRepository;
import com.license.dentist.repository.PatientRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.request.ProfilePatientRequest;
import com.license.dentist.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class AddHistoricController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoricRepository historicRepository;

    @PostMapping("/addHistoric")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createHistoric(@RequestBody HistoricRequest historicRequest) {

        User currentUser = userRepository.findByUsername(historicRequest.getUser()).get();
        boolean existHistoricForUser = historicRepository.existsHistoricForUser(currentUser.getId().toString());
        if(existHistoricForUser) {
            Historic historic = historicRepository.getHistoricByUserID(currentUser.getId().toString());
            if(!historicRequest.getAllergy().equals("")) {
                historic.setAllergy(historicRequest.getAllergy());
            } else {
                historic.setAllergy(historic.getAllergy());
            }
            if(!historicRequest.getDisease().equals("")) {
                historic.setDisease(historicRequest.getDisease());
            }else{
                historic.setDisease(historic.getDisease());
            }
            if(!historicRequest.getGender().equals("")) {
                historic.setGender(historicRequest.getGender());
            }else{
                historic.setGender(historic.getGender());
            }
            if(!historicRequest.getOther().equals("")) {
                historic.setOther(historicRequest.getOther());
            }else{
                historic.setOther(historic.getOther());
            }
            historic.setUser(currentUser);
            historicRepository.save(historic);

        } else {
            Historic historic = new Historic();
            historic.setUser(currentUser);
            historic.setAllergy(historicRequest.getAllergy());
            historic.setDisease(historicRequest.getDisease());
            historic.setGender(historicRequest.getGender());
            historic.setOther(historicRequest.getOther());
            historicRepository.save(historic);
        }

        return ResponseEntity.ok(new MessageResponse("Historic for patient saved successfully!"));
    }
}
