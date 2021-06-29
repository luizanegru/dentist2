package com.license.dentist.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserProfileResponse {
    private Integer id;

    private String doctorName;

    private String patientName;

    private String date;

    private String allergy;

    private String disease;

    private String other;

}
