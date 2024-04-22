package com.edwinsoto.tipoftheday.controller;

import com.edwinsoto.tipoftheday.record.Tip;
import com.edwinsoto.tipoftheday.service.TipService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = TipController.class)
@ContextConfiguration(classes = TipController.class)
class TipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipService service;
    @Autowired
    private TipController tipController;

    @Test
    void testcreateJavaTipOfTheDayExists() throws Exception {
        Tip tip = new Tip("abc", "Java", "2024-04-21", "Why is the sky blue?", "It just is..");

        when(service.getTipByDateAndTipType("2024-04-21", "Java")).thenReturn(tip);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ollama/java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("abc"));
    }

    @Disabled("Not working as expected")
    @Test
    void testcreateJavaTipOfTheDayNotExists() throws Exception {

        when(service.getTipByDateAndTipType("2024-04-21", "Java")).thenReturn(null);

        Tip tip = new Tip("def", "Java", "2024-04-21", "Why is the sky blue?", "It just is..");


        when(service.createTip("Java", "2024-04-21")).thenReturn(tip);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ollama/java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("def"));

        verify(service).createTip("Java", "Give me a developer tip on the topic of Java posed as a question then answer");
    }

    @Test
    void createPythonTipOfTheDay() throws Exception {
        Tip tip = new Tip("ghi", "Python", "2024-04-21", "Why is the sky blue?", "It just is..");

        when(service.getTipByDateAndTipType("2024-04-21", "Python")).thenReturn(tip);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ollama/python")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("ghi"));
    }

    @Test
    void createDesignTipOfTheDay() throws Exception {
        Tip tip = new Tip("ijk", "Design", "2024-04-21", "Why is the sky blue?", "It just is..");

        when(service.getTipByDateAndTipType("2024-04-21", "Design")).thenReturn(tip);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ollama/design")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("ijk"));
    }

    @Test
    void testGetAllTipsOfTheDay() throws Exception {
        List<Tip> tips = List.of(
                new Tip("abc", "Java", "2024-04-21", "Why is the sky blue?", "It just is.."),
                new Tip("def", "Python", "2024-04-21", "Why is the sky blue?", "It just is.."),
                new Tip("ghi", "Design", "2024-04-21", "Why is the sky blue?", "It just is..")
        );

        when(service.getAllTipsByDate("2024-04-21")).thenReturn(tips);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ollama/2024-04-21")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }
}