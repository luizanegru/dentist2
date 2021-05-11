package com.license.dentist.request;

import com.license.dentist.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricRequest {
    private String allergy;

    private String disease;

    private String gender;

    private String other;

    private String user;
}
