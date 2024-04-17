package com.edwinsoto.tipoftheday.rag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class RagControllerTest {

    @Autowired
    private RagRepository ragRepository;

    @Autowired
    private RagController ragController;


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
//        RagTip tip = new RagTip(null, "test", "2024-04-16", "Why is the sky blue?", "It just is...");
//        ragRepository.save(tip);
    }


    @Test
    void testHTMLFromMDTextConversion(){
        RagTip response = ragRepository.getRagTipByDateAndTipType("2024-04-16", "Java");

        String newResponse = ragController.replaceMDTagsWithHtmlTags(response.question());

        assertNotEquals(response, newResponse);


    }
}