package com.license.dentist.response;


import com.license.dentist.request.HistoricRequest;
import com.license.dentist.request.ShowPatientProfileRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricResponse {
    private Integer code;
    private String message;
    private HistoricRequest historicRequest;
}
