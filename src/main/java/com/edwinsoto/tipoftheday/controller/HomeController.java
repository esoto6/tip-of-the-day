package com.edwinsoto.tipoftheday.controller;


import com.edwinsoto.tipoftheday.record.Tip;
import com.edwinsoto.tipoftheday.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    private TipService service;

    @Autowired
    public HomeController(TipService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String home(Model model) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Tip> tips = service.getAllTipsByDate(date);
        System.out.println(tips);
        model.addAttribute("tips", tips);
        return "home";
    }


}
