package com.pong.line.todolist.model;

import java.time.LocalDateTime;

public class Todo {
    private String task;
    private LocalDateTime date;

    public Todo(String task, LocalDateTime date) {

        this.task = task;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
