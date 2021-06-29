package com.license.dentist.controller;


import com.license.dentist.entities.Appointment;
import com.license.dentist.entities.User;
import com.license.dentist.repository.AppointmentRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.AppointmentRequest;
import com.license.dentist.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class DeleteAppointmentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    EmailController sendEmail;

    @DeleteMapping("/delete")
    // @PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAppointment(@RequestParam Integer appointmentId) throws IOException, MessagingException {

        Appointment appointment = appointmentRepository.getOne(appointmentId);
        User patient =  userRepository.getOne(Long.valueOf(appointment.getPatient()));
        User doctor =  userRepository.getOne(Long.valueOf(appointment.getDoctor()));


        String content = "Buna ziua! Pacientul " + patient.getLastName() + " " + patient.getFirstName() + " " +
                "a anulat consultatia din: " + appointment.getDateTime() + ".";


        sendEmail.sendEmail(doctor.getEmail(), "APPOINTMENT CANCELED", content);
        appointmentRepository.delete(appointment);

        return ResponseEntity.ok(new MessageResponse("This appointment deleted successfully!"));
    }
}
