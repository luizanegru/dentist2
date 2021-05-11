package com.license.dentist.controller;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.User;
import com.license.dentist.repository.UserRoleRepository;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.response.HistoricResponse;
import com.license.dentist.response.ShowDoctorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class ShowDoctorsController {

    @Autowired
    UserRoleRepository userRoleRepository;

    @GetMapping("/showDoctors")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Integer>> getDoctors() {
      //  try {

            List<Integer> da = userRoleRepository.getIdDoctorUser();
            return ResponseEntity.ok(da);

//        } catch (Exception e) {
//            e.printStackTrace();
//              }
    }
}
