package com.license.dentist.request;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ProfilePatientRequest {

    @NotBlank
    private Integer age;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private String cnp;

    @NotBlank
    @Size(max = 200)
    private String address;

    @NotBlank
    @Size(min = 10, max = 10)
    private String phoneNumber;

    private String username;
}
