package com.pong.line.todolist;

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
        System.out.println("event: " + event);
        String replyMsg = botService.getReplyMessage(event.getMessage().getText());
        return new TextMessage(replyMsg);
    }

}
