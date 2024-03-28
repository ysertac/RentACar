package com.etiya.rentacar.business.dtos.responses.customerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
