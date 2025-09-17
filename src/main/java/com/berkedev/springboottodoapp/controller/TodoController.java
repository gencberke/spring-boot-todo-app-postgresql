package com.berkedev.springboottodoapp.controller;

import com.berkedev.springboottodoapp.data.entity.Todo;
import com.berkedev.springboottodoapp.data.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepo;

    @GetMapping("/todo")
    public Todo getTodo(@RequestParam("id") Long id) {
        return todoRepo.getTodoById(id);
    }

    @GetMapping
    public List<Todo> listTodos() {
        return todoRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody Todo body) {
        if (body.getTitle() == null || body.getTitle().isBlank())
            throw new IllegalArgumentException("title is required");

        return todoRepo.save(body);
    }

}
