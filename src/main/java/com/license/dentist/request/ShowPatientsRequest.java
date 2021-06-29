package com.license.dentist.request;

import lombok.Data;

@Data
public class ShowPatientsRequest {
    Integer id;

    String firstName;

    String lastName;

    String allergy;

    String disease;

    String other;
}
