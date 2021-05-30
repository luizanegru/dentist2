package com.license.dentist.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(	name = "user_roles")
@Entity
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer userRole;
}
