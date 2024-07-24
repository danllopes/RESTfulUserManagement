package br.com.danllopes.usermanagment.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
