package com.example.firstinternrepository.dto;

import com.example.firstinternrepository.model.Task;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ViewUserDTO(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank List<ViewTasksDTO> tasks) {
}
