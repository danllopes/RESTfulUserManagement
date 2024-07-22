package br.com.danllopes.usermanagment.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(

        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
