package com.license.dentist.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllProfileResponse {
    private Integer code;
    private String message;
    private List<GetUserProfileResponse> getUserProfileResponses;
}
