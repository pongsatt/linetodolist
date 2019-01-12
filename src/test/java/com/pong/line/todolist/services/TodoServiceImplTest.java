package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class TodoServiceImplTest {

    TodoService todoService = new TodoServiceImpl();

    @Test
    public void itCanParseFullDateAndTimeTask() {
        Todo todo = todoService.parseTodo("Buy milk : 2/5/18 : 13:00");

        assertNotNull(todo);
        assertEquals("Buy milk", todo.getTask());
        assertEquals(LocalDateTime.of(2018, 5, 2, 13, 0), todo.getDate());
    }

    @Test
    public void itCanParseDateWithoutTimeTask() {
        Todo todo = todoService.parseTodo("Buy milk : 2/5/18");

        assertNotNull(todo);
        assertEquals("Buy milk", todo.getTask());
        assertEquals(LocalDateTime.of(2018, 5, 2, 0, 0), todo.getDate());
    }

    @Test
    public void itCanParseTodayDateAndTimeTask() {
        Todo todo = todoService.parseTodo("Finish writing shopping list : today : 15:30");

        assertNotNull(todo);
        assertEquals("Finish writing shopping list", todo.getTask());
        assertEquals(LocalDate.now().atTime(15, 30), todo.getDate());
    }

    @Test
    public void itCanParseTomorrowDateAndTimeTask() {
        Todo todo = todoService.parseTodo("Watch movie : tomorrow : 18:00");

        assertNotNull(todo);
        assertEquals("Watch movie", todo.getTask());
        assertEquals(LocalDate.now().plus(1, ChronoUnit.DAYS).atTime(18, 0), todo.getDate());
    }
}