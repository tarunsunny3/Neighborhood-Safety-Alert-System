package com.neighborhood.authService.authService.dto;


import com.neighborhood.authService.authService.model.enums.Role;
import lombok.Data;

import java.util.Set;

@Data

public class SignupDTO {
    private String username;
    private String name;
    private String password;
    private Set<Role> roles;
}
