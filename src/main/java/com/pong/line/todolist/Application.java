package com.pong.line.todolist;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@LineMessageHandler
public class Application {

    @RequestMapping("/")
    String home() {
        return "This is a line todo list chat bot.";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
