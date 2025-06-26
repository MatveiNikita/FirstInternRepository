package com.example.firstinternrepository.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime timestamp,
        String status,
        String message,
        String URL
) {
}
