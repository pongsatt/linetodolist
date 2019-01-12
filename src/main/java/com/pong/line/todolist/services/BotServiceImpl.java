package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class BotServiceImpl implements BotService {

    final
    TodoService todoService;

    @Autowired
    public BotServiceImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public String getReplyMessage(String text) {
        Todo todo = todoService.parseTodo(text);

        if (todo != null) {
            return String.format("Your task is: %s on %s at %s",
                    todo.getTask(),
                    todo.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")),
                    todo.getDate().format(DateTimeFormatter.ofPattern("HH:mm")));
        }

        return "Please enter something like 'Buy milk : 2/5/18 : 13:00'";
    }
}
