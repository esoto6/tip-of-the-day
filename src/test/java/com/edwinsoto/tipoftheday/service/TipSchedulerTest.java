package com.edwinsoto.tipoftheday.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
class TipSchedulerTest {

    @MockBean
    private TipService service;

    @Autowired
    private TipScheduler scheduler;

    @Test
    void scheduleJavaTip() {
        scheduler.scheduleJavaTip();

        verify(service).createTip("Java", "Give me a developer tip on the topic of Java posed as a question then answer");
    }

    @Test
    void schedulePythonTip() {
        scheduler.schedulePythonTip();
        verify(service).createTip("Python", "Give me a developer tip on the topic of Python3 posed as a question then answer");
    }

    @Test
    void scheduleDesignTip() {
        scheduler.scheduleDesignTip();
        verify(service).createTip("Design", "Give me a developer tip on software design posed as a question then answer");
    }
}