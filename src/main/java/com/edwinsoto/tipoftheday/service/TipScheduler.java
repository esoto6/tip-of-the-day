package com.edwinsoto.tipoftheday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TipScheduler {

    private final TipService service;

    @Autowired
    public TipScheduler(TipService service) {
        this.service = service;
    }

    @Scheduled(cron = "0 1 0 * * ?", zone = "America/New_York") //12:01
    public void scheduleJavaTip() {
        System.out.println("Java Tip has been called!");
        service.createTip("Java", "Give me a developer tip on the topic of Java posed as a question then answer");
    }

    @Scheduled(cron = "0 5 0 * * ?", zone = "America/New_York") //12:15
    public void schedulePythonTip() {
        System.out.println("Python Tip has been called!");
        service.createTip("Python", "Give me a developer tip on the topic of Python3 posed as a question then answer");
    }

    @Scheduled(cron = "0 10 0 * * ?", zone = "America/New_York") // 12:30
    public void scheduleDesignTip() {
        System.out.println("Design Tip has been called!");
        service.createTip("Design", "Give me a developer tip on software design posed as a question then answer");
    }
}
