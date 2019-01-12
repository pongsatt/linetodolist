package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;
import com.pong.line.todolist.repos.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepo;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Override
    public Todo parseTodo(String text) {
        if (text != null && !"".equals(text)) {
            String[] split = text.split(" : ");

            String task = "";
            LocalDateTime date = LocalDate.now().atTime(0, 0);

            if (split.length > 0) {
                task = split[0];
            }

            if (split.length > 1) {
                date = parseDate(split[1]);
            }

            if (split.length > 2) {
                LocalTime time = parseTime(split[2]);
                date = date.toLocalDate().atTime(time);
            }

            return new Todo(task, date);
        }

        return null;
    }

    @Override
    public List<Todo> listTodos(String userId) {
        return todoRepo.findByUserIdOrderByDateAsc(userId);
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepo.save(todo);
    }

    @Override
    public Todo toggleComplete(String id, String userId) {
        Todo todo = todoRepo.findByIdAndUserId(id, userId);

        if (todo != null) {
            todo.setCompleted(!todo.isCompleted());

            return todoRepo.save(todo);
        }

        return todo;
    }

    @Override
    public Todo toggleImportant(String id, String userId) {
        Todo todo = todoRepo.findByIdAndUserId(id, userId);

        if (todo != null) {
            todo.setImportant(!todo.isImportant());
            return todoRepo.save(todo);
        }

        return todo;
    }

    @Override
    public List<Todo> getAllCompletedButNotReportedTodos(String userId) {
        return todoRepo.findByCompletedAndReportedAndUserId(true, false, userId);
    }

    @Override
    public List<Todo> getAllIncompleteTodos(String userId) {
        return todoRepo.findByCompletedAndUserId(false, userId);
    }

    @Override
    public void updateReported(List<Todo> todos) {
        if (todos != null && !todos.isEmpty()) {
            for (Todo todo : todos) {
                todo.setReported(true);
                todoRepo.save(todo);
            }
        }
    }

    public LocalTime parseTime(String timeStr) {
        if (timeStr != null && !"".equals(timeStr)) {
            timeStr = timeStr.trim();

            return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));

        }

        return null;
    }

    public LocalDateTime parseDate(String dateStr) {
        if (dateStr != null && !"".equals(dateStr)) {
            dateStr = dateStr.trim();

            LocalDate date;

            if (dateStr.equalsIgnoreCase("today")) {
                date = LocalDate.now();
            } else if(dateStr.equalsIgnoreCase("tomorrow")) {
                date = LocalDate.now().plus(1, ChronoUnit.DAYS);
            } else {
                date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d/M/yy"));
            }

            return date.atTime(0, 0);
        }

        return null;
    }
}
