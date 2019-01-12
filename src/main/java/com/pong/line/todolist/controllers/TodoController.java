package com.pong.line.todolist.controllers;

import com.pong.line.todolist.model.Todo;
import com.pong.line.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("")
    public List<Todo> listTodos(OAuth2Authentication user) {
        String userId = getUserId(user);
        return todoService.listTodos(userId);
    }

    @PutMapping("/toggleComplete")
    public Todo toggleComplete(String id, OAuth2Authentication user) {
        String userId = getUserId(user);
        return todoService.toggleComplete(id, userId);
    }

    @PutMapping("/toggleImportant")
    public Todo toggleImportant(String id, OAuth2Authentication user) {
        String userId = getUserId(user);
        return todoService.toggleImportant(id, userId);
    }

    private String getUserId(OAuth2Authentication user) {
        Map details = (Map) user.getUserAuthentication().getDetails();

        return (String) details.get("userId");
    }

    @PostMapping("")
    public Todo addTodo(Todo todo) {
        return todoService.addTodo(todo);
    }
}
