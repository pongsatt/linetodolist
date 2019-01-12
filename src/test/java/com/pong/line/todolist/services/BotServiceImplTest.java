package com.pong.line.todolist.services;

import com.linecorp.bot.client.LineMessagingClient;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BotServiceImplTest {

    private TodoService todoService = Mockito.mock(TodoService.class);
    private UserService userService = Mockito.mock(UserService.class);
    private LineMessagingClient lineClient = Mockito.mock(LineMessagingClient.class);
    private BotService bot = new BotServiceImpl(todoService, userService, lineClient);

    @Test
    public void itReplyUnknowMsg() {
        String replyMsg = bot.processMessage("testuser1", "Test");

        assertNotNull(replyMsg);
        assertEquals("Please enter something like 'Buy milk : 2/5/18 : 13:00'", replyMsg);
    }

    @Test
    public void itReplyEditCommand() {
        bot.setBaseUrl("baseurl1");
        String replyMsg = bot.processMessage("testuser1", "edit");

        assertNotNull(replyMsg);
        assertTrue(replyMsg.contains("baseurl1"));
    }

}