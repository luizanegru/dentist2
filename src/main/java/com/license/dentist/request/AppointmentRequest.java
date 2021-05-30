package com.license.dentist.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    private Integer doctor;

    private Integer patient;

    private Timestamp dateTime;

}
