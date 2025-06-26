package com.example.firstinternrepository.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskDTO(
        @NotBlank String title,
        @NotBlank String Description) {

}
