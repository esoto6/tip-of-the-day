package com.edwinsoto.tipoftheday.controller;

import com.edwinsoto.tipoftheday.record.Tip;
import com.edwinsoto.tipoftheday.service.TipService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(controllers = HomeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipService service;


    @Test
    public void testHomePageWithThreeTips() throws Exception {
        List<Tip> tips = List.of(
                new Tip("abc", "Java", "2024-04-21", "Why is the sky blue?", "It just is.."),
                new Tip("def", "Python", "2024-04-21", "Why is the sky blue?", "It just is.."),
                new Tip("ghi", "Design", "2024-04-21", "Why is the sky blue?", "It just is..")
        );

        when(service.getAllTipsByDate("2024-04-21")).thenReturn(tips);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tips"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.model().attribute("tips", tips));

    }

    @Test
    public void testHomePageWithNoTips() throws Exception {
        when(service.getAllTipsByDate("2024-04-21")).thenReturn(Collections.emptyList());


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tips"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.model().attribute("tips", Matchers.empty()));

    }
}