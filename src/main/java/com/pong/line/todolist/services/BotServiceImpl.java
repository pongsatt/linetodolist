package com.pong.line.todolist.services;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.pong.line.todolist.model.Todo;
import com.pong.line.todolist.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class BotServiceImpl implements BotService {

    final TodoService todoService;
    final UserService userService;
    final LineMessagingClient lineClient;

    @Value("${app.baseurl}")
    private String baseUrl;

    @Override
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    public BotServiceImpl(TodoService todoService, UserService userService, LineMessagingClient lineClient) {
        this.todoService = todoService;
        this.userService = userService;
        this.lineClient = lineClient;
    }

    @Override
    public String processMessage(String userId, String text) {
        if ("edit".equalsIgnoreCase(text)) {
            return String.format("Click link to edit todos %s", baseUrl);
        }

        return processTodoTask(userId, text);
    }

    @Override
    public void sendPushMessage(String to, String msg) {
        lineClient.pushMessage(new PushMessage(to, new TextMessage(msg)));
    }

    @Override
    public void sendReportToUsers() {
        Iterable<User> users = userService.getAllUsers();

        for (User user : users) {
            log.info("Process userId {}", user.getUserId());
            sendPushMessage(user.getUserId(), "Summary for today.");

            try {
                Thread.sleep(1000); //make sure that previous message has been sent
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<Todo> completedTodos = todoService.getAllCompletedButNotReportedTodos(user.getUserId());
            log.info("Found {} completed todos for userId {}", completedTodos.size(), user.getUserId());

            if (!completedTodos.isEmpty()) {
                sendPushMessage(user.getUserId(), buildMessage("Completed tasks", completedTodos));
                todoService.updateReported(completedTodos);
            } else {
                sendPushMessage(user.getUserId(), "You don't have any completed tasks.");
            }

            List<Todo> incompleteTodos = todoService.getAllIncompleteTodos(user.getUserId());
            log.info("Found {} tasks to be done for userId {}", incompleteTodos.size(), user.getUserId());

            if (!incompleteTodos.isEmpty()) {
                sendPushMessage(user.getUserId(), buildMessage("Tasks to be done", incompleteTodos));
            } else {
                sendPushMessage(user.getUserId(), "Congratulation! you don't have any tasks.");
            }
        }
    }

    private String buildMessage(String title, List<Todo> todos) {
        StringBuilder builder = new StringBuilder();

        builder.append(title);
        builder.append('\n');

        for (Todo todo : todos) {
            builder.append(String.format("- %s @%s %s\n",
                    todo.getTask(),
                    todo.getDate().format(DATE_FORMAT),
                    todo.getDate().format(TIME_FORMAT)));
        }

        return builder.toString();
    }

    private String processTodoTask(String userId, String text) {
        Todo todo = todoService.parseTodo(text);

        if (todo != null) {
            User user = userService.saveUser(new User(userId));
            todo.setUserId(user.getUserId());
            todoService.addTodo(todo);

            return String.format("Your task is: %s on %s at %s",
                    todo.getTask(),
                    todo.getDate().format(DATE_FORMAT),
                    todo.getDate().format(TIME_FORMAT));
        }

        return "Please enter something like 'Buy milk : 2/5/18 : 13:00'";
    }

}
