package com.pong.line.todolist.services;

import com.pong.line.todolist.model.Todo;
import com.pong.line.todolist.repos.TodoRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;

public class TodoServiceImplTest {

    TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    TodoService todoService = new TodoServiceImpl(todoRepository);

    @Test
    public void itCanParseFullDateAndTimeSingleDigitTask() {
        Todo todo = todoService.parseTodo("Buy milk : 2/5/18 : 9:0");

        assertNotNull(todo);
        assertEquals("Buy milk", todo.getTask());
        assertEquals(LocalDateTime.of(2018, 5, 2, 9, 0), todo.getDateToLocalDateTime());
    }

    @Test
    public void itCanParseFullDateAndTimeDoubleDigitTask() {
        Todo todo = todoService.parseTodo("Buy milk : 02/05/18 : 09:00");

        assertNotNull(todo);
        assertEquals("Buy milk", todo.getTask());
        assertEquals(LocalDateTime.of(2018, 5, 2, 9, 0), todo.getDateToLocalDateTime());
    }

    @Test
    public void itCanParseDateWithoutTimeTask() {
        Todo todo = todoService.parseTodo("Buy milk : 2/5/18");

        assertNotNull(todo);
        assertEquals("Buy milk", todo.getTask());
        assertEquals(LocalDateTime.of(2018, 5, 2, 0, 0), todo.getDateToLocalDateTime());
    }

    @Test
    public void itCanParseTodayDateAndTimeTask() {
        Todo todo = todoService.parseTodo("Finish writing shopping list : today : 15:30");

        assertNotNull(todo);
        assertEquals("Finish writing shopping list", todo.getTask());
        assertEquals(LocalDate.now().atTime(15, 30), todo.getDateToLocalDateTime());
    }

    @Test
    public void itCanParseTomorrowDateAndTimeTask() {
        Todo todo = todoService.parseTodo("Watch movie : tomorrow : 18:00");

        assertNotNull(todo);
        assertEquals("Watch movie", todo.getTask());
        assertEquals(LocalDate.now().plus(1, ChronoUnit.DAYS).atTime(18, 0), todo.getDateToLocalDateTime());
    }

    @Test
    public void toggleComplete() {
        Todo todo = new Todo();
        todo.setCompleted(false);

        Mockito.when(todoRepository.findByIdAndUserId(anyString(), anyString())).thenReturn(todo);

        todoService.toggleComplete("1", "testsuer1");

        ArgumentCaptor<Todo> captor = ArgumentCaptor.forClass(Todo.class);
        Mockito.verify(todoRepository).save(captor.capture());
        Todo savingTodo = captor.getValue();
        assertEquals(true, savingTodo.isCompleted());
    }
}