package com.pong.line.todolist.repos;


import com.pong.line.todolist.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, String> {
    List<Todo> findByUserIdOrderByDateAsc(String userId);

    Todo findByIdAndUserId(String id, String userId);

    List<Todo> findByCompletedAndUserId(boolean completed, String userId);

    List<Todo> findByCompletedAndReportedAndUserId(boolean completed, boolean reported, String userId);
}
