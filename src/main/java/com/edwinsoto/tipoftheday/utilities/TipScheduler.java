package com.edwinsoto.tipoftheday.utilities;

import com.edwinsoto.tipoftheday.rag.RagController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TipScheduler {

    private final RagController controller;

    @Autowired
    public TipScheduler(RagController controller) {
        this.controller = controller;
    }

    //    @Scheduled("1 0 * * * ?") //12:01
    @Scheduled(cron = "0 1 0 * * ?", zone = "America/New_York") //12:01
    public void scheduleJavaTip() {
        System.out.println("Java Tip has been called!");
        controller.generateJavaTip("Give me a developer tip on the topic of Java posed as a question then answer");
    }

    @Scheduled(cron = "0 5 0 * * ?", zone = "America/New_York") //12:15
    public void SchedulePythonTip() {
        System.out.println("Python Tip has been called!");
        controller.generatePythonTip("Give me a developer tip on the topic of Python3 posed as a question then answer");
    }

    @Scheduled(cron = "0 10 0 * * ?", zone = "America/New_York") // 12:30
    public void scheduleDesignTip() {
        System.out.println("Design Tip has been called!");
        controller.generateDesignTip("Give me a developer tip on software design posed as a question then answer");
    }
}
