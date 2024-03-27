package com.etiya.rentacar.entities;

import com.etiya.rentacar.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String email;
    private String username;
    private String password;
    private String role;
}
