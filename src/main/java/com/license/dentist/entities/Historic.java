package com.license.dentist.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "historic")
public class Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String allergy;

    private String disease;

    private String gender;

    private String  other;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;
}
