package com.example.firstinternrepository.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ViewTasksDTO(
        @NotBlank String id,
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank LocalDateTime createdTime) {
}
