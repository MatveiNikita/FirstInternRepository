package com.example.firstinternrepository.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        @NotBlank String username,
        @NotBlank String email) {
}
