package com.license.dentist.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowPatientProfileRequest {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    private LocalDate birthDate;

    private String cnp;

    private String address;

    private String phoneNumber;

}
