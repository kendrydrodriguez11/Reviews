package com.example.auth.controller.required;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
@Data
@AllArgsConstructor
public class RegisterUser {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank @Email
    private String email;

    @NotNull
    private Set<String> roles;

}
