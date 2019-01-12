package com.pong.line.todolist.jobs;

import com.pong.line.todolist.services.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private BotService botService;

    @Scheduled(cron = "${app.report.job.cron}")
    public void runReport() {
        log.info("Job is running");
        botService.sendReportToUsers();

        log.info("Job is done");
    }

}