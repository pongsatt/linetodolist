package com.pong.line.todolist.repos;

import com.pong.line.todolist.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
