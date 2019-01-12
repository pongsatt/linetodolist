package com.pong.line.todolist.controllers;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.pong.line.todolist.services.BotService;
import org.springframework.beans.factory.annotation.Autowired;

@LineMessageHandler
public class LineHandler {
    @Autowired
    private BotService botService;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String replyMsg = botService.processMessage(event.getSource().getUserId(), event.getMessage().getText());
        return new TextMessage(replyMsg);
    }

}
