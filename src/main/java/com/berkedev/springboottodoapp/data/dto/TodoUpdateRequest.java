package com.berkedev.springboottodoapp.data.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for updating an existing To-do
 * Used in PUT/PATCH /api/todos/{id}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequest {
    
    private String title;
    private String description;
    private Boolean completed;  // Using Boolean wrapper to allow null (optional update)
    private LocalDateTime dueAt;
}
