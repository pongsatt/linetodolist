package com.pong.line.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class Application {

    @RequestMapping("/")
    String home() {
        return "This is a line todo list chat bot.";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
