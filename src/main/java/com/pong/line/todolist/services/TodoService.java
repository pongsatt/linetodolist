package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;

import java.util.List;

public interface TodoService {
    Todo parseTodo(String text);

    List<Todo> listTodos(String userId);

    Todo addTodo(Todo todo);

    Todo toggleComplete(String id, String userId);

    Todo toggleImportant(String id, String userId);

    List<Todo> getAllCompletedButNotReportedTodos(String userId);

    List<Todo> getAllIncompleteTodos(String userId);

    void updateReported(List<Todo> todos);
}
