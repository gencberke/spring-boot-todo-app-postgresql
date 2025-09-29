package com.berkedev.springboottodoapp.service.impl;

import com.berkedev.springboottodoapp.data.dto.TodoResponse;
import com.berkedev.springboottodoapp.data.dto.TodoUpdateRequest;
import com.berkedev.springboottodoapp.data.entity.Todo;
import com.berkedev.springboottodoapp.data.mapper.TodoMapper;
import com.berkedev.springboottodoapp.data.repository.TodoRepository;
import com.berkedev.springboottodoapp.service.TodoService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * To-do operations to use in controller. merged with JpaRepository from TodoRepository
 * Used in TodoController
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();

        return todoMapper.toTodoResponseList(todos);
    }

    @Override
    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.getTodoById(id);
        return todoMapper.toTodoResponse(todo);
    }

    @Override
    public TodoResponse createTodo(Todo todo) {
        Todo savedTodo = todoRepository.saveAndFlush(todo);
        return todoMapper.toTodoResponse(savedTodo);
    }

    @Override
    @Transactional
    public TodoResponse updateTodoById(Long id, TodoUpdateRequest updateRequest) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo with id of: " + id + " not found"));

        todoMapper.updateTodoFromRequest(existingTodo, updateRequest);
        
        Todo updatedTodo = todoRepository.save(existingTodo);
        return todoMapper.toTodoResponse(updatedTodo);
    }

    @Override
    @Transactional
    public void deleteTodoById(Long id) {
        todoRepository.delete(todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo with id of: " + id + " not found")));
    }
}
