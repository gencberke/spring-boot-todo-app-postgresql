package com.berkedev.springboottodoapp.data.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for returning a To-do referance to client
 * Used in GET/ /api/todos/{id}
 */
@Data
@Builder
public class TodoResponse {

    private String title;

    private String description;

    private boolean completed;

    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueAt;

}
