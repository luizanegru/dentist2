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
@Table(	name = "doctor_profile")
public class ProfileForDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String specialization;

    @NotBlank
    private String description;

    @NotBlank
    private String courses;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;
}
