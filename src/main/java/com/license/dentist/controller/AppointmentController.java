package com.license.dentist.controller;

import com.license.dentist.email.controller.SendEmailController;
import com.license.dentist.email.domain.EmailRequest;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collections;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class AppointmentController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    SendEmailController sendEmailController;

    @Autowired
    EmailController sendEmail;

    @PostMapping("/add")
   // @PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest appointmentRequest) throws IOException, MessagingException {

        Appointment appointment = new Appointment();
        appointment.setDateTime(appointmentRequest.getDateTime());
        appointment.setPatient(appointmentRequest.getPatient());
        appointment.setDoctor(appointmentRequest.getDoctor());
        System.out.println(appointment.getDoctor());
        appointmentRepository.save(appointment);

        User patient =  userRepository.getOne(Long.valueOf(appointmentRequest.getPatient()));
        User doctor =  userRepository.getOne(Long.valueOf(appointmentRequest.getDoctor()));

        String patientContent = "Buna ziua! Va asteptam pe data si la ora: " + appointmentRequest.getDateTime() + " la adresa: " +
                "Strada Orizont, nr. 14, Bucuresti, Sector 5, pentru consultatia la doctorul: " +  doctor.getLastName()
                + " " + doctor.getFirstName()
                + ". Daca doriti sa anulati consultatia  sunati la numarul de telefon: 0740901907 sau direct din aplicatie.";
        sendEmail.sendEmail(patient.getEmail(), "APPOINTMENT", patientContent);

        String doctorContent = "Buna ziua! S-a realizat o programare pe data si la ora: " + appointmentRequest.getDateTime() + " pentru pacientul: "
                + patient.getLastName() + " " + patient.getFirstName() + ".";
        sendEmail.sendEmail(doctor.getEmail(), "APPOINTMENT", doctorContent);
        return ResponseEntity.ok(new MessageResponse("This appointment saved successfully!"));
    }
}
