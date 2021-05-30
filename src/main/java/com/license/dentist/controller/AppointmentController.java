package com.license.dentist.controller;

import com.license.dentist.entities.Appointment;
import com.license.dentist.entities.Historic;
import com.license.dentist.entities.User;
import com.license.dentist.repository.AppointmentRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.AppointmentRequest;
import com.license.dentist.request.HistoricRequest;
import com.license.dentist.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class AppointmentController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {



        Appointment appointment = new Appointment();
        appointment.setDateTime(appointmentRequest.getDateTime());
        appointment.setPatient(appointmentRequest.getPatient());
        appointment.setDoctor(appointmentRequest.getDoctor());
        System.out.println(appointment.getDoctor());
        appointmentRepository.save(appointment);

        return ResponseEntity.ok(new MessageResponse("This appointment saved successfully!"));
    }
}
