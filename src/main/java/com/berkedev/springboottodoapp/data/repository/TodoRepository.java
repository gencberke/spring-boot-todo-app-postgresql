package com.berkedev.springboottodoapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.berkedev.springboottodoapp.data.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT e FROM Todo e WHERE e.id =: id")
    Todo getTodoById(@Param("id") Long id);
}
