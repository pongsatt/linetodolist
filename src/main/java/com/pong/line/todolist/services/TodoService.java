package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;

public interface TodoService {
    Todo parseTodo(String text);
}
