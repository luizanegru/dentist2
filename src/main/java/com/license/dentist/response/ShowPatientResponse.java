package com.license.dentist.response;

import com.license.dentist.request.ShowDoctorsRequest;
import com.license.dentist.request.ShowPatientsRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowPatientResponse {
    private Integer code;
    private String message;
    private List<ShowPatientsRequest> showPatientsRequests;
}
