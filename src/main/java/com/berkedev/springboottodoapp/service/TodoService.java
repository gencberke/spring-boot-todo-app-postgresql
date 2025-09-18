package com.berkedev.springboottodoapp.service;

import com.berkedev.springboottodoapp.data.dto.TodoResponse;
import com.berkedev.springboottodoapp.data.dto.TodoUpdateRequest;
import com.berkedev.springboottodoapp.data.entity.Todo;

import java.util.List;

public interface TodoService {
    List<TodoResponse> getAllTodos();

    TodoResponse getTodoById(Long id);

    TodoResponse createTodo(Todo todo);

    TodoResponse updateTodoById(Long id, Todo todo);
    
    TodoResponse updateTodoById(Long id, TodoUpdateRequest updateRequest);

    void deleteTodoById(Long id);
}
