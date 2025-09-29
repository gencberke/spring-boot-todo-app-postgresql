package com.berkedev.springboottodoapp.data.mapper;

import com.berkedev.springboottodoapp.data.dto.TodoCreateRequest;
import com.berkedev.springboottodoapp.data.dto.TodoResponse;
import com.berkedev.springboottodoapp.data.dto.TodoUpdateRequest;
import com.berkedev.springboottodoapp.data.entity.Todo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper {

    /**
     * Convert To-do entity to TodoResponse DTO
     * Usage: Returning data to client (GET requests)
     */
    public TodoResponse toTodoResponse(Todo todo) {
        if (todo == null) {
            return null;
        }
        
        return TodoResponse.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .createdAt(todo.getCreatedAt())
                .dueAt(todo.getDueAt())
                .build();
    }

    /**
     * Convert list of To-do  entities to list of TodoResponse DTOs
     * Usage: Returning list of todos (GET /api/todos)
     */
    public List<TodoResponse> toTodoResponseList(List<Todo> todos) {
        if (todos == null) {
            return null;
        }
        
        return todos.stream()
                .map(this::toTodoResponse)
                .collect(Collectors.toList());
    }

    /**
     * Convert TodoCreateRequest DTO to To-do entity
     * Usage: Creating new todos (POST requests)
     */
    public Todo toTodoEntity(TodoCreateRequest createRequest) {
        if (createRequest == null) {
            return null;
        }
        
        return Todo.builder()
                .setTitle(createRequest.getTitle())
                .setDescription(createRequest.getDescription())
                .setCompleted(createRequest.isCompleted())
                .setDueAt(createRequest.getDueAt())
                // createdAt will be set automatically by @PrePersist
                .build();
    }

    /**
     * Update existing To-do entity with data from TodoUpdateRequest
     * Usage: Updating existing todos (PUT/PATCH requests)
     */
    public void updateTodoFromRequest(Todo existingTodo, TodoUpdateRequest updateRequest) {
        if (updateRequest == null || existingTodo == null) {
            return;
        }

        if (updateRequest.getTitle() != null) {
            existingTodo.setTitle(updateRequest.getTitle());
        }
        
        if (updateRequest.getDescription() != null) {
            existingTodo.setDescription(updateRequest.getDescription());
        }
        
        if (updateRequest.getCompleted() != null) {
            existingTodo.setCompleted(updateRequest.getCompleted());
        }
        
        if (updateRequest.getDueAt() != null) {
            existingTodo.setDueAt(updateRequest.getDueAt());
        }
        
        // updatedAt will be set automatically by @PreUpdate
    }

}
