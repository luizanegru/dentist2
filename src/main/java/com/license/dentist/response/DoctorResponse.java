package com.license.dentist.response;


import com.license.dentist.request.DoctorRequest;
import com.license.dentist.request.HistoricRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private Integer code;
    private String message;
    private DoctorRequest doctorRequest;
}
