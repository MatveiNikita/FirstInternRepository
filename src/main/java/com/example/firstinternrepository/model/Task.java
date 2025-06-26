package com.example.firstinternrepository.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "tasks")
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @CreatedDate
    private LocalDateTime createdTime;

    @DBRef
    User handler;


}
