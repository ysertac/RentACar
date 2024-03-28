package com.etiya.rentacar.business.dtos.requests.customerRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String identificationNumber;
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "([(]?)([5])([0-9]{2})([)]?)([\s]?)([0-9]{3})([\s]?)([0-9]{2})([\s]?)([0-9]{2})$") // 546
    private String phoneNumber;
    private String password;
}