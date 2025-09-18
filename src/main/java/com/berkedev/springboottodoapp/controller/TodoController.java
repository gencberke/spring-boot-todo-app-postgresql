package com.berkedev.springboottodoapp.controller;

import com.berkedev.springboottodoapp.data.dto.TodoCreateRequest;
import com.berkedev.springboottodoapp.data.dto.TodoResponse;
import com.berkedev.springboottodoapp.data.dto.TodoUpdateRequest;
import com.berkedev.springboottodoapp.data.entity.Todo;
import com.berkedev.springboottodoapp.data.mapper.TodoMapper;
import com.berkedev.springboottodoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    /**
     * GET /api/todos - Get all todos
     */
    @GetMapping
    public List<TodoResponse> getAllTodos() {
        return todoService.getAllTodos();
    }

    /**
     * GET /api/todos/to-do?id={id} - Get specific to-do by ID
     */
    @GetMapping("/todo")
    public TodoResponse getTodoById(@RequestParam("id") Long id) {
        return todoService.getTodoById(id);
    }

    /**
     * GET /api/todos/{id} - Alternative endpoint to get to-do by ID (REST standard)
     */
    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    /**
     * POST /api/todos - Create new to-do
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@Valid @RequestBody TodoCreateRequest createRequest) {
        Todo todo = todoMapper.toTodoEntity(createRequest);
        return todoService.createTodo(todo);
    }

    /**
     * PUT /api/todos/{id} - Update existing to-do
     */
    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable Long id, @Valid @RequestBody TodoUpdateRequest updateRequest) {
        return todoService.updateTodoById(id, updateRequest);
    }

    /**
     * PATCH /api/todos/{id} - Partially update existing to-do
     */
    @PatchMapping("/{id}")
    public TodoResponse patchTodo(@PathVariable Long id, @RequestBody TodoUpdateRequest updateRequest) {
        return todoService.updateTodoById(id, updateRequest);
    }

    /**
     * DELETE /api/todos/{id} - Delete to-do by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
    }
}
