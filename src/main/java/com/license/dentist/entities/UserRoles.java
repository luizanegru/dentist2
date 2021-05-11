package com.license.dentist.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Table(	name = "user_roles")
@Entity
public class UserRoles {

    @Id
    Integer id;

    Integer userRole;
}
