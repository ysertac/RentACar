package com.etiya.rentacar.business.dtos.requests.customerRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 6, max = 15)
    private String username;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 15)
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 15)
    private String lastName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[0,2,4,6,8]{1}$")
    private String identificationNumber;
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "([(]?)([5])([0-9]{2})([)]?)([\\s]?)([0-9]{3})([\\s]?)([0-9]{2})([\\s]?)([0-9]{2})$") // 546
    private String phoneNumber;
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$")
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
}
