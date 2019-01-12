package com.pong.line.todolist.services;

public interface BotService {
    void setBaseUrl(String baseUrl);

    String processMessage(String userId, String text);

    void sendPushMessage(String to, String msg);

    void sendReportToUsers();
}
