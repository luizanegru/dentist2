package com.license.dentist.request;

import lombok.Data;

@Data
public class ShowDoctorsRequest {
    Integer id;

    String firstName;

    String lastName;

    String specialization;

    String description;

    String courses;

}
