package com.example.firstinternrepository.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskDTO(
        @NotBlank String handlerId ,
        @NotBlank String title,
        @NotBlank String description) {
}
