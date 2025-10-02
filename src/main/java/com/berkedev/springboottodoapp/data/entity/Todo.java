package com.berkedev.springboottodoapp.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "todo")
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq")
    @SequenceGenerator(name = "todo_seq", sequenceName = "todo_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private boolean completed = false;

    private LocalDateTime dueAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
            updatedAt = null;
        }
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Todo(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.completed = builder.completed;
        this.dueAt = builder.dueAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String description;
        private boolean completed = false;
        private LocalDateTime dueAt;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder setDueAt(LocalDateTime dueAt) {
            this.dueAt = dueAt;
            return this;
        }


        public Todo build() {
            return new Todo(this);
        }
    }
}
