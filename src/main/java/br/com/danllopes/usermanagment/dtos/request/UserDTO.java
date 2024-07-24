package br.com.danllopes.usermanagment.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email(message = "Invalid Email")
        String email,

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
