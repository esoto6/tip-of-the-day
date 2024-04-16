package com.edwinsoto.tipoftheday.rag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RagControllerTest {

    @Autowired
    private RagRepository ollamaRepository;


    @Test
    void generateJavaTip() {
    }

    @Test
    void generatePythonTip() {
    }

    @Test
    void generateDesignTip() {
    }


    @Test
    void addNewTip(){
        RagTip tip = new RagTip(null, "test", "2024-04-16", "Why is the sky blue?", "It just is...");
        ollamaRepository.save(tip);
    }
}