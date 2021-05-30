package com.license.dentist.response;

import com.license.dentist.request.DoctorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Integer code;
    private String message;
    private List<ShowAppointmentResponse> showAppointmentResponse;
}
