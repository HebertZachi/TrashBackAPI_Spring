package br.com.fiap.traskBackAPI.dto;

import br.com.fiap.traskBackAPI.model.User;

public record UserSignUpReturnDTO(
        String name,
        String email,
        String role
) {

    public UserSignUpReturnDTO(User user) {
        this(
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
