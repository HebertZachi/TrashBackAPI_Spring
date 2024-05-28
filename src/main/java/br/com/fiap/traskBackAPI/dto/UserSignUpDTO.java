package br.com.fiap.traskBackAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSignUpDTO(

        @NotBlank(message = "Name is mandatory")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email cannot exceed 100 characters")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 3, message = "Password must be at least 3 characters long")
        String password,

        @NotBlank(message = "Role is mandatory")
        String role
) {}
