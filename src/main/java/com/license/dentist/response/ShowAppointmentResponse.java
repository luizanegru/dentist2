package com.license.dentist.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowAppointmentResponse {
    private Integer id;

    private String doctorName;

    private String patientName;

    private String date;
}
