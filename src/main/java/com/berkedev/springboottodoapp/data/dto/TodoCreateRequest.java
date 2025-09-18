package com.berkedev.springboottodoapp.data.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * DTO for creating a new To-do
 * Used in POST /api/todos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateRequest {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    private boolean completed = false;
    
    private LocalDateTime dueAt;
}
