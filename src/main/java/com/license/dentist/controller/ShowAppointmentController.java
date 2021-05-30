package com.license.dentist.controller;

import com.license.dentist.entities.Appointment;
import com.license.dentist.entities.ProfileForDoctor;
import com.license.dentist.entities.User;
import com.license.dentist.repository.AppointmentRepository;
import com.license.dentist.repository.UserRepository;
import com.license.dentist.request.ShowDoctorsRequest;
import com.license.dentist.response.AppointmentResponse;
import com.license.dentist.response.ShowAppointmentResponse;
import com.license.dentist.response.ShowDoctorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class ShowAppointmentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("/showAppointmentForPatient")
    //@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<AppointmentResponse> getAppointmentsForPatient(@RequestParam Integer id) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        List<ShowAppointmentResponse> showAppointmentResponses= new ArrayList<>();
        List<Appointment> appointmentsForUser = appointmentRepository.getAppointmentForUser(id);

        for (Appointment appointment: appointmentsForUser) {
            User patient = userRepository.getOne(appointment.getPatient().longValue());
            User doctor = userRepository.getOne(appointment.getDoctor().longValue());

            ShowAppointmentResponse showAppointmentResponse = new ShowAppointmentResponse();
            showAppointmentResponse.setId(appointment.getId());
            showAppointmentResponse.setDoctorName(doctor.getFirstName() + "  " + doctor.getLastName());
            showAppointmentResponse.setPatientName(patient.getFirstName() + " " + patient.getLastName());

            Timestamp stamp = new Timestamp(appointment.getDateTime().getTime());
            Date date = new Date(stamp.getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy h:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String formattedDate = sdf.format(date);
            showAppointmentResponse.setDate(formattedDate);
            showAppointmentResponses.add(showAppointmentResponse);

        }

        return ResponseEntity.ok(new AppointmentResponse(200, "Get appointments successfully!", showAppointmentResponses));
    }
}
