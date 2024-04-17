package com.edwinsoto.tipoftheday.controller;


import com.edwinsoto.tipoftheday.rag.RagRepository;
import com.edwinsoto.tipoftheday.rag.RagTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RagRepository ragRepository;

    @GetMapping("/")
    public String home(Model model) {
        List< RagTip> ragTips = ragRepository.getRagTipsByDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("ragTips", ragTips);
        return "home";
    }

}
