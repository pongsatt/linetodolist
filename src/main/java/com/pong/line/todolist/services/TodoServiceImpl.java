package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public Todo parseTodo(String text) {
        if (text != null && !"".equals(text)) {
            String[] split = text.split(" : ");

            String task = "";
            LocalDateTime date = LocalDateTime.now();

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
