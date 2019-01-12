package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;

import java.util.List;

public interface TodoService {
    Todo parseTodo(String text);
}
