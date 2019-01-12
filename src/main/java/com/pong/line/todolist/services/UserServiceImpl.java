package com.pong.line.todolist.services;

import com.pong.line.todolist.model.User;
import com.pong.line.todolist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }
}
