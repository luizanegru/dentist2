package com.license.dentist.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "patient_profile",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cnp")
        })
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @OneToOne
    @JoinColumn(name = "username")
    private User user;
}
