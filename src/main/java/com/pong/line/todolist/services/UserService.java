package com.pong.line.todolist.services;

import com.pong.line.todolist.model.User;

public interface UserService {
    User saveUser(User user);

    Iterable<User> getAllUsers();
}
