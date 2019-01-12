package com.pong.line.todolist.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class BotServiceImplTest {

    private TodoService todoService = new TodoServiceImpl();
    private BotService bot = new BotServiceImpl(todoService);

    @Test
    public void itReplyUnknowMsg() {
        String replyMsg = bot.getReplyMessage("Test");

        assertNotNull(replyMsg);
        assertEquals("Please enter something like 'Buy milk : 2/5/18 : 13:00'", replyMsg);
    }

    @Test
    public void itReplyTaskMsg() {
        String replyMsg = bot.getReplyMessage("Buy milk : 2/5/18 : 13:00");

        assertNotNull(replyMsg);
        assertTrue(replyMsg.startsWith("Your task is:"));
    }
}