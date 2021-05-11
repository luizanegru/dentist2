package com.license.dentist.request;

import com.license.dentist.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {

    private String specialization;

    private String description;

    private String courses;

    private String user;
}
